package com.xingtan.common.utils;

import com.xingtan.common.constants.FileConstants;
import com.xingtan.common.entity.ImageSuffix;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 头像工具类
 */
@Slf4j
public class HeadImageUtils {
    public final static String HEAD_IMAGE_PATH = "photo/headImage";

    /**
     * 保存为头像
     *
     * @param uploadPath
     * @param fileName
     * @param imgFileBytes
     * @throws IOException
     */
    public static void saveHeadImage(String uploadPath, String fileName, byte[] imgFileBytes) throws IOException {
        try {
            File dir = new File(uploadPath + HEAD_IMAGE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            try (BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file))) {
                buffStream.write(imgFileBytes);
                buffStream.close();
            }
            // 压缩一张小图
            log.info(fileName);
            String[] splits = fileName.split("[\\.]");
            String fileName100=String.format("%s_100*100.%s", splits[0], splits[1]);
            log.info(fileName100);
            File file100 = new File(dir, fileName100);
            Thumbnails.of(file).size(100, 100).outputQuality(0.25).toFile(file100);

            String fileName200=String.format("%s_200*200.%s", splits[0], splits[1]);
            log.info(fileName200);
            File file200 = new File(dir, fileName200);
            Thumbnails.of(file).size(200, 200).outputQuality(0.25).toFile(file200);
        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] getHeadImage(File file) {
        if (!file.exists()) {
            log.error("file is not exist, fileName:{}", file.getPath());
            return null;
        }
        byte[] buf = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(buf);
            fis.close();
        } catch (IOException e) {
            log.error("Error occurs while read resource file.", e);
            return null;
        }
        return buf;
    }

    /**
     * 获取base64的图像
     *
     * @param uploadPath
     * @param fileName
     * @return
     */
    public static String getBase64HeadImage(String uploadPath, String fileName) {
        String filePath = uploadPath + HEAD_IMAGE_PATH + "/" + fileName;
        return Imagebase64.getImageBinary(filePath, ImageSuffix.JPG.getName());
    }
}
