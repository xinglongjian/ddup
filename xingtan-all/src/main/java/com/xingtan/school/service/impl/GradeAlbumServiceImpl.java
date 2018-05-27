package com.xingtan.school.service.impl;

import com.xingtan.account.entity.User;
import com.xingtan.account.service.UserService;
import com.xingtan.common.utils.DateUtils;
import com.xingtan.school.bean.AlbumSimple;
import com.xingtan.school.bean.NewUpload;
import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumItem;
import com.xingtan.school.entity.GradeAlbumUpload;
import com.xingtan.school.mapper.GradeAlbumItemMapper;
import com.xingtan.school.mapper.GradeAlbumMapper;
import com.xingtan.school.mapper.GradeAlbumUploadMapper;
import com.xingtan.school.service.GradeAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xinglongjian on 5/20 0020 10:30.
 */
@Service
public class GradeAlbumServiceImpl implements GradeAlbumService {

    @Autowired
    private GradeAlbumMapper gradeAlbumMapper;
    @Autowired
    private GradeAlbumUploadMapper gradeAlbumUploadMapper;
    @Autowired
    private GradeAlbumItemMapper gradeAlbumItemMapper;
    @Autowired
    private UserService userService;

    @Override
    public GradeAlbum getAlbumById(long id) {
        return gradeAlbumMapper.getAlbumById(id);
    }

    @Override
    public List<GradeAlbum> getAlbumsByGradeId(long gradeId) {
        return gradeAlbumMapper.getAlbumsByGradeId(gradeId);
    }

    @Override
    public GradeAlbum getAlbumByName(String name) {
        return gradeAlbumMapper.getAlbumByName(name);
    }

    @Override
    public long insertAlbum(GradeAlbum album) {
        gradeAlbumMapper.insertAlbum(album);
        return album.getId();
    }

    @Override
    public void updateAlbum(GradeAlbum album) {
        gradeAlbumMapper.updateAlbum(album);
    }

    @Override
    public void deleteAlbum(long id) {
        gradeAlbumMapper.deleteAlbum(id);
    }

    @Override
    public GradeAlbumUpload getAlbumUpload(long id) {
        return gradeAlbumUploadMapper.getAlbumUploadById(id);
    }

    @Override
    public long insertAlbumUpload(GradeAlbumUpload albumUpload) {
        gradeAlbumUploadMapper.insertAlbumUpload(albumUpload);
        return albumUpload.getId();
    }

    @Override
    public void insertAlbumItem(GradeAlbumItem item) {
        gradeAlbumItemMapper.insertAlbumItem(item);
    }

    @Override
    public void insertBatchAlbumItems(List<GradeAlbumItem> items) {
        gradeAlbumItemMapper.insertBatchAlbumItems(items);
    }

    @Override
    public List<AlbumSimple> getAllAlbumSimple(long gradeId) {
        List<AlbumSimple> albumSimples = new ArrayList<>();
        List<GradeAlbum> gradeAlbums = gradeAlbumMapper.getAlbumsByGradeId(gradeId);
        if (!CollectionUtils.isEmpty(gradeAlbums)) {
            for (GradeAlbum album : gradeAlbums) {
                albumSimples.add(getAlbumSimple(album));
            }
        }
        return albumSimples;
    }

    private AlbumSimple getAlbumSimple(GradeAlbum album) {
        AlbumSimple simple = new AlbumSimple();
        simple.setGradeId(album.getGradeId());
        simple.setId(album.getId());
        simple.setName(album.getName());
        List<GradeAlbumUpload> uploads = gradeAlbumUploadMapper.getAlbumsUploadsByAlbumId(album.getId());
        if (!CollectionUtils.isEmpty(uploads)) {
            GradeAlbumUpload last = uploads.get(0);
            List<Long> uploadIds = new ArrayList<>();
            for (GradeAlbumUpload upload : uploads) {
                uploadIds.add(upload.getId());
            }
            long count = gradeAlbumItemMapper.getCountOfUploadIds(uploadIds);
            simple.setCount(count);
            GradeAlbumItem item = gradeAlbumItemMapper.getLastAlbumsItemByUploadId(last.getId());
            if (item != null) {
                simple.setFileName(item.getPath());
                simple.setUploadId(item.getAlbumUploadId());
            }
        }
        return simple;
    }

    @Override
    public List<NewUpload> getNewUploads(long gradeId) {
        List<NewUpload> newUploads = new ArrayList<>();
        //前5个
        List<GradeAlbumUpload> uploads = gradeAlbumUploadMapper.getNewUploadsByGradeId(gradeId);
        for (GradeAlbumUpload upload : uploads) {
            NewUpload newUpload = new NewUpload();
            newUpload.setUploadId(upload.getId());
            newUpload.setGradeId(gradeId);
            newUpload.setUploadUserId(upload.getCreatedUserId());
            newUpload.setUploadDate(DateUtils.longFormat.format(upload.getGmtCreate()));
            // 上传者
            User user = userService.getUserById(upload.getCreatedUserId());
            if (user != null) {
                newUpload.setUploadNickName(user.getNickName());
                newUpload.setUploadRealName(user.getRealName());
            }
            //items
            List<GradeAlbumItem> items = gradeAlbumItemMapper.getAlbumsItemByUploadId(upload.getId());
            if (!CollectionUtils.isEmpty(items)) {
                int count = items.size() >= 9 ? 9 : items.size();
                int leftNum = items.size() >= 9 ? (items.size() - 9) : 0;
                List<String> fileList = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    fileList.add(items.get(i).getPath());
                }
                newUpload.setFileList(fileList);
                newUpload.setLeftNum(leftNum);
            }
            //album
            GradeAlbum album = gradeAlbumMapper.getAlbumById(upload.getAlbumId());
            AlbumSimple simple = getAlbumSimple(album);
            newUpload.setAlbumSimple(simple);

            newUploads.add(newUpload);
        }
        return newUploads;
    }
}
