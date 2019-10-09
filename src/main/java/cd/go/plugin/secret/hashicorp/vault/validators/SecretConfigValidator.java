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

package cd.go.plugin.secret.hashicorp.vault.validators;

import cd.go.plugin.secret.hashicorp.vault.models.SecretConfig;
import com.github.bdpiparva.plugin.base.validation.ValidationResult;
import com.github.bdpiparva.plugin.base.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;

public class SecretConfigValidator implements Validator {

    @Override
    public ValidationResult validate(Map<String, String> requestBody) {

        ValidationResult validationResult = new ValidationResult();
        String vaultHost = requestBody.get(SecretConfig.VAULT_URL_PROPERTY);
        
        if (StringUtils.isBlank(vaultHost)) {
            return addErrorAndReturn(validationResult, "SecretsVaultURL must not be blank.");
        }

        return validationResult;
    }

    private ValidationResult addErrorAndReturn(ValidationResult validationResult, String message) {
        validationResult.add(SecretConfig.VAULT_URL_PROPERTY, message);
        return validationResult;
    }
}
