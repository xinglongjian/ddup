package com.xingtan.admin.service.impl;

import com.xingtan.admin.entity.Area;
import com.xingtan.admin.mapper.AreaMapper;
import com.xingtan.admin.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xinglongjian on 5/5 2018 13:25.
 */
@Service
@Slf4j
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Area getAreaById(long id) {
        return areaMapper.getAreaById(id);
    }

    @Override
    public List<Area> getAreasByParentNo(String parentNo, int level) {
        return areaMapper.getAreasByParentNo(parentNo, level);
    }

    @Override
    public List<Area> getAllLevelOne() {
        return areaMapper.getAllLevelOne();
    }

    @Override
    public long insertArea(Area area) {
        areaMapper.insertArea(area);
        return area.getId();
    }

    @Override
    public void updateArea(Area area) {
        areaMapper.updateArea(area);
    }

    @Override
    public void deleteArea(long id) {
        areaMapper.deleteArea(id);
    }
}
