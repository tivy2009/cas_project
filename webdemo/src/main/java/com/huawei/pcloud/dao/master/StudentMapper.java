package com.huawei.pcloud.dao.master;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.huawei.pcloud.entity.Student;
import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(@Param(value = "uid") byte[] uid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(byte[] uid);

    List<Student> selectByCondition(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<java.util.HashMap<String,Object>> getMaptypeList(Map<String,Object> map);
}
