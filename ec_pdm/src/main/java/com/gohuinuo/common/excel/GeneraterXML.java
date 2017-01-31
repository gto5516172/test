package com.gohuinuo.common.excel;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;

public class GeneraterXML {
	//对类进行解析
	//com.isea.easyxls.test.TestObj.java
	public void test1() throws Exception{
		Object obj = Class.forName("com.isea.easyxls.test.TestObj").newInstance();
		PropertyDescriptor[]  ps = PropertyUtils.getPropertyDescriptors(obj);
		for(int i=0;i<ps.length;i++){
			System.out.println(ps[i].getName()+","+ps[i].getPropertyType()+","+ps[i].getShortDescription()+","+ps[i].getPropertyEditorClass()+","+ps[i].getClass());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new GeneraterXML().test1();
	}
}
