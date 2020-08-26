package com.zxk.jenkinsapi.Utils;

import com.zxk.jenkinsapi.Entity.User;
import com.zxk.jenkinsapi.constants.Valus;
import net.sf.json.JSONArray;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ApiUtils {

    public static List<User>  userList= new ArrayList<>();

    //获取gitlab projects api 的json数据
    public static List<User> getJson() throws IOException {
        String url = Valus.gitLabUrl+"/api/v4/projects";
        //通过get请求，获取到json字符串
        String line = RestUtil.getMethod(url);
        //json字符串转为json数组
        JSONArray jsonArray = JSONArray.fromObject(line);

        for (int index = 0; index < jsonArray.size(); index++) {
            User user = new User();
            user.setUserNames(getUserNames(jsonArray,index));
            user.setProjectNames(getProjectNames(jsonArray,index));
            user.setRepoUrls(getRepoUrls(jsonArray,index));
            user.setCreateProDates(getCreateProDates(jsonArray,index));
            user.setId(getId(jsonArray,index));
//            user.setSeconds(getSecond(jsonArray,index));

            userList.add(user);
            System.out.println("第"+index + "个用户:");
            System.out.println(user.getUserNames());
            System.out.println(user.getProjectNames());
            System.out.println(user.getRepoUrls());
            System.out.println(user.getCreateProDates());

        }
        return userList;
    }

    /**
     * //获取用户名
     * @param jsonArray
     * @param index
     * @return
     */
    public static String getUserNames(JSONArray jsonArray,Integer index){
        String path_with_namespace = jsonArray.getJSONObject(index).getString("path_with_namespace");
        String[] nameArray = path_with_namespace.split("/");
        //userNames.add(userName);
        return nameArray[0];
    }

    /**
     * //获取项目名
     * @param jsonArray
     * @param index
     * @return
     */
    public static String getProjectNames(JSONArray jsonArray,Integer index){
        String path_with_namespace = jsonArray.getJSONObject(index).getString("path_with_namespace");
        String[] nameArray = path_with_namespace.split("/");
        String projectName = nameArray[1];//截取仓库名
        return projectName;
    }

    /**
     * //获取gitlab项目url
     * @param jsonArray
     * @param index
     * @return
     */
    public static String getRepoUrls(JSONArray jsonArray,Integer index){
        String repoUrl = jsonArray.getJSONObject(index).getString("http_url_to_repo");
        //repoUrls.add(repoUrl);
        return repoUrl;
    }

    /**
     * //获取仓库创建时间
     * @param jsonArray
     * @param index
     * @return
     */
    public static String getCreateProDates(JSONArray jsonArray,Integer index){
        String proDate = jsonArray.getJSONObject(index).getString("created_at");
        String proDate1 = proDate.replace("T", " ");
        String[] dateArray = proDate1.split("\\.");
//        String proDate2 = dateArray[0];
//        String dateString1 = proDate2.substring(0,16);//获取除秒数以外的前面部分字符串
        return dateArray[0];
    }
    public static String getId(JSONArray jsonArray,Integer index){
        String id = jsonArray.getJSONObject(index).getString("id");
//        String proDate2 = dateArray[0];
//        String dateString1 = proDate2.substring(0,16);//获取除秒数以外的前面部分字符串
        return id;
    }
//    /**
//     * //获取秒数
//     * @param jsonArray
//     * @param index
//     * @return
//     */
//    public static Integer getSecond(JSONArray jsonArray,Integer index){
//        String proDate = jsonArray.getJSONObject(index).getString("created_at");
//        String proDate1 = proDate.replace("T", " ");
//        String[] dateArray = proDate1.split("\\.");
//        String proDate2 = dateArray[0];
//        String dateString2 = proDate2.substring(17,19);
//        Integer secPro = parseInt(dateString2);
//        return secPro;
//    }
}
