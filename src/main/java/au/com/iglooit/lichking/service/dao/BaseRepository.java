package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.BaseEntity;
import com.google.appengine.api.datastore.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public abstract class BaseRepository<T extends BaseEntity> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseRepository.class);
    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BaseRepository(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final Long id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        Query q = entityManager.createQuery("select c from " + clazz.getName() + " c");
        return q.getResultList();

    }

    public void add(final T entity) {
        entityManager.persist(entity);
    }

    public void update(final T entity) {
        entityManager.merge(entity);
    }


    public void removeEntity(T entity) {
        LOG.info("remove the entity " + entity.toString());
        entityManager.remove(entity);
    }

    public void removeById(final Long id) {
        T entity = findOne(id);
        entityManager.remove(entity);
    }

    public T findByKey(final Key key) {
        return findOne(key.getId());
    }

    public void removeAll() {
        Query q = entityManager.createQuery("DELETE FROM " + clazz.getName() + " m");
        q.executeUpdate();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
