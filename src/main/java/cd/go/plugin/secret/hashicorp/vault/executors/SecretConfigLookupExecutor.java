/*
 * Copyright 2019 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.plugin.secret.hashicorp.vault.executors;

import cd.go.plugin.secret.hashicorp.vault.models.LookupSecretRequest;
import cd.go.plugin.secret.hashicorp.vault.models.SecretConfig;
import com.github.bdpiparva.plugin.base.executors.secrets.LookupExecutor;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.SslConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.bdpiparva.plugin.base.GsonTransformer.toJson;

public class SecretConfigLookupExecutor extends LookupExecutor<LookupSecretRequest> {

    private static final int NOT_FOUND_ERROR_CODE = 404;

    @Override
    protected GoPluginApiResponse execute(LookupSecretRequest request) {
        List<Map<String, String>> responseList = new ArrayList<>();
        SecretConfig secretConfig = request.getConfig();
        List<String> requestKeys = request.getKeys();
        List<String> unresolvedKeys = new ArrayList<>();

        // Check if verify SSL certificate
        Boolean verifySSL;
        verifySSL = secretConfig.getVaultSSL().equals("true");

        final VaultConfig config;

        try {

            config = new VaultConfig()
                    .address(secretConfig.getVaultURL())
                    .token(secretConfig.getVaultToken())
                    .openTimeout(5)
                    .readTimeout(30)
                    .sslConfig(new SslConfig().verify(verifySSL).build())
                    .build();

            final Vault vault = new Vault(config);

            for (String key : requestKeys) {
                final String value = vault.logical().read(key).getData().get("value");

                if (value != null) {
                    Map<String, String> response = new HashMap<>();
                    response.put("key", key);
                    response.put("value", value);
                    responseList.add(response);
                } else {
                    unresolvedKeys.add(key);
                }
            }

            if (unresolvedKeys.isEmpty()) {
                return DefaultGoPluginApiResponse.success(toJson(responseList));
            }

            Map<String, String> response = Collections.singletonMap("message", String.format("Secrets with keys %s not found.", unresolvedKeys));
            return new DefaultGoPluginApiResponse(NOT_FOUND_ERROR_CODE, toJson(response));
        } catch (Exception e) {
            Map<String, String> errorMessage = Collections.singletonMap("message", "Error to connect to Vault: " + e.getMessage());
            return DefaultGoPluginApiResponse.error(toJson(errorMessage));
        }
    }

    @Override
    protected LookupSecretRequest parseRequest(String body) {
        return LookupSecretRequest.fromJSON(body);
    }
}
