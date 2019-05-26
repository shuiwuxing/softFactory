package com.yang.phone.common;

import com.yang.phone.common.sysenum.CodeInfoEnum;
/*
 * 请求响应信息
 *
*/
public class ResultMessage {

    public ResultMessage(){
        this.code= CodeInfoEnum.suss200.getCode();
        this.message= CodeInfoEnum.suss200.getMessage();
    }
    public ResultMessage(Object obj){
        this.code= CodeInfoEnum.suss200.getCode();
        this.message= CodeInfoEnum.suss200.getMessage();
        this.data=obj;
    }
    public ResultMessage(int code,String message,Object obj){
        this.code= CodeInfoEnum.suss200.getCode();
        this.message= CodeInfoEnum.suss200.getMessage();
        this.data=obj;
    }


    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
