package com.gohuinuo.common.base;

import java.io.File;

/**
 * UploadFile.
 */
public class UploadFile {

	private String parameterName;  //参数名称,表单name值
	private String saveDirectory; //保存路径
	private String fileName;  //文件名字
	private String originalFileName;  //原文件名称
	private String contentType; //类型
	private String relativeUrl; // 相对路径
	private String suffix; // 后缀

	public UploadFile(String parameterName, String saveDirectory, String fileName, String originalFileName,
			String contentType) {
		this.parameterName = parameterName;
		this.saveDirectory = saveDirectory;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
		this.contentType = contentType;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}
	
	public String getParameterName() {
		return parameterName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public String getSaveDirectory() {
		return saveDirectory;
	}

	public File getFile() {
		if (saveDirectory == null || fileName == null) {
			return null;
		} else {
			return new File(saveDirectory + File.separator + fileName);
		}
	}
}
