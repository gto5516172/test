package com.gyf.ec.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcFileLogMapper;
import com.gyf.ec.model.EcFile;
import com.gyf.ec.model.EcFileLog;

@Service("ecFileLogService")
public class EcFileLogService extends ServiceMybatis<EcFileLog> {

	@Resource
	private EcFileLogMapper ecFileMapperLog;
	
	/**
	 * 文件日子查看
	 * 
	 * @param params
	 * @return
	 */
	public List<EcFileLog> findFileList(Map<String, Object> params){
		List<EcFileLog> list = ecFileMapperLog.findFileLogList(params);
		return list;
	}
	
	public long saveEcFileLog(EcFileLog ecFileLog) {
		return this.insertSelective(ecFileLog);
	}
}
