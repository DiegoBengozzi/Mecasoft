package banco.DAO;

import java.util.List;

import org.hibernate.Query;

import banco.connection.HibernateConnection2;
import banco.modelo.TipoFuncionario;
import banco.utils.TipoFuncionarioUtils;

public class TipoFuncionarioDAO extends HibernateConnection2 implements TipoFuncionarioUtils{

	@Override
	public void saveOrUpdate(TipoFuncionario modelo) {
		if(modelo.getId() != null)
			getSession().merge(modelo);
		else
			getSession().persist(modelo);
	}

	@Override
	public void delete(TipoFuncionario modelo) {
		getSession().delete(modelo);
	}

	@Override
	public TipoFuncionario find(Long id) {
		Query q = getSession().createQuery("select t from TipoFuncionario t where t.id = :id");
		q.setParameter("id", id);
		return (TipoFuncionario)q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFuncionario> findAll() {
//		Query q = createQueryClearSession("select t from TipoFuncionario t");
		Query q = getSession().createQuery("select t from TipoFuncionario t");
		return q.list();
	}

}
