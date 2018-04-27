package com.huawei.pcloud.dao.slave;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huawei.pcloud.entity.Machine;

@Repository
public interface MachineMapper {

	List<Machine> selectByPrimaryKey(Integer id);
	
	int insert(Machine machine);
	
}
