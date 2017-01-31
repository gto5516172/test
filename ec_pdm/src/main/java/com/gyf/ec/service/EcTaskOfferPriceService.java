package com.gyf.ec.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcTaskOfferPriceMapper;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.model.EcSuppliers;
import com.gyf.ec.model.EcTaskDo;
import com.gyf.ec.model.EcTaskOfferPrice;
@Service("ecTaskOfferPriceService")
public class EcTaskOfferPriceService extends ServiceMybatis<EcTaskOfferPrice> {
	private static int dayCount = 0;
	private static String today = "";
	@Resource
	private EcTaskDoService doService;
	@Resource
	private EcTaskProductService productService;
	
	@Resource
	private EcSuppliersService ecSuppliersService;
	@Resource
	private EcSkuService skuService;
	@Resource
	private EcTaskOfferPriceMapper mapper;
	static{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.format(new Date());
	}

	public synchronized int generateCount(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String mytoday = sdf.format(new Date());
		if(mytoday.equals(today)){
			dayCount=dayCount+1;
		}else{
			today = mytoday;
			dayCount =1;
		}
		return dayCount;
	}
	public String generateOfferNo(){
		
		int count = generateCount();
		 Random r = new Random();
		int random = r.nextInt(1000) + 9999;
		String no="";
		if(count<10)
		no="XJ-"+today+"-"+random+"-0"+count;
		else
		no="XJ-"+today+"-"+random+"-"+count;
		return no;
	}
	public EcSku getSku(long productId){
		return skuService.selectByPrimaryKeyMy(productId);
	}
	public EcTaskDo queryTaskDoById(Long pid){
		return doService.selectByPrimaryKey(pid);
	}
	public List<EcTaskOfferPrice> queryByProductId(Long productId){
		return mapper.queryByProductId(productId);
	}
	public JSONObject delete(Long id){
		JSONObject obj = new JSONObject();
		int delStatus=mapper.deleteByPrimaryKey(id);
		if(delStatus>0){
			obj.put("status", "0");
			obj.put("message", "修改成功");
		}else{
			obj.put("status", "-1");
			obj.put("message", "修改失败");
			obj.put("doId", "0");
		}
		return obj;
	}
	public int save(EcTaskOfferPrice ep,Long pid,Long taskId){
		if(ep.getDoId()==null){
			EcTaskDo taskDo = new EcTaskDo();
			taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
			taskDo.setPid(pid);
			taskDo.setTaskId(taskId);
			doService.insert(taskDo);
			ep.setDoId(taskDo.getId());
		}
		int saveStatus = save(ep);
		return saveStatus;
	}
	public JSONObject compare(Long id){
		EcTaskOfferPrice eof = mapper.selectByPrimaryKey(id);
		JSONObject obj = new JSONObject();
		if(eof!=null){
			EcSuppliers es = new EcSuppliers();
			es.setSuppliersName(eof.getCompanyName());
			es.setPersonName1(eof.getContactName());
			es.setPersonPhone1(eof.getPhone());
			es.setFaxNumber(eof.getFaxArea()+eof.getFaxNo());
			es.setQq(eof.getContactQq());
			es.setEmail(eof.getEmail());
			es.setAreaId(eof.getAreaId());
			es.setSuppliersAddr(eof.getBusinessAddress());
			es.setSuppliersType(1);
			ecSuppliersService.save(es);
			eof.setIsCompare(1);
			mapper.updateByPrimaryKey(eof);
			obj.put("status", "0");
			obj.put("message", "操作成功");
		}else{
			obj.put("status", "-1");
			obj.put("message", "对象不存在，操作失败");
		}
		return obj;
	}
}
