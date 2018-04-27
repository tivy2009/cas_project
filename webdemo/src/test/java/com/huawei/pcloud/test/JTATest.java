package com.huawei.pcloud.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huawei.pcloud.entity.Student;
import com.huawei.pcloud.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JTATest {
	
	//log
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private IStudentService studentService;
	
	@Test
	public void testRegister(){
		for(int i = 0; i < 100;i++)
		{
			Student student = new Student();
	        student.setUid(("auto-"+i).getBytes());
	        student.setClassid(1);
	        student.setAge(18+i);
	        student.setName("测试数据");
	        int result = studentService.insert(student);
			if(result == 1){
				logger.info("##写入数据成功");
			}else{
				logger.info("##写入数据失败");
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        
	}
	
	@Test
	public void testDelete(){
		byte[] uid = "01".getBytes();
		uid = null;
		studentService.deleteByPrimaryKey(uid);
	}
}
