package banco.utils;

import java.util.List;

public interface MecasoftUtils<T> extends TFEUtils{
	
	void saveOrUpdate(T modelo);
	void delete(T modelo);
	T find(Long id);
	List<T> findAll();

}
