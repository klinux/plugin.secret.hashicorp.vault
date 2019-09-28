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

package com.example.secrets.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SecretConfigTest {

    @Test
    void shouldDeserializeFromJson() {
        SecretConfig secretConfigToken = SecretConfig.fromJSON("{" +
                "\"SecretsVaultToken\": \"some_property\" " +
                "}"
        );

        assertThat(secretConfigToken.getVaultToken()).isEqualTo("some_property");

        SecretConfig secretConfigURL = SecretConfig.fromJSON("{" +
                "\"SecretsVaultURL\": \"some_property\" " +
                "}"
        );
        
        assertThat(secretConfigURL.getVaultURL()).isEqualTo("some_property");
    }
}