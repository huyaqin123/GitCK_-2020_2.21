package com.bdqn.mall.utils;/**
 * Created by lenovo on 2019/12/3.
 */

/**
 * @program: test
 * @description: 返回对象封装
 * @author: xxxx
 * @create: 2019-12-03 21:37
 **/
public class ResponseShop{
    private int code;
    private String msg;
    private Object data;

    public ResponseShop() {
    }

    public ResponseShop(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
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
}
