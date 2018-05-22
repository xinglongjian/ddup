package com.xingtan.school.web;

import com.xingtan.common.entity.ImageSuffix;
import com.xingtan.common.utils.GradeImageUtils;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.school.bean.AlbumSimple;
import com.xingtan.school.bean.GradeData;
import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumItem;
import com.xingtan.school.entity.GradeAlbumUpload;
import com.xingtan.school.service.GradeAlbumService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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


    @PostMapping("/upload")
    @ApiOperation(value = "上传照片到相册", notes = "上传照片到相册", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse uploadAlbum(@RequestParam("name") String name,
                                    @RequestParam("position") String position,
                                    @RequestParam("info") String info,
                                    @RequestParam("userId") long createdUserId,
                                    @RequestParam("gradeId") long gradeId,
                                    @RequestParam(value = "imgFiles", required = false) List<MultipartFile> imageFiles) {
        try {
            GradeAlbum album = gradeAlbumService.getAlbumByName(name);
            if (album == null) {
                album = new GradeAlbum(gradeId, name, info, createdUserId);
                gradeAlbumService.insertAlbum(album);
            }
            GradeAlbumUpload upload = new GradeAlbumUpload(album.getId(),position,info,createdUserId);
            gradeAlbumService.insertAlbumUpload(upload);
            String path = String.format("%s/%s/%s/", gradeId, album.getId(),upload.getId());
            List<GradeAlbumItem> items =new ArrayList<>();
            for(MultipartFile file: imageFiles) {
                GradeImageUtils.saveImage(uploadPath, path, file.getOriginalFilename(), file.getBytes());
                items.add(new GradeAlbumItem(upload.getId(), file.getOriginalFilename()));
            }
            gradeAlbumService.insertBatchAlbumItems(items);
            log.info("Upload Grade Image Success!");
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, null);
    }


}
