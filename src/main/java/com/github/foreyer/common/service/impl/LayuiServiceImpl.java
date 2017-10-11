package com.github.foreyer.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.foreyer.common.service.LayuiService;

/**
 * 基础CRUD service
 * 
 * @author jameszhou
 *
 * @param <R>
 *            持久化对象
 * @param <T>
 *            实体
 * @param <ID>
 *            主键
 * 
 * 
 */
public abstract class LayuiServiceImpl<R extends JpaRepository<T, ID>, T, ID extends Serializable>
		extends CrudServiceImpl<JpaRepository<T, ID>, T, ID> implements LayuiService<T, ID> {

	@Autowired
	private R r;

	@Override
	public Page<T> page(int page, int size, T t) {
		// TODO Auto-generated method stub
		return this.page(page, size, null, t);
	}

	@Override
	public Page<T> page(int page, int size, Sort sort, T t) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page - 1, size);
		if(sort != null){
			new PageRequest(page - 1, size,sort);
		}
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase(true)
				.withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING); //字符串忽略大小写模糊查询
		Example<T> example = Example.of(t, matcher);
		return r.findAll(example, pageable);
	}

	@Override
	public List<T> findByExample(T t) {
		// TODO Auto-generated method stub
		
		Example<T> example = Example.of(t);
		return r.findAll(example);
	}

	
}
