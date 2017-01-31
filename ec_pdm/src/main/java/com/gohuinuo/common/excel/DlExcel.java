package com.gohuinuo.common.excel;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * xml对象
 * @author liuzh
 */
@XStreamAlias("excel")
public class DlExcel {
	private String title;
	private String description;
	private String author;
	private String sheet;
	@XStreamAlias("class")
	private String clazz;
	
	private int sheetNum;
	
	private int startRow;
	
	@XStreamAlias("columns")
	private List<DlColumn> columns;
	
	
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSheet() {
		return sheet;
	}
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	public List<DlColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DlColumn> columns) {
		this.columns = columns;
	}
	/**
	 * 获取excel标题
	 * @return
	 */
	public String[] getHeaders(){
		String[] headers = new String[columns.size()];
		DlColumn column = null;
		for(int i=0;i<columns.size();i++){
			column = columns.get(i);
			if(column.getHeader()!=null&&!column.getHeader().equals("")){
				headers[i] = columns.get(i).getHeader();
			}
			else {
				headers[i] = column.getName();
			}
		}
		return headers;
	}
	
	/**
	 * 获取Object要输出的字段名
	 * @return
	 */
	public String[] getNames(){
		String[] headers = new String[columns.size()];
		for(int i=0;i<columns.size();i++){
			headers[i] = columns.get(i).getName();
		}
		return headers;
	}
	
	/**
	 * 获取Object的字段类型
	 * @return
	 */
	public String[] getTypes(){
		String[] types = new String[columns.size()];
		for(int i=0;i<columns.size();i++){
			types[i] = columns.get(i).getType();
		}
		return types;
	}
	
	
	
}
