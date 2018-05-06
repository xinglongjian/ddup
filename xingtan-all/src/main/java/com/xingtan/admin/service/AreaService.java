package com.xingtan.admin.service;

import com.xingtan.admin.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/5 2018 13:25.
 */
public interface AreaService {
    Area getAreaById(long id);

    List<Area> getAreasByParentNo(String parentNo, int level);

    List<Area> getAllLevelOne();

    long insertArea(Area area);

    void updateArea(Area area);

    void deleteArea(long id);
}
