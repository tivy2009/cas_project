package com.huawei.pcloud.service;

import java.util.List;

import com.huawei.pcloud.entity.Machine;

public interface IMachineService {

	List<Machine> selectByPrimaryKey(Integer id);
	
	int insert(Machine machine);
	
}
