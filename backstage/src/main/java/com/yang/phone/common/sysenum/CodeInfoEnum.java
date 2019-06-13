package com.yang.phone.common.sysenum;

public enum CodeInfoEnum {

    //成功
    suss200(200, "响应成功"),
    //user相关
    error2001(2001, "密码错误"),
    error2002(2002, "登录失败，该用户已被冻结"),
    error2003(2003, "该用户不存在"),
    error2004(2004, " 操作失败"),

    //错误信息
    error1000(1000, "参数错误"),
    error1002(1002, "参数错误");

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
