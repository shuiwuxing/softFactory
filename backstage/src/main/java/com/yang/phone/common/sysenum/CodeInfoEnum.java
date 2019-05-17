package com.yang.phone.common.sysenum;

public enum CodeInfoEnum {

    //成功
    suss200(200, "响应成功"),
    //错误信息
    error1000(1000, "参数错误"),
    error1001(1001, "参数错误");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CodeInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeInfoEnum getPaymentType(int value) {
        for (CodeInfoEnum codeInfo : CodeInfoEnum.values()) {
            if (value == codeInfo.code) {
                return codeInfo;
            }
        }
        return null;
    }
}
