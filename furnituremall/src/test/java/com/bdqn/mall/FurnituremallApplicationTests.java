package com.bdqn.mall;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class FurnituremallApplicationTests {
    //短信平台相关参数
    //这个不用改
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "105022";
    private String appSecret = "031c661d-e2e7-4be7-b75d-f2b754fb3558";

    @Test
    void contextLoads() {
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            String result = client.send("13726493246", "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return ;
            }
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            json = new JSONObject();
            json.put("memPhone","13726493246");
            json.put("code",code);
            json.put("createTime",System.currentTimeMillis());
            // 将认证码存入SESSION
            //httpSession.setAttribute("code",json);
            System.out.println(json);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }
    }

}
