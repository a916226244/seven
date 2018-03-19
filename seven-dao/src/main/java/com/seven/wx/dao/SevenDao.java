package com.seven.wx.dao;

import com.seven.wx.dao.domain.Seven;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiaozhiping on 18/3/18.
 */
@Mapper
public interface SevenDao {

    public int insert( @Param("model") Seven seven);

    public int selectCount();

    public int deleteById(@Param("id") Long id);

    public List<Seven> selectByPage( @Param("start") int start, @Param("pageSize") int pageSize);
}
