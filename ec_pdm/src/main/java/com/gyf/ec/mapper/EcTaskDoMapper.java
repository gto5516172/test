package com.gyf.ec.mapper;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcTaskDo;

public interface EcTaskDoMapper extends Mapper<EcTaskDo>  {
	public EcTaskDo queryOtherDoByUserId(long taskUser,long taskId);
	public EcTaskDo queryDoByUserId(long taskUser,long taskId);
	public Integer queryCountByStatus(Long taskUser,Long taskId,String status,Integer type);
//	public Integer updateDoStatus(String doStatus,Long taskUser,Long taskId);
//	public Integer updateOtherDoStatus(String doStatus,Long taskUser,Long taskId);
//	public Integer queryTaskDoStatus(String doStatus,Long taskUser,Long taskId);
}
