package com.xingtan.school.service;

import com.xingtan.school.bean.AlbumSimple;
import com.xingtan.school.bean.NewUpload;
import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumItem;
import com.xingtan.school.entity.GradeAlbumUpload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/20 0020 10:25.
 */
public interface GradeAlbumService {
    GradeAlbum getAlbumById(long id);
    List<GradeAlbum> getAlbumsByGradeId(long gradeId);
    GradeAlbum getAlbumByName(String name);
    long insertAlbum(GradeAlbum album);
    void updateAlbum(GradeAlbum album);
    void deleteAlbum(long id);
    //GradeAlbumUpload
    GradeAlbumUpload getAlbumUpload(long id);
    long insertAlbumUpload(GradeAlbumUpload albumUpload);
    void insertAlbumItem(GradeAlbumItem item);
    void insertBatchAlbumItems(List<GradeAlbumItem> items);
    List<AlbumSimple> getAllAlbumSimple(long gradeId);
    // 获取最新的5个
    List<NewUpload> getNewUploads(long gradeId);


}
