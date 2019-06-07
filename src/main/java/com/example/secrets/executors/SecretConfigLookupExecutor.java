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

package com.example.secrets.executors;

import com.example.secrets.models.LookupSecretRequest;
import com.github.bdpiparva.plugin.base.executors.secrets.LookupExecutor;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.ArrayList;
import java.util.List;

import static com.github.bdpiparva.plugin.base.GsonTransformer.toJson;

public class SecretConfigLookupExecutor extends LookupExecutor<LookupSecretRequest> {
    @Override
    protected GoPluginApiResponse execute(LookupSecretRequest request) {
        List<String> secrets = new ArrayList<>();

        /*
        SecretConfig secretConfig = request.getConfig();
        List<String> requestKeys = request.getKeys();

        utilize the secret config to resolve the keys present in requestKeys and add the same in secrets
        */

        return DefaultGoPluginApiResponse.success(toJson(secrets));
    }

    @Override
    protected LookupSecretRequest parseRequest(String body) {
        return LookupSecretRequest.fromJSON(body);
    }
}
