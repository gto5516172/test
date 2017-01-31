package com.gohuinuo.common.excel;

import java.io.File;
import java.util.List;


public class EasyXls {
	/**
	 * 读取excel到list
	 * @param xmlPath
	 * @param xlsFile
	 * @return
	 * @throws Exception
	 */
	public static List<?> xls2List(String xmlPath,File xlsFile) throws Exception{
		return ImpXls2ListObj.impXls2ListObj(xmlPath,xlsFile);
	}
	
	/**
	 * 写入list到xls文件
	 * @param list
	 * @param xmlPath
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean list2Xls(List<Object> list,String xmlPath,String filePath,String fileName) throws Exception{
		return ExpListObj2Xls.expListObjFile(list, xmlPath, filePath, fileName);
	}
}
