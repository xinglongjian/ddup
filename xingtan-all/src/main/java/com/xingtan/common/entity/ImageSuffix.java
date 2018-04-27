package com.xingtan.common.entity;

public enum  ImageSuffix {

    JPG(".jpg"),
    GIF(".gif");

    private String name;

    private ImageSuffix(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
