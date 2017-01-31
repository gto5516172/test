package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.ImportExcel;
import com.github.abel533.mapper.Mapper;

public interface ImportExcelMapper extends Mapper<ImportExcel>{
	public List<ImportExcel> findPageInfo(Map<String, Object> params);
	public Long insertExcelBatch(List<ImportExcel> list);
}
