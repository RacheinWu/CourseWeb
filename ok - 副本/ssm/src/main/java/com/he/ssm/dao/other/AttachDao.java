package com.he.ssm.dao.other;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

/**
 * @author itaem
 * date:2021-03-10 2021/3/10:22:03
 */
@Mapper
public interface AttachDao{
    int deleteByPrimaryKey(Long id);

    int insert(Attach record);

    int insertSelective(Attach record);

    Attach selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);

    int batchInsert(@Param("list") List<Attach> list);
    List<Attach> findByIdIn(@Param("idCollection")Collection<Long> idCollection);
    int deleteByIdIn(@Param("idCollection")Collection<Long> idCollection);
    List<Attach> findByDataIdAndAttachType(@Param("dataId")Long dataId,@Param("attachType")String attachType);
    int deleteByDataIdIn(@Param("dataIdCollection")Collection<Long> dataIdCollection);

    @Select("select id, downloadCount from attach where relativePath = '${r}'")
    Attach getIdAndCountByRU(@Param("r") String url);

    @Select("update attach set downloadCount=${count} where id = ${id}")
    void updateCountById(@Param("id") Long id, @Param("count") Long count);

}