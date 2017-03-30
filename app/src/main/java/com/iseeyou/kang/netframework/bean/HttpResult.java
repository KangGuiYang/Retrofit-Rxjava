package com.iseeyou.kang.netframework.bean;

/**
 * 处理你的请求结果 用于判断网络请求接口是否请求成功或者失败.
 * @param <T>
 */
public class HttpResult<T> {

    private T res; // 接口Json返回数据
    private boolean ok; // 请求是否成功
    private String message; // 请求失败错误信息
    private String code; // 请求返回结果码

    public boolean isError(){
        return ok;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "res=" + res +
                ", ok=" + ok +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
