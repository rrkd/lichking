package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.BaseEntity;
import com.google.appengine.api.datastore.Key;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * IGUser: nicholas.zhu
 * Date: 18/08/2014
 * Time: 11:53 AM
 */
public interface IEntityService<T extends BaseEntity> {
    T findByKey(Key key);
    T findOne(Long id);
    void removeAll();
    void removeEntity(T entity);
    List<T> findAll();
    void update(T entity);
    EntityManager getEntityManager();
}
