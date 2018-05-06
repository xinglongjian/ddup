package com.xingtan.admin.mapper;

import com.xingtan.admin.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/5 2018 13:23.
 */
@Mapper
public interface AreaMapper {
    Area getAreaById(@Param("id") long id);
    List<Area> getAreasByParentNo(@Param("parentNo") String parentNo,
                                  @Param("level") int level);
    List<Area> getAllLevelOne();
    void insertArea(Area area);
    void updateArea(Area area);
    void deleteArea(@Param("id") long id);
}
