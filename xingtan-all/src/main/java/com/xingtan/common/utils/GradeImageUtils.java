package com.xingtan.common.utils;

import com.xingtan.common.entity.ImageSuffix;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.*;

/**
 * 班级图像工具类,photo/grade/{gradeId}/{albumId}/{uploadId}/file
 */
@Slf4j
public class GradeImageUtils {
    public final static String GRADE_PHOTO_PATH = "photo/grade/";

    /**
     * 保存照片
     *
     * @param uploadPath
     * @param fileName
     * @param imgFileBytes
     * @throws IOException
     */
    public static void saveImage(String uploadPath, String path, String fileName, byte[] imgFileBytes) throws IOException {
        try {
            File dir = new File(uploadPath + GRADE_PHOTO_PATH + path);
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

        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] getGradeImage(File file) {
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
}
