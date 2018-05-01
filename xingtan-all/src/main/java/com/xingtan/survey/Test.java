package com.xingtan.survey;

import com.xingtan.common.entity.ImageSuffix;

public class Test {
    public static void main(String[] args) {
        String fileName ="66.jpg";
        String[] splits = fileName.split("[\\.]");
        String fileName100=String.format("%s_100*100.%s", splits[0], splits[1]);
        System.out.println(fileName100);
    }
}
