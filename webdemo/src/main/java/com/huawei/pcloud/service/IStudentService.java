package com.huawei.pcloud.service;

import com.github.pagehelper.PageInfo;
import com.huawei.pcloud.entity.Student;
import java.util.List;
import java.util.Map;

public interface IStudentService {
    int deleteByPrimaryKey(byte[] uid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(byte[] uid);

    List<Student> selectByCondition(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<java.util.HashMap<String,Object>> getMaptypeList(Map<String,Object> map);
    
    PageInfo<Student> queryByPage(Student student,Integer pageNo,Integer pageSize);

}