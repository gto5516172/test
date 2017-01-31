package com.gohuinuo.common.excel;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jxl.Sheet;
import jxl.Workbook;

/**
 * 导入excel到listobj
 * @author liuzh
 *
 */
public class ImpXls2ListObj {
	
	public final static String EXCEL = ".xls";
	
	private static XmlConfig xmlConfig = null;
	
	private static void createXmlConfig(){
		if(xmlConfig==null){
			xmlConfig = new XmlConfig();
		}
	}
	
	
	public static List<?> impXls2ListObj(String xmlPath,File xlsFile) throws Exception {
		createXmlConfig();
		
		List<Object> list = new ArrayList<Object>();
		try {
			//获取配置文件
			DlExcel config = xmlConfig.getXmlConfig(xmlPath);
			String[] names = config.getNames();
			String[] types = config.getTypes();
			
			Workbook wb = null;
			Sheet sheet = null;
			//**********读取EXCEL*********************************
			wb = Workbook.getWorkbook(xlsFile);
			
			sheet = wb.getSheet(config.getSheetNum());
			
			Object obj = null;//
			for(int i=config.getStartRow();i<sheet.getRows();i++){
				obj = Class.forName(config.getClazz()).newInstance();
				for(int j=0;j<sheet.getColumns();j++){
					setValue(obj, names[j], types[j], sheet.getCell(j, i).getContents());
				}
				list.add(obj);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}
		return list;
	}
	/**
	 * 跟对象obj的某个field赋值value
	 * @param obj
	 * @param field
	 * @param value
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setValue(Object obj,String field,String type,String value) throws Exception{
		Object val = null;
		/**
		 * 对类型进行转换，支持int,long,float,double,boolean,Integer,Long,Double,Float,Date,String
		 */
		Class clazz = null;
		if(type.equals("int")){
			clazz = int.class;
			val = Integer.parseInt(value);
		}
		else if(type.equals("long")){
			clazz = long.class;
			val = Long.parseLong(value);
		}
		else if(type.equals("float")){
			clazz = float.class;
			val = Float.parseFloat(value);
		}
		else if(type.equals("double")){
			clazz = double.class;
			val = Double.parseDouble(value);
		}
		else if(type.equals("boolean")){
			clazz = boolean.class;
			val = Boolean.parseBoolean(value);
		}
		else {
			clazz = Class.forName(type);
			if(!clazz.equals(String.class)){
				if(clazz.equals(Date.class)){
					val = DateUtilStrict.stringToDate(value, DateUtilStrict.NORMAL_DATE_FORMAT);
				}
				else if(clazz.equals(Integer.class)){
					if("".equals(value)) {
						val = null;
					} else {
						val = Integer.valueOf(value);
					}
				}
				else if(clazz.equals(Long.class)){
					if("".equals(value)) {
						val = null;
					} else {
						val = Long.valueOf(value);
					}
				}
				else if(clazz.equals(Float.class)){
					if("".equals(value)) {
						val = null;
					} else {
						val = Float.valueOf(value);
					}
				}
				else if(clazz.equals(Double.class)){
					if("".equals(value)) {
						val = null;
					} else {
						val = Double.valueOf(value);
					}
				}
				else if(clazz.equals(Boolean.class)){
					if("".equals(value)) {
						val = null;
					} else {
						val = Boolean.valueOf(value);
					}
				}
				else if(clazz.equals(BigDecimal.class)){
					val = new BigDecimal(value);
				}
			}
			else {
				val = value;
			}
		}
		Method method = null;
		
		String setMethod = getSetMethod(field);
		Class objClass = obj.getClass();
		try {
			method = objClass.getDeclaredMethod(setMethod, clazz);
		} catch (Exception e) {
			method = null;
		}
		
		int count = 0;
		while(method==null&&count<=3){
			if(objClass.getSuperclass()!=null){
				Class superClass = objClass.getSuperclass();
				try {
					method = superClass.getDeclaredMethod(setMethod, clazz);
				} catch (Exception e) {
					method = null;
				}
				if(method==null){
					objClass = superClass;
				}
				count++;
			}
			else {
				break;
			}
		}
		method.invoke(obj, val);
	}
	/**
	 * 获取field的set方法
	 * @param field
	 * @return
	 */
	public static String getSetMethod(String field){
		field = field.substring(0,1).toUpperCase()+field.substring(1);
		return "set"+field;
	}
}
