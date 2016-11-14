package db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	public T findOne(Serializable id);

	public List<T> loadAll();

	public void save(T entity);

	public void remove(T entity);

	public void update(T entity);

	public List<T> findList(T entity);

	public List<T> findByParams(Map<String, Object> params);
}
