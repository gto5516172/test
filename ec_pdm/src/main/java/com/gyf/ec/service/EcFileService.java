package com.gyf.ec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.gyf.ec.mapper.EcFileMapper;
import com.gyf.ec.model.EcFile;

@Service("ecFileService")
public class EcFileService extends ServiceMybatis<EcFile> {

	@Resource
	private EcFileMapper ecFileMapper;
	
	/**
	 * 鉴权 查询有无下载权限
	 * @param userId
	 * @return
	 */
	public boolean authentication(long userId, long fileId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("fileId", fileId);
		Integer num =ecFileMapper.authentication(map);
		if(num > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 文件资料分页显示筛选查询
	 * 
	 * @param params
	 *            {"documentTheme":"文档主题","id":"文档分类id"}
	 * @return
	 */
	public List<EcFile> findFileList(Map<String, Object> params){
		List<EcFile> list = ecFileMapper.findFileList(params);
		return list;
	}
	
	public long saveEcFile(EcFile ecFile) {
		int count = 0;
		if(null == ecFile.getId()){
			count = this.insertSelective(ecFile);
			if(count > 0){
				return ecFile.getId();
			} else {
				return count;
			}
		} else {
			count = this.updateByPrimaryKeySelective(ecFile);
			if(count == 0) {
				return count;
			} else {
				return ecFile.getId();
			}
		}
	}
	
	/**
	 * 删除文档
	* @param id
	 */
	public int deleteEcFile(Long id){
		int count = this.deleteByPrimaryKey(id);
		return count;
	}
	
	/**
	 * 更新文档状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateStatus(EcFile ecFile) {
		int count = ecFileMapper.updateStatus(ecFile);
		return count;
	}
	
	/**
	 * 根据角色id查询拥有此角色的用户
	* @param roleId
	* @return
	 */
	public List<SysUser> findUserByFileId(Long fileId){
		return ecFileMapper.findUserByFileId(fileId);
	}
	
	/**
	 * 保存文件绑定的用户
	* @param sysRole
	* @return
	 */
	public int saveUserFile(EcFile ecFile){
		//旧的绑定的人员id
		List<Long> userIds = ecFileMapper.findUserIdsByFileId(ecFile.getId());
		//当前的要绑定的人员id
		List<Long> curUserIds = Lists.newArrayList(ecFile.getUserIds());
		userIds.addAll(curUserIds);
		ImmutableList<Long> mergeList = ImmutableSet.copyOf(userIds).asList();
		
		ecFileMapper.deleteUserFileByFileId(ecFile.getId());
		if(ecFile.getUserIds().length>0) {
			ecFileMapper.insertUserFileByFileId(ecFile);
		}
		//清除缓存
		SysUserUtils.clearAllCachedAuthorizationInfo(mergeList);
		return 1;
	}
}
