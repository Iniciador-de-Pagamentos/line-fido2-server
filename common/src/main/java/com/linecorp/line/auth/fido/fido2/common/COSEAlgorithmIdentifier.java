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

package com.linecorp.line.auth.fido.fido2.common;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum COSEAlgorithmIdentifier {
    RS1(-65535),
    RS256(-257),
    RS384(-258),
    RS512(-259),
    PS256(-37),
    PS384(-38),
    PS512(-39),
    ES256(-7),
    ES384(-35),
    ES512(-36),
    EdDSA(-8),
    ES256K(-43);

    @JsonValue
    @Getter private final long value;

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static COSEAlgorithmIdentifier fromValue(long value) {
        return Arrays.stream(COSEAlgorithmIdentifier.values()).filter(e -> e.value == value)
                     .findFirst()
                     .get();
    }
}
