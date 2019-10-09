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

package cd.go.plugin.secret.hashicorp.vault.models;

import com.github.bdpiparva.plugin.base.annotations.Property;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SecretConfig {

    public static final String VAULT_URL_PROPERTY = "vault_url";
    public static final String SECURITY_TOKEN = "security_token";
    public static final String VAULT_SSL_PROPERTY = "vault_validate_ssl";

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    @Expose
    @Property(name = SECURITY_TOKEN, required = true)
    @SerializedName(SECURITY_TOKEN)
    private String secretsVaultToken;

    @Expose
    @Property(name = VAULT_URL_PROPERTY, required = true)
    @SerializedName(VAULT_URL_PROPERTY)
    private String secretsVaultURL;

    @Expose
    @Property(name = VAULT_SSL_PROPERTY, required = false)
    @SerializedName(VAULT_SSL_PROPERTY)
    private String secretsVaultSSL;

    public static SecretConfig fromJSON(String requestBody) {
        return GSON.fromJson(requestBody, SecretConfig.class);
    }

    public String getVaultToken() {
        return secretsVaultToken;
    }

    public String getVaultURL() { return secretsVaultURL; }

    public String getVaultSSL() { return secretsVaultSSL; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretConfig that = (SecretConfig) o;
        if (that.secretsVaultToken.equals(secretsVaultToken)) {
            return true;
        } else if (that.secretsVaultToken.equals(secretsVaultSSL)) {
            return Objects.equals(secretsVaultToken, that.secretsVaultSSL);
        } else {
            return Objects.equals(secretsVaultURL, that.secretsVaultURL);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(secretsVaultToken, secretsVaultURL, secretsVaultSSL);
    }
}
