package com.example.Model;

public class RespEntity {
    private int code;
    private String msg;
    private Object data;

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum RespCode {

        SUCCESS(0, "请求成功"),
        WARN(-1, "网络异常，请稍后重试"),
        BAD_REQUEST(500,"未找到相应数据");

        private int code;
        private String msg;

        RespCode(int code, String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }

}