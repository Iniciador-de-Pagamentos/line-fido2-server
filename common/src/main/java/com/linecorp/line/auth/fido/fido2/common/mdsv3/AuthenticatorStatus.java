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

package com.linecorp.line.auth.fido.fido2.common.mdsv3;

public enum AuthenticatorStatus {
    NOT_FIDO_CERTIFIED,
    FIDO_CERTIFIED,
    USER_VERIFICATION_BYPASS,
    ATTESTATION_KEY_COMPROMISE,
    USER_KEY_REMOTE_COMPROMISE,
    USER_KEY_PHYSICAL_COMPROMISE,
    UPDATE_AVAILABLE,
    REVOKED,
    SELF_ASSERTION_SUBMITTED,
    FIDO_CERTIFIED_L1,
    FIDO_CERTIFIED_L1plus,
    FIDO_CERTIFIED_L2,
    FIDO_CERTIFIED_L2plus,
    FIDO_CERTIFIED_L3,
    FIDO_CERTIFIED_L3plus
}
