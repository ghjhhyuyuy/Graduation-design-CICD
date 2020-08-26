package com.zxk.jenkinsapi.Utils;

import com.offbytwo.jenkins.JenkinsServer;
import com.zxk.jenkinsapi.constants.Valus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UpdateUtil {
    static JenkinsServer server;
    static String url = Valus.jenkinsUrl;
    static String name = Valus.jenkinsName;
    static String password = Valus.jenkinsPassword;
    public  String updateXml (String buildDir,String module,String host,String gitHost,String habor) throws IOException, URISyntaxException {
        server = new JenkinsServer(new URI(url), name, password);
        //todo 容器云平台要将类型写入这个文件
        //String jobKeyWord = getKeyWord();
        //String jobXml = server.getJobXml(jobKeyWord+Valus.demoJobName);
        String jobXml = server.getJobXml(Valus.demoJobName);
        System.out.println(jobXml);
        jobXml = jobXml.replace("[buildDir]",buildDir);
        jobXml = jobXml.replace("[module]",module);
        jobXml = jobXml.replace("[host]",host);
        jobXml = jobXml.replace("[gitHost]",gitHost);
        jobXml = jobXml.replace("[habor]",habor);
        return jobXml;
    }
}
