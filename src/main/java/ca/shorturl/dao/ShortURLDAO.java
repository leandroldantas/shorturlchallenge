package ca.shorturl.dao;

import ca.shorturl.entities.ShortURL;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ShortURLDAO {

    @PersistenceContext(unitName = "shorturl")
    EntityManager entityManager;

    @Resource
    UserTransaction tx;

    public Long createNew(String url) {
        try {
            tx.begin();
        } catch (Exception e) {
            return null;
        }
        ShortURL entity = new ShortURL(url);
        entityManager.persist(entity);
        try {
            tx.commit();
            return entity.getId();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                return null;
            }
        }
        return null;
    }

    public String getLongURL(@NotNull Long id) {
        ShortURL entity = entityManager.find(ShortURL.class, id);
        return null != entity ? entity.getLongURL() : null;
    }

    public void setShortURL(Long id, String shortURL) {
        try {
            tx.begin();
            entityManager.createNamedQuery("shorturl.setShortURL")
                    .setParameter("short", shortURL)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
        }
    }

}
