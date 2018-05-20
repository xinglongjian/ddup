package com.xingtan.school.mapper;

import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/20 0020 10:06.
 */
@Mapper
public interface GradeAlbumUploadMapper {
    GradeAlbumUpload getAlbumUploadById(@Param("id") long id);
    List<GradeAlbumUpload> getAlbumsUploadsByAlbumId(@Param("albumId") long albumId);
    void insertAlbumUpload(GradeAlbumUpload albumUpload);
    void updateAlbumUpload(GradeAlbumUpload albumUpload);
    void deleteAlbumUpload(@Param("id") long id);
}
