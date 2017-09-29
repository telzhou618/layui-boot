package com.github.foreyer.common.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thymeleaf.util.ArrayUtils;

import com.github.foreyer.common.service.CrudService;

/**
 *  基础CRUD service
 * @author jameszhou
 *
 * @param <R>	持久化对象
 * @param <T>	实体
 * @param <ID>	主键
 * 
 * 		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
	    Page<User> pageData = userRepository.findAll(pageable);
 * 
 */
public abstract class CrudServiceImpl<R extends JpaRepository<T, ID>, T, ID extends Serializable> implements CrudService<T, ID>{

	@Autowired private R r;
	
	@Transactional
	public void save(T t) {
		r.save(t);
	}

	@Transactional
	public void updateById(T t,ID id) {
		T rt = r.findOne(id);
		Field[] fields = rt.getClass().getDeclaredFields();
		for(Field field : fields){
			String name = field.getName();
			if(name.equalsIgnoreCase("serialVersionUID")){ //忽略serialVersionUID字段
				continue;
			}
			 Object value;
			try {
				value = PropertyUtils.getProperty(t, name);
				PropertyUtils.setProperty(rt, name,value);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			}
			
		}
	}
	
	@Transactional
	public void delete(ID id) {
		r.delete(id);
	}
	
	@Transactional
	public void delete(ID[] ids) {
		if(ArrayUtils.isEmpty(ids)){
			throw new RuntimeException(" delete ids is empty ");
		}
		for(ID id : ids){
			r.delete(id);
		}
	}
	
	public T findOne(ID id){
		return r.findOne(id);
	}
	
	public Page<T> page(int page, int size) {
		
		Pageable pageable = new PageRequest(page-1, size);
		return r.findAll(pageable);
		
	}
}
