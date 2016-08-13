/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class DAO<T extends IGenericEntity<T>>{

	private final Class<T> clazz;
	
	public DAO(Class<T> clazz) {
		this.clazz = clazz;   //(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T add(T entity) throws DAOException {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	 public List<T> add(List<T> list) throws DAOException{
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (T t : list) {
				entityManager.persist(t);
			}
			entityManager.getTransaction().commit();
			return list;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }

	 
	@SuppressWarnings("unchecked")
	public void add(T ...entitys) throws DAOException{
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (T iGenericEntity : entitys) {
				entityManager.persist(iGenericEntity);
			}
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }
	 
	public boolean remove(Integer id) throws DAOException {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.remove(findOne(id));
			entityManager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	public boolean remove(T entity) throws DAOException {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			T entityToBeRemoved = entityManager.merge(entity);
			entityManager.remove(entityToBeRemoved);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	public T update(T entity) throws DAOException {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	@SuppressWarnings("hiding")
	public <T> List<T> findByQuery(String select, Object... params){
		return findByQuery(0, select, params);
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> findByQuery(int maxResult, String select, Object... params) {
		EntityManager entityManager = createEntityManager();
		try {
			return createQuery(entityManager, clazz, maxResult, select, params).getResultList();
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> findByNativeQuery(String select, Object... parametros) {
		return findByNativeQuery(0, select, parametros);
	}
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> findByNativeQuery(int maxResult, String select, Object... params) {
		EntityManager entityManager = createEntityManager();
		try {
			return createNativeQuery(entityManager, clazz, maxResult, select, params).getResultList();
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T findOneByQuery(String select, Object... params) {
		EntityManager entityManager = createEntityManager();
		try {
			return (T) createQuery(entityManager, clazz, 0, select, params).getSingleResult();
		}finally{
			entityManager.close();
		}
	}

	public T findOne(Serializable pk) {
		EntityManager entityManager = createEntityManager();
		try {
			return entityManager.find(clazz, pk);
		}finally{
			entityManager.close();
		}
	}
	
	public List<T> findAll() {
		return findByQuery("SELECT x FROM "+clazz.getSimpleName()+" x");
	}
	
	public Long countAll(){
		EntityManager entityManager = createEntityManager();
		try{
			return (Long) entityManager.createQuery("SELECT COUNT(*) FROM "+clazz.getSimpleName()).getSingleResult();
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Query createQuery(EntityManager entityManager, Class clazz, Integer maxResult, String select, Object... params){
		Query query = entityManager.createQuery(select, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		if(maxResult != 0){
			query.setMaxResults(maxResult);
		}
		return query;
	}
	
	@SuppressWarnings("rawtypes")
	public Query createNativeQuery(EntityManager entityManager, Class clazz, Integer maxResult, String select, Object...params){
		Query query = entityManager.createNativeQuery(select, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		if(maxResult != 0){
			query.setMaxResults(maxResult);
		}
		return query;
	}
	
	public EntityManager createEntityManager() {
		return GenericJPA.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	static class GenericJPA implements Serializable{
		private static final long serialVersionUID = -8732971614557917884L;

		private static GenericJPA jpa;
		private static EntityManagerFactory entityManagerFactory;

		private GenericJPA() {
			if (entityManagerFactory == null) {
				entityManagerFactory = Persistence.createEntityManagerFactory("ifce_estagio_postgree"); 
			}
		}

		public static GenericJPA getInstance() {
			if (jpa == null) {
				jpa = new GenericJPA();
			}
			return jpa;
		}

		public EntityManagerFactory getEntityManagerFactory() {
			return entityManagerFactory;
		}
	}
}


