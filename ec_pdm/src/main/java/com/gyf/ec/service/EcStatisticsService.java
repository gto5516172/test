package com.gyf.ec.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcSkuCostPriceLogMapper;
import com.gyf.ec.mapper.EcStatisticsMapper;
import com.gyf.ec.mapper.EcSuppliersComPriceLogMapper;
import com.gyf.ec.model.EcSkuCostPriceLog;
import com.gyf.ec.model.EcStatistics;
import com.gyf.ec.model.EcStatisticsBySku;
import com.gyf.ec.model.EcStatisticsBySupp;
import com.gyf.ec.model.EcStatisticsUserOrType;
import com.gyf.ec.model.EcSuppliersComPriceLog;

@Service("ecStatisticsService")
public class EcStatisticsService extends ServiceMybatis<EcStatistics> {

	@Resource
	private EcStatisticsMapper mapper;
	
	@Resource
	private EcSkuCostPriceLogMapper ecSkuCostPriceLogMapper;
	
	@Resource
	private EcSuppliersComPriceLogMapper ecSuppliersComPriceLogMapper;
	
	public JSONObject findEcStatistics(Map<String, Object> params) {
		JSONObject obj = new JSONObject();
		String selectType = params.get("selectType") + "";
		if("1".equals(selectType)) {
			obj.put("text", "每日新增任务数量");
		} else if("2".equals(selectType)) {
			obj.put("text", "每日解决任务数量");
		}
		List<EcStatistics> list = mapper.findEcStatistics(params);
		String[] dateTimeAry = new String[list.size()];
		Integer[] numAry = new Integer[list.size()];
		for(int i = 0; i < list.size(); i++) {
			EcStatistics es = list.get(i);
			dateTimeAry[i] = es.getDateTime();
			numAry[i] = es.getNum();
		}
		obj.put("dateTimeAry", dateTimeAry);
		obj.put("numAry", numAry);
		return obj;
	}
	
	public JSONObject findEcStatistics2(Map<String, Object> params) {
		JSONObject obj = new JSONObject();
		String selectType = params.get("selectType") + "";
		List<EcStatisticsUserOrType> list = new ArrayList<EcStatisticsUserOrType>();
		if("1".equals(selectType)) {
			obj.put("text", "按人员查看任务量");
			list = mapper.findEcStatisticsByUuser(params);
		} else if("2".equals(selectType)) {
			obj.put("text", "按任务类型查看任务量");
			list = mapper.findEcStatisticsByTaskType(params);
		}
		//Long[] eidAry = new Long[list.size()];
		//String[] snameAry = new String[list.size()];
		//Integer[] numAry = new Integer[list.size()];
		Object[] objAry = new Object[list.size()];
		for(int i = 0; i < list.size(); i++) {
			Object[] objA = new Object[2];
			EcStatisticsUserOrType es = list.get(i);
			//eidAry[i] = es.getEid();
			//snameAry[i] = es.getSname();
			//numAry[i] = es.getNum();
			objA[0] = es.getSname();
			objA[1] = es.getNum();
			objAry[i] = objA;
		}
		//obj.put("eidAry", eidAry);
		//obj.put("snameAry", snameAry);
		//obj.put("numAry", numAry);
		obj.put("objAry", objAry);
		return obj;
	}
	
	public JSONObject findEcStatistics3(Map<String, Object> params) {
		JSONObject obj = new JSONObject();
		String skus = params.get("skus") + "";
		String[] skuAry = skus.split(",");
		Object[] objAry = new Object[skuAry.length];
		int cnum = 0;
		for(int i = 0; i < skuAry.length; i++) {
			params.put("sku", skuAry[i].trim());
			List<EcStatisticsUserOrType> list = mapper.findEcStatisticsBySuppliersCommodity(params);
			Double[] dAry = new Double[list.size()];
			for(int j = 0; j < list.size(); j++) {
				EcStatisticsUserOrType esuot = list.get(j);
				dAry[j] = esuot.getPrice();
			}
			if(cnum < list.size()) {
				cnum = list.size();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", skuAry[i]);
			map.put("data", dAry);
			objAry[i] = map;
		}
		String[] textAry = new String[cnum];
		for(int k = 1; k <= cnum; k++) {
			textAry[k-1] = k + "";
		}
		obj.put("textAry", textAry);
		obj.put("objAry", objAry);
		obj.put("skuAry", skuAry);
		return obj;
	}
	
	/**
	 * sku报表
	 * @param params
	 * @return
	 */
	public List<EcStatisticsBySku> findEcStatisticsBySku(Map<String, Object> params) {
		List<EcStatisticsBySku> list = null;
		int order = Integer.parseInt(params.get("order")+"");
		if(order == 0) {
			// 最新一次价格比上一次高的价格，差距百分比最大的
			list = mapper.findEcStatisticsBySku0(params);
		} else if(order == 1) {
			// 最新更新价格的SKU排在第一，依此类推
			list = mapper.findEcStatisticsBySku1(params);
		} else if(order == 2) {
			// 按报价价格最高到最低排序
			list = mapper.findEcStatisticsBySku2(params);
		} else if(order == 3) {
			// 按最新一次价格从低到高
			list = mapper.findEcStatisticsBySku3(params);
		} else if(order == 4) {
			// 价格未更新时间降序，一直未更新SKU价格的SKU，从最近一次更新价格时间到当天间隔最久的在最上面
			list = mapper.findEcStatisticsBySku4(params);
		}
		for(int i = 0; i < list.size(); i++) {
			EcStatisticsBySku esbs = list.get(i);
			params.put("skuId", esbs.getSkuId());
			List<EcSkuCostPriceLog> elist = ecSkuCostPriceLogMapper.findEcSkuCostPriceLog(params);
			String costPriceList = "";
			for(int j = 0; j < elist.size(); j++) {
				EcSkuCostPriceLog ecSkuCostPriceLog = elist.get(j);
				if(j == 0) {
					costPriceList = ecSkuCostPriceLog.getOldCostPrice();
				} else {
					costPriceList +=","+ecSkuCostPriceLog.getOldCostPrice();
				}
			}
			esbs.setCostPriceList(costPriceList);
			Double floatProportion = 0.0;
	    	if(esbs.getFloatPrice() != null && esbs.getFloatPrice() > 0 && esbs.getSecond() != null && esbs.getSecond() > 0) {
	    		BigDecimal bg = new BigDecimal(esbs.getFloatPrice()/esbs.getSecond());
	    		floatProportion = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	}
			esbs.setFloatProportion(floatProportion);
		}
		return list;
	}
	
	/**
	 * 供应商报表
	 * @param params
	 * @return
	 */
	public List<EcStatisticsBySupp> findEcStatisticsBySupp(Map<String, Object> params) {
		List<EcStatisticsBySupp> list = null;
		int order = Integer.parseInt(params.get("order")+"");
		if(order == 0) {
			// 最新一次价格比上一次高的价格，差距百分比最大的
			list = mapper.findEcStatisticsBySupp0(params);
		} else if(order == 1) {
			// 最新更新价格的SKU排在第一，依此类推
			list = mapper.findEcStatisticsBySupp1(params);
		} else if(order == 2) {
			// 按报价价格最高到最低排序
			list = mapper.findEcStatisticsBySupp2(params);
		} else if(order == 3) {
			// 按最新一次价格从低到高
			list = mapper.findEcStatisticsBySupp3(params);
		} else if(order == 4) {
			// 价格未更新时间降序，一直未更新SKU价格的SKU，从最近一次更新价格时间到当天间隔最久的在最上面
			list = mapper.findEcStatisticsBySupp4(params);
		}
		for(int i = 0; i < list.size(); i++) {
			EcStatisticsBySupp esbs = list.get(i);
			params.put("skuId", esbs.getSkuId());
			params.put("suppcomId", esbs.getSuppcomId());
			List<EcSuppliersComPriceLog> elist = ecSuppliersComPriceLogMapper.findEcSuppliersComPriceLog(params);
			String purchasePriceList = "";
			for(int j = 0; j < elist.size(); j++) {
				EcSuppliersComPriceLog ecSuppliersComPriceLog = elist.get(j);
				if(j == 0) {
					purchasePriceList = ecSuppliersComPriceLog.getOldPurchasePrice();
				} else {
					purchasePriceList +=","+ecSuppliersComPriceLog.getOldPurchasePrice();
				}
			}
			esbs.setPurchasePriceList(purchasePriceList);
			Double floatProportion = 0.0;
	    	if(esbs.getFloatPrice() > 0 && esbs.getSecond() > 0) {
	    		BigDecimal bg = new BigDecimal(esbs.getFloatPrice()/esbs.getSecond());
	    		floatProportion = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	}
			esbs.setFloatProportion(floatProportion);
		}
		return list;
	}
	
	public List<EcStatisticsBySupp> findMainPrice(Map<String, Object> params) {
		return mapper.findMainPrice(params);
	}
}
