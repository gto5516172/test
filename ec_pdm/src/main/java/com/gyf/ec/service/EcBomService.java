package com.gyf.ec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.Photos;
import com.bety.web.service.PhotosService;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcBomMapper;
import com.gyf.ec.mapper.EcSkuMapper;
import com.gyf.ec.model.EcBom;
import com.gyf.ec.model.EcSku;

@Service("ecBomService")
public class EcBomService extends ServiceMybatis<EcBom> {
	
	@Resource
	private EcBomMapper ecBomMapper;
	
	@Resource
	private EcSkuMapper ecSkuMapper;
	
	@Resource
	private PhotosService photosService;
	
	public List<EcBom> findBomList(Map<String, Object> params) {
		return ecBomMapper.findBomList(params);
	}
	
	public EcBom findBom(Long id) {
		return ecBomMapper.findBom(id);
	}
	
	public int saveEcBom(EcBom ecBom) {
		String ps = ecBom.getBasicImgs();
		if(ecBom.getBasicImgs() != null && !"".equals(ecBom.getBasicImgs())) {
			JSONObject j = JSON.parseObject(ps);
			String img = j.getString("url");
			ecBom.setShowImg(img);
		}
		int count = 0;
		if(null == ecBom.getId()){
			count = this.insertSelective(ecBom);
		} else {
			count = this.updateByPrimaryKeySelective(ecBom);
		}
		return count;
	}
	
	public int deleteEcBom(long id) {
		return this.deleteByPrimaryKey(id);
	}
	
	public JSONObject querySku(EcBom bom) {
		JSONObject obj = new JSONObject();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sku", bom.getItemCodes());
		EcSku sku = ecSkuMapper.selectBySku(map);
		if(sku != null) {
			if(sku.getId() != bom.getSkuId()){
				obj.put("sku", sku);
				obj.put("status", 0);
			} else {
				obj.put("status", 1);
				obj.put("info", "物料编码不能是本商品编码SKU");
			}
		} else {
			obj.put("status", 1);
			obj.put("info", "没有此物料编码请重新输入");
		}
		return obj;
	}
}
