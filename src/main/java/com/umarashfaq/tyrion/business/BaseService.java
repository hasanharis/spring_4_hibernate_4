package com.umarashfaq.tyrion.business;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.umarashfaq.tyrion.dao.BaseDAO;
import com.umarashfaq.tyrion.domain.BaseEntity;
import com.umarashfaq.tyrion.util.MessageBundleWrapper;

/**
 * @author Administrator
 *
 */

@Transactional
public class BaseService<T extends BaseEntity> {

	@Autowired
	protected BaseDAO<T> dao;
	
	@Autowired
	protected MessageBundleWrapper messageBundle;
	
	private Class<T> persistentClass;
	private String persistentClassSimpleName;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Find the entity of T with a given id.
	 * @param id
	 * @return
	 */
	public T findById(Serializable id) {
		if(id == null){
			return null;
		}
		return dao.findById(getPersistentClass(), id);		
	}
	
	/**
	 * Delete the entity of type T with the given Id.
	 * 
	 * @param id
	 */
	public void delete(Serializable id) {
		dao.delete(getPersistentClass(), id);		
	}
	
	public void deleteAll(Set<T> entitySet){
		if(entitySet == null){
			return;
		}
		for (T t : entitySet) {
			delete(t.getId());			
		}
	}
	
	/**
	 * Returns all entities of type T.
	 * @return
	 */
	public List<T> findAll() {
		return dao.findAll(getEntityName());	
	}
	
	public List<T> findAll(Long[] ids) {
		return dao.findAll(getEntityName(), ids);
	}
	
	/**
	 * Counts all the rows in the table for entity type T.
	 * @return count of all the entities of type T
	 */
	public Integer countAll() {
		return dao.countAll(getEntityName());		
	}
	
	public Long persist(T t){
		return (Long)this.dao.persist(t);
	}
	
	public T merge(T t){
		return this.dao.merge(t);
	}
	
	public void flush(){
		this.dao.flush();
	}
	
	public void refresh(T t){
		this.dao.refresh(t);
	}
	
	/**
	 * BaseService being parameterized generic class, this method returns the 
	 * Class that's being used as the parameter for this instance. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null) {
			Type gsc = getClass().getGenericSuperclass();
			this.persistentClass = (Class<T>) ((ParameterizedType) gsc)
					.getActualTypeArguments()[0];
		}
		return this.persistentClass;
	}
	
	/**
	 * The entity name for type T.
	 * @return
	 */
	protected String getEntityName() {
		if (this.persistentClassSimpleName == null) {
			this.persistentClassSimpleName = getPersistentClass().getSimpleName();
		}
		return this.persistentClassSimpleName;
	}
	
}
