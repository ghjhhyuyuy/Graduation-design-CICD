package com.zxk.jenkinsapi.Utils;

import com.zxk.jenkinsapi.constants.Valus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static com.zxk.jenkinsapi.Utils.ApiUtils.userList;

/**
 * Created by wzw on 2019/11/25
 *
 * @Author wzw
 */
public class MyThread extends Thread {
    private RestUtil restUtil =new RestUtil();
    private int index;
    public MyThread(int index) {
        this.index = index;
    }
    @Override
    public void run() {
        //成功则打印build
        System.out.println("==============================找到刚创建的仓库=================================");
        //修改config.xml
        UpdateUtil updateUtil = new UpdateUtil();
        String jobXml = null;
        try {
            jobXml = updateUtil.updateXml(Valus.buildDir,userList.get(index).getProjectNames(),userList.get(index).getProjectNames()+"cloud.com",userList.get(index).getRepoUrls(),Valus.haborUrl);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        String url = restUtil.create(index,userList.get(index).getProjectNames(),jobXml);
        String requestUrl = Valus.gitLabUrl+"/api/v4/projects/"+userList.get(index).getId()+"/hooks";
        boolean ifHasWebHook = sendGet(requestUrl,"private_token="+Valus.privateToken);
        if(!ifHasWebHook){
            sendPost(requestUrl,url);
        }
    }
    public static boolean sendGet(String url,String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url+ "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if(result.equals("[]")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
    public static String sendPost(String requestUrl,String url) {
        //创建webhook
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        try {
            URL realUrl = new URL(requestUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //先发送get请求查看是否已经添加webhook
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            System.out.println(url);
            url = url.replace("/job/","/project/");
            String param = "url="+url+"&private_token="+Valus.privateToken+"&token="+ Valus.token;
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
