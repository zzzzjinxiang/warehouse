package com.yeqifu.bus.mapper;

import com.yeqifu.bus.vo.DubboServiceDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DubboServiceDao {
    List<DubboServiceDo> selectByName(String stuName);

    int deleteByPrimaryKey(Integer id);

    int insert(DubboServiceDo record);

    int insertSelective(DubboServiceDo record);

    DubboServiceDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DubboServiceDo record);

    int updateByPrimaryKey(DubboServiceDo record);

    int updateServiceByStatus(DubboServiceDo record);
}