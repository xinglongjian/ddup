package com.xingtan.school.mapper;

import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.entity.GradeAlbumItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/20 0020 10:06.
 */
@Mapper
public interface GradeAlbumItemMapper {
    GradeAlbumItem getAlbumItemById(@Param("id") long id);
    List<GradeAlbumItem> getAlbumsItemByUploadId(@Param("uploadId") long uploadId);
    void insertAlbumItem(GradeAlbumItem albumItem);
    void insertBatchAlbumItems(List<GradeAlbumItem> list);
    void updateAlbumItem(GradeAlbumItem albumItem);
    void deleteAlbumItem(@Param("id") long id);
    long getCountOfUploadIds(@Param("ids") List<Long> ids);
    GradeAlbumItem getLastAlbumsItemByUploadId(@Param("uploadId") long uploadId);
}
