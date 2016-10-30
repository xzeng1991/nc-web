package repository;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	public T findOne(Serializable id);

	public List<T> loadAll();

	public void save(T entity);

	public void remove(T entity);

	public void update(T entity);
	
	public List<T> findList(T entity);
}
