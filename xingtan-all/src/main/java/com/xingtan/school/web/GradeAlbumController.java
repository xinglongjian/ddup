package com.xingtan.school.web;

import com.xingtan.common.entity.ImageSuffix;
import com.xingtan.common.utils.DateUtils;
import com.xingtan.common.utils.GradeImageUtils;
import com.xingtan.common.utils.HeadImageUtils;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.school.bean.AlbumSimple;
import com.xingtan.school.bean.GradeData;
import com.xingtan.school.bean.NewUpload;
import com.xingtan.school.bean.UploadBean;
import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumItem;
import com.xingtan.school.entity.GradeAlbumUpload;
import com.xingtan.school.service.GradeAlbumService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xinglongjian on 5/20 2018 10:33.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/album", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GradeAlbumController {

    @Autowired
    private GradeAlbumService gradeAlbumService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID获取相册", notes = "通过ID获取相册", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "相册ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAlbumById(@PathVariable("id") long id) {
        GradeAlbum grade = null;
        try {
            grade = gradeAlbumService.getAlbumById(id);
        } catch (Exception e) {
            return new BaseResponse<GradeData>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<GradeAlbum>(HttpStatus.OK, grade);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加相册", notes = "添加相册", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addAlbum(@RequestBody GradeAlbum album) {
        Long id = null;
        try {
            GradeAlbum existAlbum = gradeAlbumService.getAlbumByName(album.getName());
            if (existAlbum != null) {
                return new BaseResponse<Long>(HttpStatus.OK, "Repeat", album.getId());
            }
            id = gradeAlbumService.insertAlbum(album);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, id);
    }

    @PostMapping("/addUpload")
    @ApiOperation(value = "添加上传记录", notes = "添加上传记录", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addAlbumUpload(@RequestBody UploadBean uploadBean) {
        Long id = null;
        try {
            GradeAlbum album = gradeAlbumService.getAlbumByName(uploadBean.getName());
            if (album == null) {
                album = new GradeAlbum(uploadBean.getGradeId(), uploadBean.getName(),
                        uploadBean.getInfo(), uploadBean.getUserId());
                gradeAlbumService.insertAlbum(album);
            }
            GradeAlbumUpload upload = new GradeAlbumUpload(uploadBean.getGradeId(), album.getId(),
                    uploadBean.getPosition(), uploadBean.getInfo(), uploadBean.getUserId());
            id = gradeAlbumService.insertAlbumUpload(upload);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, id);
    }

    @GetMapping("/newUpload/{gradeId}")
    @ApiOperation(value = "获取班级最新上传前5个", notes = "获取班级最新上传前5个", httpMethod = "GET")
    @ApiImplicitParam(name = "gradeId", value = "班级ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAllNewUploadsByGradeId(@PathVariable("gradeId") long gradeId) {
        List<NewUpload> newUploads;
        try {
            newUploads = gradeAlbumService.getNewUploads(gradeId);
            log.info("getNewUpload:{}", newUploads);
        } catch (Exception e) {
            log.error("getNewUpload error:", e);
            return new BaseResponse<List<NewUpload>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, newUploads);
    }

    @GetMapping("/all/simple/{gradeId}")
    @ApiOperation(value = "获取班级全部相册简化版", notes = "获取班级全部相册简化版", httpMethod = "GET")
    @ApiImplicitParam(name = "gradeId", value = "班级ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAllSimpleAlbumsByGradeId(@PathVariable("gradeId") long gradeId) {
        List<AlbumSimple> albumSimples;
        try {
            albumSimples = gradeAlbumService.getAllAlbumSimple(gradeId);
            log.info("getAllAlbumSimple:{}", albumSimples);
        } catch (Exception e) {
            return new BaseResponse<List<AlbumSimple>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, albumSimples);
    }

    @PostMapping("/uploadAlbum")
    @ApiOperation(value = "上传照片到相册", notes = "上传照片到相册", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse uploadAlbum(HttpServletRequest request,
                                    @RequestParam(value = "imgFile", required = false) MultipartFile imageFile,
                                    @RequestParam("uploadId") long uploadId
    ) {
        try {
            GradeAlbumUpload upload = gradeAlbumService.getAlbumUpload(uploadId);
            if (upload == null) {
                return new BaseResponse<Long>(HttpStatus.BAD_REQUEST, "upload is null", null);
            }
            String originName = imageFile.getOriginalFilename();
            String suffix = originName.substring(originName.lastIndexOf(".") + 1);
            String path = String.format("%s/%s/%s/", upload.getGradeId(), upload.getAlbumId(), upload.getId());
            String fileDate = DateUtils.fileNameFormat.format(new Date());
            String fileName = String.format("%s.%s", fileDate, suffix);
            GradeImageUtils.saveImage(uploadPath, path, fileName, imageFile.getBytes());
            GradeAlbumItem item = new GradeAlbumItem(upload.getId(), fileName);
            gradeAlbumService.insertAlbumItem(item);
            log.info("Upload Grade Image Success!");
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, null);
    }

    @GetMapping("/getAlbumImage/{gradeId}/{albumId}/{uploadId}/{fileName}/{size}")
    @ApiOperation(value = "获取上传的图像", notes = "获取上传的图像", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeId", value = "班级ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "uploadId", value = "上传ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "fileName", value = "文件名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public void getGradeImage(@PathVariable("gradeId") Long gradeId,
                              @PathVariable("albumId") Long albumId,
                              @PathVariable("uploadId") Long uploadId,
                              @PathVariable("fileName") String fileName,
                              @PathVariable("size") Integer size,
                              HttpServletResponse res) {
        ServletOutputStream writer = null;
        try {
            String sizeform = "";
            if (size != null) {
                sizeform = String.format("_%s*%s", size, size);
            }
            String[] fileSplits = fileName.split("[.]");
            String filePath = String.format("%s/%s/%s/%s%s.%s", gradeId, albumId, uploadId, fileSplits[0], sizeform, fileSplits[1]);
            log.info("filePath:{}", filePath);
            File file = new File(uploadPath + GradeImageUtils.GRADE_PHOTO_PATH + "/" + filePath);
            byte[] fileByte = GradeImageUtils.getGradeImage(file);
            String fileType = new MimetypesFileTypeMap().getContentType(file);
            res.setContentType(fileType);
            res.setContentLength(fileByte.length);
            writer = res.getOutputStream();
            writer.write(fileByte);
            writer.flush();
        } catch (Exception e) {
            log.error("getGradeImage error, gradeId:{},albumId:{},uploadId:{},fileName:{},size:{},error:{}",
                    gradeId, albumId, uploadId, fileName, size, e);
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (writer != null)
                    writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
