package com.demo.web.bean.ResponseDto;


import java.io.Serializable;

public class ResponseDto<T> implements Serializable{

    private int code = 200;

    private String msg = "操作成功";

    private T data;

    private boolean status = true;


    public int getCode() {
        return code;
    }

    public ResponseDto setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public ResponseDto setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public ResponseDto setStatus(boolean status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return "ResponseDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
