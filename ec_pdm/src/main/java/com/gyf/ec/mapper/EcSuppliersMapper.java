package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcSuppliers;

public interface EcSuppliersMapper extends Mapper<EcSuppliers> {
	
	
	public List<EcSuppliers> findPageInfo(Map<String, Object> params);
	
	public int updateStatus(EcSuppliers ecSuppliers);
	
	public List<EcSuppliers> findAllSuppliers();
	
	public int querySuppliersRelation(Map<String, Object> params);
	
}
