/*
 * Copyright (c) 2021-2022 Huawei Device Co., Ltd.
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

package com.ohos.hapsigntool.error;

/**
 * Errors.
 *
 * @since 2021/12/28
 */
public enum ERROR {
    /**
     * Enum constant COMMAND_ERROR.
     */
    COMMAND_ERROR(101),
    /**
     * Enum constant FILE_NOT_FOUND.
     */
    FILE_NOT_FOUND(102),
    /**
     * Enum constant WRITE_FILE_ERROR.
     */
    WRITE_FILE_ERROR(103),
    /**
     * Enum constant READ_FILE_ERROR.
     */
    READ_FILE_ERROR(104),
    /**
     * Enum constant NOT_SUPPORT_ERROR.
     */
    NOT_SUPPORT_ERROR(105),
    /**
     * Enum constant NETWORK_ERROR.
     */
    NETWORK_ERROR(106),
    /**
     * Enum constant SIGN_ERROR.
     */
    SIGN_ERROR(107),
    /**
     * Enum constant VERIFY_ERROR.
     */
    VERIFY_ERROR(108),
    /**
     * Enum constant ACCESS_ERROR.
     */
    ACCESS_ERROR(109);

    /**
     * Field errorCode.
     */
    private final int errorCode;

    /**
     * getErrorCode.
     *
     * @return Integer code
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    ERROR(int code) {
        this.errorCode = code;
    }
}
