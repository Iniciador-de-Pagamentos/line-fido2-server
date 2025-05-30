/*
 * Copyright 2024 LY Corporation
 *
 * LY Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.line.auth.fido.fido2.common.server.bean.validation;

import com.linecorp.line.auth.fido.fido2.common.server.ServerAuthPublicKeyCredential;
import com.linecorp.line.auth.fido.fido2.common.server.VerifyCredential;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ServerAuthPublicKeyCredentialBeanValidationTest extends BeanValidationTestSupport {

    private static ServerAuthPublicKeyCredential origin;
    private ServerAuthPublicKeyCredential serverAuthPublicKeyCredential;

    @BeforeAll
    static void initGlobal() throws IOException {
        origin = objectMapper.readValue(VerifyCredential.class.getResourceAsStream("/json/auth/auth-response-req.json"), VerifyCredential.class).getServerPublicKeyCredential();
    }

    @BeforeEach
    void setUp() throws IOException {
        //Deep copy
        serverAuthPublicKeyCredential = objectMapper.readValue(objectMapper.writeValueAsString(origin), ServerAuthPublicKeyCredential.class);
    }

    @Test
    void validateSuccessfulRequest() {
        final Set<ConstraintViolation<ServerAuthPublicKeyCredential>> constraintViolations = validator.validate(serverAuthPublicKeyCredential);
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void validateIncompleteRequestWithNull() {

        serverAuthPublicKeyCredential.setResponse(null);
        serverAuthPublicKeyCredential.setType(null);

        final Set<ConstraintViolation<ServerAuthPublicKeyCredential>> constraintViolations = validator.validate(serverAuthPublicKeyCredential);

        assertThat(constraintViolations).hasSize(2);
        assertThat(constraintViolations).extracting(ConstraintViolation::getMessage)
                .containsOnly(MUST_NOT_BE_NULL);
    }

    @Test
    void validateIncompleteRequestWithBlank() {

        serverAuthPublicKeyCredential.setId("");

        final Set<ConstraintViolation<ServerAuthPublicKeyCredential>> constraintViolations = validator.validate(serverAuthPublicKeyCredential);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations).extracting(ConstraintViolation::getMessage)
                .containsOnly(MUST_NOT_BE_BLANK);
    }

    @Test
    void validateIncompleteRequestWithInvalidBase64Url() {

        final String id = serverAuthPublicKeyCredential.getId() + NOT_VALID_BASE64_URL_STRING;
        serverAuthPublicKeyCredential.setId(id);

        final Set<ConstraintViolation<ServerAuthPublicKeyCredential>> constraintViolations = validator.validate(serverAuthPublicKeyCredential);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations).extracting(ConstraintViolation::getMessage)
                .containsOnly(MUST_BE_A_WELL_FORMED_BASE_64);
    }
}
