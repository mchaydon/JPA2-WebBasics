package edu.wctc.jpa.web.servlet.basics.ejb;

import edu.wctc.jpa.web.servlet.basics.model.Manufacturer;
import edu.wctc.jpa.web.servlet.basics.model.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This class is an imlementation of the AbstractFacade, typed to handle
 * Product entities. It is also a Stateless EJB Session Bean. EJBs have
 * many sophisticated features, but for this demo it's the ability to
 * auto handle JTA transactions (the EJB container does this).
 * 
 * @author jlombardo
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "sample_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Product> findPurchaseCostLessThan(BigDecimal cost){
        String jpql = "select p from Product p where p.purchaseCost < ?1";
        
        TypedQuery<Product> q =
                getEntityManager().createQuery(jpql, Product.class);
        
        q.setParameter(1, cost);
        q.setMaxResults(10);
        return q.getResultList();
    }
    
    public List<Product> findByDescriptionKeyword(String keyword){
        String jpql = "select p from Product p where p.purchaseCost like ?1";
        
        TypedQuery<Product> q =
                getEntityManager().createQuery(jpql, Product.class);
        
        q.setParameter(1, keyword);
        q.setMaxResults(10);
        return q.getResultList();
    }

    public ProductFacade() {
        super(Product.class);
    }
    
}
