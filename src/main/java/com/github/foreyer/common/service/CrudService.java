package com.github.foreyer.common.service;

import org.springframework.data.domain.Page;

/**
 *  CRUD 基础服务
 * @author jameszhou
 *
 * @param <T>	实体
 * @param <ID>	主键
 */
public interface CrudService<T,ID> {
	
	/**
	 * 保存实体
	 * @param t
	 */
	void save(T t);
	
	/**
	 * 更新实体
	 * @param t
	 */
	void updateById(T t,ID id) ;
	
	/**
	 * 删除一个实体
	 * @param id
	 */
	void delete(ID id);
	
	/**
	 * 批量删除实体
	 * @param ids
	 */
	void delete(ID[] ids);
	
	/**
	 * 查询获取一个实体
	 * @param id
	 * @return
	 */
	T findOne(ID id);
	
	/**
	 * 分页查询实体
	 * @param page
	 * @param size
	 * @return
	 */
	Page<T> page(int page, int size) ;
	
}
