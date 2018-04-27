package com.huawei.pcloud.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.huawei.pcloud.entity.Machine;
import com.huawei.pcloud.entity.Student;
import com.huawei.pcloud.service.IActivemqService;
import com.huawei.pcloud.service.IMachineService;
import com.huawei.pcloud.service.IStudentService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/Student")
public class StudentController {
	
	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private IMachineService machineService;

    @Autowired
    private IActivemqService activemqService;
    
    @GetMapping()
    public String Get(HttpServletRequest request,HttpSession httpSession) {
        List<Student> students = studentService.selectByCondition(new Student());
        String studentsListJson = com.alibaba.fastjson.JSON.toJSONString(students);
        logger.info("students：" + studentsListJson);
        
        Integer id = 1;
        List<Machine> machineList = machineService.selectByPrimaryKey(id);
        String machineListJson = com.alibaba.fastjson.JSON.toJSONString(machineList);
        logger.info("machineListJson：" + machineListJson);
        
        httpSession.setAttribute("nickname","Frank");
        HttpSession session = request.getSession();
        session.setAttribute("age","14");
        
        return studentsListJson;
    }
    
    //@RequestMapping(value = "/map", produces = "application/json;charset=utf-8")
    @GetMapping(value = "/map")
	@ResponseBody
	public String search() {
    	Map<String,Object> map = new HashMap<String,Object>();
		List<HashMap<String,Object>> resultMapList = studentService.getMaptypeList(map);
		Gson gson = new Gson();
		String json = gson.toJson(resultMapList);
		logger.info("json：" + json);
		return json;
	}
    
    @GetMapping(value = "/paging")
	@ResponseBody
	public String paging() {
    	Student student = new Student();
    	student.setAge(28);
    	Integer pageNo = 1;
    	Integer pageSize = 10;
    	PageInfo<Student> paginInfo = studentService.queryByPage(student, pageNo, pageSize);
		Gson gson = new Gson();
		String json = gson.toJson(paginInfo);
		logger.info("json：" + json);
		return json;
	}
    
    @GetMapping(value = "/tx")
	@ResponseBody
	public String testTransaction() {
    	//以下各事务独立
    	Student student = new Student();
        student.setUid((new Date().toString()).getBytes());
        student.setClassid(1);
        student.setAge(21);
        student.setName("分布式事务");
        studentService.insert(student);
        
        Machine machine = new Machine();
        machine.setHostname("分布式事务");
        machineService.insert(machine);
        
        activemqService.sendMessage("分布式事务测试.");
        
        return "分布式事务测试";
	}

}