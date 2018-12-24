package com.baizhi.springb1.conf;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadUtils {

    public static String getFileName(String originalFilename){
        String uuid = UUID.randomUUID().toString().replace("-","");
        int i = originalFilename.lastIndexOf(".");
        String substring1 = originalFilename.substring(0, i);
        String substring2 = originalFilename.substring(i, originalFilename.length());
        String fileName = substring1+uuid+substring2;
        return fileName;
    }

    //截取数字  【读取字符串中第一个连续的字符串，不包含后面不连续的数字】
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
}
