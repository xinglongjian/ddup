package com.xingtan.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Imagebase64 {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

//    public static void main(String[] args) {
//        System.out.println(getImageBinary()); // image to base64
//        base64StringToImage(getImageBinary()); // base64 to image
//    }

    static String getImageBinary(String filePath, String formatName) {
        File f = new File(filePath);
        try {
            BufferedImage bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, formatName, baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            log.error("getImageBinary error, filePath:{}, causedBy:{}", filePath, e.getMessage());
            return StringUtils.EMPTY;
        }
    }

    static void base64StringToImage(String base64String, String filePath, String formatName) {
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File f1 = new File(filePath);
            ImageIO.write(bi1, formatName, f1);
        } catch (IOException e) {
            log.error("base64StringToImage error,filePath:{},causedBy:{}", filePath, e.getMessage());
        }
    }
}
