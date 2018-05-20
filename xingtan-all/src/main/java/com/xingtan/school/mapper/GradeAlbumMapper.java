package com.xingtan.school.mapper;

import com.xingtan.school.entity.GradeAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/20 0020 10:06.
 */
@Mapper
public interface GradeAlbumMapper {
    GradeAlbum getAlbumById(@Param("id") long id);
    List<GradeAlbum> getAlbumsByGradeId(@Param("gradeId") long gradeId);
    GradeAlbum getAlbumByName(@Param("name") String name);
    void insertAlbum(GradeAlbum album);
    void updateAlbum(GradeAlbum album);
    void deleteAlbum(@Param("id") long id);
}
