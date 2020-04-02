package com.bdqn.mall.utils;/**
 * Created by lenovo on 2019/12/3.
 */

/**
 * @program: test
 * @description: 响应数据封装
 * @author: xxxx
 * @create: 2019-12-03 21:29
 **/
public class ResponseUtil {


    /**
     * 直接返回成功
     * @return
     */
    public static ResponseShop ok(){
        ResponseShop success = new ResponseShop(200, "success", null);
        return success;
    }

    /**
     * 返回带参数的成功对象
     * @return
     */
    public static ResponseShop ok(Object obj){
        return new ResponseShop(200,"success",obj);
    }

    /**
     * 返回错误
     * @return
     */
    public static ResponseShop error(){
        return new ResponseShop(500,"error",null);
    }

    /**
     * 返回错误
     * @return
     */
    public static ResponseShop error(String msg){
        return new ResponseShop(500,msg,null);
    }

    /**
     * 返回错误
     * @return
     */
    public static ResponseShop error(int code,String msg){
        return new ResponseShop(code,msg,null);
    }

    /**
     * 返回所有指定属性
     * @param code
     * @param msg
     * @param object
     * @return
     */
    public ResponseShop result(int code,String msg,Object object){
        return new ResponseShop(code,msg,object);
    }
}
