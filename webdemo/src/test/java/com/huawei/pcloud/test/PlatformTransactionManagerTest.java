package com.huawei.pcloud.test;

import java.util.Date;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.huawei.pcloud.entity.Machine;
import com.huawei.pcloud.entity.Student;
import com.huawei.pcloud.service.IActivemqService;
import com.huawei.pcloud.service.IMachineService;
import com.huawei.pcloud.service.IStudentService;

/**
 * 编程式分布式事务测试
 * @author hWX486145
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PlatformTransactionManagerTest {
	protected final Logger logger = LogManager.getLogger();
	
	@Resource
	private PlatformTransactionManager transactionManager;
	
	@Resource
	private DataSource mysqlmasterdataSource;
	
	@Resource
	private DataSource mysqlslavedataSource;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IMachineService machineService;
	
	@Autowired
	private IActivemqService activemqService;
	
	private JdbcTemplate jdbcTemplateMaster;
	
	private JdbcTemplate jdbcTemplateSlave;
	
    private static final String INSERT_MASTER_SQL = "insert into student(Uid,Name,Age,ClassId) values(?,?,?,?)";
    private static final String COUNT_MASTER_SQL = "select count(*) from student";
    
    private static final String INSERT_SLAVE_SQL = "insert into machine(hostname) values(?)";
    private static final String COUNT_SLAVE_SQL = "select count(*) from machine";
    
    /**
     * 测试编程式方式实现JTA事务管理
     */
	@Test
	public void testdelivery(){
		
		//此处写入machine到DB中与下面不在同一个事务
		Machine machine = new Machine();
        machine.setHostname("分布式事务");
        machineService.insert(machine);
		
		//定义事务隔离级别，传播行为，
	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
	    def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);  
	    //事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；
	    //获取事务状态后，Spring根据传播行为来决定如何开启事务(关键之处)
	    //从此行开始后面的逻辑全部受JAT事务管理器管理
	    TransactionStatus status = transactionManager.getTransaction(def);
	    
	    jdbcTemplateMaster = new JdbcTemplate(mysqlmasterdataSource);
	    jdbcTemplateSlave = new JdbcTemplate(mysqlslavedataSource);
	    
	    int i = jdbcTemplateMaster.queryForObject(COUNT_MASTER_SQL, Integer.class);
	    System.out.println("表中记录总数："+i);
	    
	    try {  
	    	jdbcTemplateMaster.update(INSERT_MASTER_SQL, (new Date()).toString().getBytes(),"分布式事务",18,1);
	        
	    	jdbcTemplateSlave.update(INSERT_SLAVE_SQL, (new Date()).toString());
	    	
	    	Student student = new Student();
	        student.setUid(((new Date()).toString()).getBytes());
	        student.setClassid(1);
	        student.setAge(22);
	        student.setName("分布式事务");
	        int result = studentService.insert(student);
	        if(result == 1){
				logger.info("##写入数据成功");
			}else{
				logger.info("##写入数据失败");
			}
	        
	        activemqService.sendMessage("分布式事务测试.");
	        
	    	//测试事务回滚
	    	//int z = 1/0;
	    	
	        transactionManager.commit(status);  //提交status中绑定的事务
	    } catch (RuntimeException e) {  
	    	transactionManager.rollback(status);  //回滚
	    }  
	    i = jdbcTemplateMaster.queryForObject(COUNT_MASTER_SQL, Integer.class);
	    logger.info("表中记录总数："+i);
	}
	
	/**
	 * 测试编程式方式自动回滚事务
	 */
	@Test
	public void testTransactionTemplate(){
		jdbcTemplateMaster = new JdbcTemplate(mysqlmasterdataSource);
		jdbcTemplateSlave = new JdbcTemplate(mysqlslavedataSource);
		int i = jdbcTemplateMaster.queryForObject(COUNT_MASTER_SQL, Integer.class);
		int j = jdbcTemplateSlave.queryForObject(COUNT_SLAVE_SQL, Integer.class);
		logger.info("start mster总数："+i +",slave总数:"+j);
		//构造函数初始化TransactionTemplate
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		//重写execute方法实现事务管理
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				jdbcTemplateSlave.update(INSERT_SLAVE_SQL, (new Date()).toString());
				
				jdbcTemplateMaster.update(INSERT_MASTER_SQL, ((new Date()).toString()).getBytes(),"分布式事务",18,1);
				
				Machine machine = new Machine();
		        machine.setHostname("分布式事务");
		        machineService.insert(machine);
		        
		        activemqService.sendMessage("分布式事务测试.");
				
			}}
		);
		i = jdbcTemplateMaster.queryForObject(COUNT_MASTER_SQL, Integer.class);
		j = jdbcTemplateSlave.queryForObject(COUNT_SLAVE_SQL, Integer.class);
		logger.info("end   mster总数："+i +",slave总数:"+j);
	}
}
