

package com.bety.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.bety.web.mapper.CommoditySpuDescMapper;
import com.bety.web.model.CommoditySpuDesc;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

import gui.ava.html.image.generator.HtmlImageGenerator;

//import gui.ava.html.image.generator.HtmlImageGenerator;

/**
 * 
 * @author BetyChen
 */

@Service("commoditySpuServiceDesc")
public class CommoditySpuDescService extends ServiceMybatis<CommoditySpuDesc>{

	@Resource
	private CommoditySpuDescMapper commoditySpuDescMapper;
	/**
	 * 添加或更�
	* @param 
	* @return
	 */
	public Long saveSpuDesc(CommoditySpuDesc obj,HttpServletRequest request){
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		/*try {
			imageGenerator.loadHtml(URLDecoder.decode(obj.getDescr(),"Utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String image = "file/html_to_image/"+System.currentTimeMillis()+".png";
		imageGenerator.saveAsImage(request.getSession().getServletContext().getRealPath("/")+image);
		obj.setImage(image);
		int count = 0;
		if(null == obj.getId()){
 			count = this.insertSelective(obj);
		}else{
			count = this.updateByPrimaryKeySelective(obj);
		}*/
	    return obj.getId();
	}
	
	/**
	 * 删除
	* @param userId
	* @return
	 */
	public int deleteSpuDesc(Long userId){
		return this.updateDelFlagToDelStatusById(CommoditySpuDesc.class, userId);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<CommoditySpuDesc> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		
		List<CommoditySpuDesc> list = commoditySpuDescMapper.findPageInfo(params);
		return new PageInfo<CommoditySpuDesc>(list);
	}
	
	public List<CommoditySpuDesc> findPageInfo1(Map<String, Object> params) {
		  PageHelper.startPage(params);
		  List<CommoditySpuDesc> list=commoditySpuDescMapper.findPageInfo(params);
		  return list;
	}
	public CommoditySpuDesc findById(Long id){
		return commoditySpuDescMapper.selectByPrimaryKey(id);
	}
}
