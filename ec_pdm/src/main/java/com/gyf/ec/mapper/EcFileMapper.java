package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gohuinuo.web.sys.model.SysUser;
import com.gyf.ec.model.EcFile;

public interface EcFileMapper extends Mapper<EcFile> {

	public List<EcFile> findFileList(Map<String, Object> params);
	
	public int updateStatus(EcFile ecFile);
	
	public List<SysUser> findUserByFileId(Long fileId);
	
	public List<Long> findUserIdsByFileId(Long fileId);
	
	public int deleteUserFileByFileId(Long fileId);
	
	public int insertUserFileByFileId(EcFile ecFile);
	
	public Integer authentication(Map<String, Object> params);
}
