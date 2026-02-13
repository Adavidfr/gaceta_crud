package ec.gaceta.service;

import ec.gaceta.model.Official;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OfficialService {

    @PersistenceContext(unitName = "gacetaPU")
    private EntityManager em;

    public List<Official> findAll() {
        return em.createQuery("SELECT o FROM Official o ORDER BY o.id DESC", Official.class)
                .getResultList();
    }

    public void create(Official o) { em.persist(o); }

    public Official update(Official o) { return em.merge(o); }

    public void delete(Long id) {
        Official o = em.find(Official.class, id);
        if (o != null) em.remove(o);
    }
}
