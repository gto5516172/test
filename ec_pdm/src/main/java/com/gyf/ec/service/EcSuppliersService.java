package com.gyf.ec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.mapper.SysRoleMapper;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcSuppliersMapper;
import com.gyf.ec.model.EcSuppliers;

@Service("ecSuppliersService")
public class EcSuppliersService extends ServiceMybatis<EcSuppliers> {
	
	@Resource
	private EcSuppliersMapper ecSuppliersMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	public List<EcSuppliers> findAllSuppliers() {
		return ecSuppliersMapper.findAllSuppliers();
	}
	
	
	/**
	 * 根据条件分页查询EcSuppliers列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<EcSuppliers> findPageInfo(Map<String,Object> params) {
		//params.put(Constant.CACHE_USER_DATASCOPE, SysUserUtils.dataScopeFilterString("so", "sur","user_id"));
        PageHelper.startPage(params);
        List<EcSuppliers> list = ecSuppliersMapper.findPageInfo(params); 
        return new PageInfo<EcSuppliers>(list);
	}
	
	/**
	 *新增或更新供应商
	 */
	public int saveEcSuppliers(EcSuppliers ecSuppliers){
		int count = 0;
		if(null == ecSuppliers.getId()){
			count = this.insertSelective(ecSuppliers);
		}else{
			count = this.updateByPrimaryKeySelective(ecSuppliers);
			//清除缓存
			List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(ecSuppliers.getId());
			SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
		}
	    return count;
	}
	
	/**
	 * 删除供应商
	 * @param id
	 */
	public int deleteEcSuppliers(Long id){
		List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(id);
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		// 判断是否存在子集关联
		int num = ecSuppliersMapper.querySuppliersRelation(map);
		if(num == 0) {
			count = this.deleteByPrimaryKey(id);
			//清除缓存
			SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
		} else {
			count = -1;
		}
		return count;
	}
	
	/**
	 * 更新供应商状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateStatus(EcSuppliers ecSuppliers) {
		List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(ecSuppliers.getId());
		int count = ecSuppliersMapper.updateStatus(ecSuppliers);
		//清除缓存
		SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
		return count;
	}
}
