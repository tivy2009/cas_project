package com.huawei.pcloud.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.huawei.pcloud.dao.master.StudentMapper;
import com.huawei.pcloud.entity.Student;
import com.huawei.pcloud.service.IStudentService;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements IStudentService {
	
	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(byte[] uid) {
        return studentMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(byte[] uid) {
        return studentMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<Student> selectByCondition(Student record) {
        return studentMapper.selectByCondition(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<java.util.HashMap<String,Object>> getMaptypeList(Map<String,Object> map){
    	return studentMapper.getMaptypeList(map);
    }
    
    @Override
    public PageInfo<Student> queryByPage(Student student,Integer pageNo,Integer pageSize){
    	pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Student> list = studentMapper.selectByCondition(student);
		logger.info("paging studentList：" + new Gson().toJson(list));
        
		//恢复查询不使用分页
		PageHelper.clearPage();
		List<Student> alllist = studentMapper.selectByCondition(student);
		logger.info("all studentList：" + new Gson().toJson(alllist));
		
        //用PageInfo对结果进行包装
        PageInfo<Student> page = new PageInfo<Student>(list);
        return page;
    	
    }
}