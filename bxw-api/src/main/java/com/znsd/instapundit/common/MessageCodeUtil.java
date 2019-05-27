package com.znsd.instapundit.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* 发送短信验证码的工具类
* */
public class MessageCodeUtil {

    private final static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    public static Map<String,Object> getNote(String phone) {
        Map<String,Object> map = new HashMap<String, Object>();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        int mobile_code = (int)((Math.random()*9+1)*100000);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        map.put("mobile_code",mobile_code);
        map.put("content",content);
        NameValuePair[] data = {
                new NameValuePair("account", "C74165562"),
                new NameValuePair("password", "691338b72e9ca3727d6963150a93c234"),
                new NameValuePair("mobile", phone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            map.put("code",code);
            if("2".equals(code)){
                System.out.println("短信提交成功");
            }
            System.out.println(msg);
            System.out.println(smsid);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            // Release connection
            method.releaseConnection();
        }
        return map;
    }
}
