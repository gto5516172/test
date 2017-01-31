

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bety.web.mapper.ImportExcelMapper;
import com.bety.web.model.ImportExcel;
import com.bety.web.model.Shop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

/**
 * 
 * @author BetyChen
 */

@Service("importExcelService")
public class ImportExcelService extends ServiceMybatis<ImportExcel>{

	@Resource
	private ImportExcelMapper mapper;
	

	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<ImportExcel> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<ImportExcel> list = mapper.findPageInfo(params);
		return new PageInfo<ImportExcel>(list);
	}
	public Long insertExcelBatch(List<ImportExcel> list){
		return mapper.insertExcelBatch(list);
	}
}
