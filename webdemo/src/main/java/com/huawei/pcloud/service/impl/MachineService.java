package com.huawei.pcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.pcloud.dao.slave.MachineMapper;
import com.huawei.pcloud.entity.Machine;
import com.huawei.pcloud.service.IMachineService;

@Service
public class MachineService implements IMachineService {

	@Autowired
    private MachineMapper machineMapper;
	
	@Override
	public List<Machine> selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return machineMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Machine machine) {
		// TODO Auto-generated method stub
		return machineMapper.insert(machine);
	}

}
