package com.xingtan.school.service.impl;

import com.xingtan.school.bean.AlbumSimple;
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
    public long insertAlbumUpload(GradeAlbumUpload albumUpload) {
        gradeAlbumUploadMapper.insertAlbumUpload(albumUpload);
        return albumUpload.getId();
    }

    @Override
    public void insertBatchAlbumItems(List<GradeAlbumItem> items) {
        gradeAlbumItemMapper.insertBatchAlbumItems(items);
    }

    @Override
    public List<AlbumSimple> getAllAlbumSimple(long gradeId) {
        List<AlbumSimple> albumSimples = new ArrayList<>();
        List<GradeAlbum> gradeAlbums = gradeAlbumMapper.getAlbumsByGradeId(gradeId);
        if(!CollectionUtils.isEmpty(gradeAlbums)) {
            for(GradeAlbum album:gradeAlbums) {
                AlbumSimple simple = new AlbumSimple();
                simple.setGradeId(gradeId);
                simple.setId(album.getId());
                simple.setName(album.getName());
                List<GradeAlbumUpload> uploads =  gradeAlbumUploadMapper.getAlbumsUploadsByAlbumId(album.getId());
                if(!CollectionUtils.isEmpty(uploads)) {
                    GradeAlbumUpload last = uploads.get(0);
                    List<Long> uploadIds = new ArrayList<>();
                    for(GradeAlbumUpload upload:uploads) {
                        uploadIds.add(upload.getId());
                    }
                    int count = gradeAlbumItemMapper.getCountOfUploadIds(uploadIds);
                    simple.setCount(count);
                    GradeAlbumItem item = gradeAlbumItemMapper.getLastAlbumsItemByUploadId(last.getId());
                    if(item !=null) {
                        simple.setFileName(item.getPath());
                        simple.setUploadId(item.getAlbumUploadId());
                    }
                }
                albumSimples.add(simple);
            }
        }
        return albumSimples;
    }
}
