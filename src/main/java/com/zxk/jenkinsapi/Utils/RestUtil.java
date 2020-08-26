package com.zxk.jenkinsapi.Utils;

import com.offbytwo.jenkins.model.JobWithDetails;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestUtil {
    /**
     * //jenkins创建job
     * @param index 满足条件的仓库
     * @throws SAXException
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public String create(Integer index,String name,String jobXml){
        JobWithDetails jobWithDetails = new JobWithDetails();
        System.out.println("==============================读取config.xml成功===============================");
        try {
            //String crumb = "1123f2f29c8b8f98525c0d4c3120e23a5f";
            UpdateUtil.server.createJob(name, jobXml, true);
            jobWithDetails = UpdateUtil.server.getJob(name);
            System.out.println("Build Success!"+index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobWithDetails.getUrl();
    }

    /**
     * //发送get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String getMethod(String url) throws IOException {
        URL restURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        conn.setRequestMethod("GET"); // POST GET PUT DELETE
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String json = "";
        String line ;
        while((line = br.readLine()) != null ){
            System.out.println("get:");
            System.out.println(line);
            json = json + line ;
        }
        br.close();
        return json;
    }

    /**
     * //发送post请求
     * @param url
     * @param query
     * @throws IOException
     */
    public static void postMethod(String url, String query) throws IOException {
        URL restURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);
        ps.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        System.out.println("Post:");
        while((line = br.readLine()) != null ){
            System.out.println(line);
        }
        br.close();
    }
}
