package com.zxk.jenkinsapi.Utils;

import com.zxk.jenkinsapi.constants.Valus;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by wzw on 2020/5/22
 *
 * @author wzw
 */
public class KeyWordUtil {
    //todo 在容器云平台相关功能完成后启用
//    public static String getKeyWord() throws IOException {
//        String fileName = Valus.keyWordFile;
//        FileInputStream fileReader = new FileInputStream(fileName);
//        byte b[] = new byte[fileReader.available()];
//        // 从输入流读取数据
//        fileReader.read(b);
//        String keyWord = new String(b);
//        System.out.println(keyWord);
//        // 关闭输入流，对文件的操作，一定要在最后关闭输入输出流
//        fileReader.close();
//        return keyWord;
//    }
}
