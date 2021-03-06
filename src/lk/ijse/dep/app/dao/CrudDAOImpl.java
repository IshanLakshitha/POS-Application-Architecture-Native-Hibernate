package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    private Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }


    public void setSession(Session session){
        this.session = session;
    }

    public void save(T entity) throws Exception{
        session.save(entity);
    }

    public void update(T entity) throws Exception{
        session.update(entity);
    }

    public void delete(ID key) throws Exception{
        session.delete(session.load(entity,key));
    }

    public Optional<T> find(ID key) throws Exception {
        return Optional.ofNullable(session.find(entity, key));
    }

    public Optional<List<T>> findAll() throws Exception{
        return Optional.ofNullable(session.createQuery("FROM" + entity.getName()).list());
    }

}
