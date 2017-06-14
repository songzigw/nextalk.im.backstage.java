/*
 * Copyright [2016] [zhangsong <songm.cn>].
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
 * 
 */
package songm.im.backstage;

public class ApiException extends Exception {

    private static final long serialVersionUID = 9060028080362777992L;

    private ErrorCode errorCode;

    private String errorDesc;

    public ApiException(ErrorCode errorCode, String errorDesc) {
        super(errorCode + ": " + errorDesc);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ApiException(ErrorCode errorCode, String errorDesc,
            Throwable cause) {
        super(errorCode + ": " + errorDesc, cause);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public static enum ErrorCode {
        /** 请求异常 */
        REQUEST,

        /** API签名失败 */
        SIGN_FAILURE,
    }
}
