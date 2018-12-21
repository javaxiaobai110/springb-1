package com.baizhi.springb1.conf;

import java.util.UUID;

public class UploadUtils {

    public static String getFileName(String originalFilename){
        String uuid = UUID.randomUUID().toString().replace("-","");
        int i = originalFilename.lastIndexOf(".");
        String substring1 = originalFilename.substring(0, i);
        String substring2 = originalFilename.substring(i, originalFilename.length());
        String fileName = substring1+uuid+substring2;
        return fileName;
    }
}
