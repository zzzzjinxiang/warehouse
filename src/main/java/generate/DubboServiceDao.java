package generate;

import generate.DubboService;

public interface DubboServiceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DubboService record);

    int insertSelective(DubboService record);

    DubboService selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DubboService record);

    int updateByPrimaryKey(DubboService record);
}