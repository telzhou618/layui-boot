package com.github.foreyer.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * LayuiService
 * @author jameszhou
 *
 * @param <T>
 * @param <ID>
 */
public interface LayuiService<T,ID> extends CrudService<T, ID>{
	
	/**
	 * 分页模糊查询
	 * @param page
	 * @param size
	 * @param t
	 * @return
	 */
	Page<T> page(int page, int size,T t) ;
	/**
	 * 分页模糊查询
	 * @param page
	 * @param size
	 * @param sort
	 * @param t
	 * @return
	 */
	Page<T> page(int page, int size,Sort sort,T t) ;
	
	/**
	 * 根据属性获取对象列表
	 * @param t
	 * @return
	 */
	List<T> findByExample(T t);
}
