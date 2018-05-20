package com.xingtan.school.service.impl;

import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.mapper.GradeAlbumItemMapper;
import com.xingtan.school.mapper.GradeAlbumMapper;
import com.xingtan.school.mapper.GradeAlbumUploadMapper;
import com.xingtan.school.service.GradeAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insertAlbum(GradeAlbum album) {
        gradeAlbumMapper.insertAlbum(album);
    }

    @Override
    public void updateAlbum(GradeAlbum album) {
        gradeAlbumMapper.updateAlbum(album);
    }

    @Override
    public void deleteAlbum(long id) {
        gradeAlbumMapper.deleteAlbum(id);
    }
}
