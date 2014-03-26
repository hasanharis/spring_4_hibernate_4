package com.umarashfaq.tyrion.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.umarashfaq.tyrion.domain.BaseEntity;



/**
 * This class is the base dao for the application. It provides the generic methods 
 * for interacting with database for CRUD operations.
 * 
 * @author Administrator
 */

@Repository
public class BaseDAO<T extends BaseEntity> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String NULL_ID_MESSAGE = "Id cannot be null.";

	@PersistenceContext
	public EntityManager em;

	/**
	 * Find the entity of T with a given id.
	 * @param id
	 * @return
	 */
	public T findById(Class<T> persistanceClass, Serializable id) {
		if (id == null) {
			throw new IllegalArgumentException(NULL_ID_MESSAGE);
		}
		return em.find(persistanceClass, id);
	}

	/**
	 * Delete the given entity.
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot delete null entity.");
		}
		em.remove(entity);
	}

	/**
	 * Delete the entity of type T with the given Id.
	 * 
	 * @param id
	 */
	public void delete(Class<T> persistanceClass, Serializable id) {
		if (id == null) {
			throw new IllegalArgumentException(NULL_ID_MESSAGE);
		}
		em.remove(em.find(persistanceClass, id));
	}
	
	public void delete(String entityName, Serializable id) {
		if (id == null) {
			throw new IllegalArgumentException(NULL_ID_MESSAGE);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ")
			.append(entityName)
			.append(" where id = ")
			.append(id);
		
		em.createQuery(sb.toString())
			.executeUpdate();
	}

	/**
	 * Returns all entities of type T.
	 * @return
	 */
	public List<T> findAll(String entityName) {
		String sql = "select b from " + entityName + " b";		
		return getResultList(sql);
	}
	
	public List<T> findAll(String entityName, Long[] ids) {
		StringBuilder sql = new StringBuilder("select b from ");
			sql.append(entityName)
				.append(" b where id in ( ");		
		int i = 0;
		for (Long id : ids) {
			if ( i++ > 0 ) {
				sql.append(",");
			}
			sql.append(id);
		}	
		sql.append(")");
		String _sql = sql.toString();
		logger.trace("Executing query: "+_sql);
		return getResultList(_sql);
	}

	/**
	 * Add a new entity.
	 * @param entity
	 * @return
	 */
	public Serializable persist(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot persist null entity.");
		}
		em.persist(entity);
		return entity.getId();
	}
	
	/**
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return
	 */
	
	public int executeUpdateNamedQuery(String namedQuery, Object []parameters) {	
		try{
			return executeUpdateNamedQuery(namedQuery, parameters, null);
		} catch(NoResultException e){
			return 0;
		}
	}
	
	/**
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @param parametersName
	 * @return
	 */
	
	public int executeUpdateNamedQuery(String namedQuery, Object []parameters, Object[]parametersName) {
		try{
			return getPreparedNamedQuery(namedQuery, parameters, parametersName).executeUpdate();
		} catch(NoResultException e){
			return 0;
		}		
	}

	/**
	 * Result list for a JPQL query.  
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getResultList(String query) {
		try{
			return em.createQuery(query).getResultList();
		} catch(NoResultException e){
			return new ArrayList<T>();
		}
	}
	
	/**
	 * A single result, like count, max or a single row/column combo etc., for a native SQL query.   
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getSingleResult(String sqlQuery) {
		try{
			return (T)em.createQuery(sqlQuery).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	/***************************************************************************************************
	 * 
	 * @param namedQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getSingleNamedQueryResult(String namedQuery) {
		try{
			return (T)em.createNamedQuery(namedQuery).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	

	public Object getSingleNamedQueryScalerResult(String namedQuery) {
		try{
			return em.createNamedQuery(namedQuery).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	/****************************************************************************************************
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return
	 */
	public T getSingleNamedQueryResult(String namedQuery, Object []parameters) {	
		try{
			return getSingleNamedQueryResult(namedQuery, parameters, null);
		} catch(NoResultException e){
			return null;
		}
	}
	
	public Object getSingleNamedQueryScalerResult(String namedQuery, Object []parameters) {	
		try{
			return getSingleNamedQueryScalerResult(namedQuery, parameters, null);
		} catch(NoResultException e){
			return null;
		}
	}
	
	/*****************************************************************************************************
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return
	 */
	public List<T> getNamedQueryResultList(String namedQuery, Object []parameters) {
		try{
			return getNamedQueryResultList(namedQuery, parameters, null);
		} catch(NoResultException exception){
			return new ArrayList<T>();
		}
	}
	
	public List<? extends Object> getNamedQueryScalerResultList(String namedQuery, Object []parameters) {
		try{
			return getNamedQueryScalerResultList(namedQuery, parameters, null);
		} catch(NoResultException exception){
			return null;
		}
	}
	
	/****************************************************************************************************
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getSingleNamedQueryResult(String namedQuery, Object []parameters, Object[]parametersName) {
		try{
			return (T)getPreparedNamedQuery(namedQuery, parameters, parametersName).getSingleResult();
		} catch(NoResultException e){
			return null;
		}		
	}
	
	public Object getSingleNamedQueryScalerResult(String namedQuery, Object []parameters, Object[]parametersName) {
		try{
			return getPreparedNamedQuery(namedQuery, parameters, parametersName).getSingleResult();
		} catch(NoResultException e){
			return null;
		}		
	}
	
	/*****************************************************************************************************
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getNamedQueryResultList(String namedQuery, Object []parameters, Object[]parametersName) {
		try{
			return getPreparedNamedQuery(namedQuery, parameters, parametersName).getResultList();
		} catch(NoResultException exception){
			return new ArrayList<T>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends Object> getNamedQueryScalerResultList(String namedQuery, Object []parameters, Object[]parametersName) {
		try{
			return getPreparedNamedQuery(namedQuery, parameters, parametersName).getResultList();
		} catch(NoResultException exception){
			return null;
		}
	}
	
	/*****************************************************************************************************
	 * 
	 * @param namedQueryName
	 * @param parameters
	 * @return
	 */
	private Query getPreparedNamedQuery(String namedQueryName, Object[] parameters, Object[]parametersName) {
		
		if(parametersName != null && parameters != null && parametersName.length != parameters.length) {
			throw new RuntimeException("length of parameters and parameters name array are not equals. parameters = " + parameters.length + " -- parametersName = " + parametersName.length); 
		}
		
		Query namedQuery = em.createNamedQuery(namedQueryName);
		 
		 if(parameters != null && parameters.length > 0){
			 for (int i=1; i <= parameters.length; i++) {
				 if(parametersName == null) {
					 namedQuery.setParameter(i, parameters[i-1]);
				 } else {
					 namedQuery.setParameter(String.valueOf(parametersName[i-1]), parameters[i-1]);
				 }
			 }
		 }
		 
		 return namedQuery;
	}
	
	/**
	 * Counts all the rows in the table for entity type T.
	 * @return count of all the entities of type T
	 */
	public Integer countAll(String entityName) {
		String sql = "select count(o) from " + entityName + " o";
		return (Integer) em.createQuery(sql).getSingleResult();
	}

	/**
	 * Refresh the state of the instance from the database, overwriting changes made to the entity, if any. 
	 * @param entity
	 */
	public void refresh(T entity) {
		em.refresh(entity);
	}

	/**
	 * Merge the state of the given entity into the current persistence context.
	 * <br><br>Simply delegates to merge(T entity) method in this class.
	 * @see #merge 
	 * @param entity
	 * @return the updated entity instance
	 */
	public T update(T entity) {
		return this.merge(entity);
	}
	
	/**
	 * Merge the state of the given entity into the current persistence context.
	 * @see #update
	 * @param entity
	 * @return The updated entity instance. The state of the passed entity is not changed; 
	 * that is, it doesn't become managed. 
	 */
	public T merge(T entity) {
		return em.merge(entity);
	}
	
	/**
	 * Detach the state of the given entity from the current persistence context.
	 * @see #update
	 * @param entity
	 * @return The detached entity instance. 
	 */
	public void detach(T entity) {
		em.detach(entity);
	}
	
	public void flush() {
		em.flush();
	}
	
	/************************************************************************************************
	 * 
	 */
	
	public EntityManager getEntityManager(){
		return em;
	}
}