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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LookupSecretRequestTest {
    @Test
    void shouldDeserializeFromJson() {
        LookupSecretRequest tokenLookupSecretRequest = LookupSecretRequest.fromJSON("{" +
                "\"configuration\": {" +
                "\"security_token\": \"some_property\" " +
                "   }," +
                "\"keys\": [\"key1\",\"key2\"]" +
                "}"
        );

        assertThat(tokenLookupSecretRequest.getConfig().getVaultToken()).isEqualTo("some_property");
        assertThat(tokenLookupSecretRequest.getKeys().size()).isEqualTo(2);

        LookupSecretRequest urlLookupSecretRequest = LookupSecretRequest.fromJSON("{" +
                "\"configuration\": {" +
                "\"vault_url\": \"some_property\" " +
                "   }," +
                "\"keys\": [\"key1\",\"key2\"]" +
                "}"
        );

        assertThat(urlLookupSecretRequest.getConfig().getVaultURL()).isEqualTo("some_property");
        assertThat(urlLookupSecretRequest.getKeys().size()).isEqualTo(2);

        LookupSecretRequest sslLookupSecretRequest = LookupSecretRequest.fromJSON("{" +
                "\"configuration\": {" +
                "\"vault_validate_ssl\": \"some_property\" " +
                "   }," +
                "\"keys\": [\"key1\",\"key2\"]" +
                "}"
        );

        assertThat(sslLookupSecretRequest.getConfig().getVaultSSL()).isEqualTo("some_property");
        assertThat(sslLookupSecretRequest.getKeys().size()).isEqualTo(2);
    }
}