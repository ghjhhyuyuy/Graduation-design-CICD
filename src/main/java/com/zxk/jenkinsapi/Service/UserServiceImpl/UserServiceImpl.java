package com.zxk.jenkinsapi.Service.UserServiceImpl;

import com.zxk.jenkinsapi.Utils.*;
import com.zxk.jenkinsapi.constants.Valus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.zxk.jenkinsapi.Utils.ApiUtils.userList;

@Service
public class UserServiceImpl{
    /**
     * //创建仓库触发Jenkins创建任务
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws TransformerException
     */
    public void createJob() throws IOException, ParseException {
        //获取当前时间dateStringNow
        String dateStringParse = TimeUtils.getTimeNow();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(dateStringParse);
        //当前时间
        long dateStringNow = date1.getTime();
        userList.clear();
        ApiUtils.getJson();
        for (Integer index= 0; index < userList.size(); index++){
            //获取仓库创建时间dateStringPro
           Date date2 = format.parse(userList.get(index).getCreateProDates());
           //创建时间
           long dateStringPro = date2.getTime();
            //算出时间差dates
            long dates = dateStringNow - dateStringPro;
            //28805000是28800000+5000，28800000为主机时差
            if ( Math.abs(dates)< 28905000 ) {
                ScheduledExecutorService exec = Executors.newScheduledThreadPool(20);
                exec.execute(new MyThread(index));
            }
        }
    }

}
