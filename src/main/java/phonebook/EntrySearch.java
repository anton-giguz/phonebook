package phonebook;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

interface EntrySearch {
    List<Entry> search(Entry entry);
}

class EntrySearchImpl implements EntrySearch {

    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;
    private Predicate pt;
    private CriteriaQuery<Entry> cq;
    private Root<Entry> root;

    private void init() {
        cb   = em.getCriteriaBuilder();
        pt   = cb.isTrue(cb.literal(true));
        cq   = cb.createQuery(Entry.class);
        root = cq.from(Entry.class);
    }

    public List<Entry> search(Entry entry) {
        init();

        String lastName  = entry.getLastName();
        String firstName = entry.getFirstName();
        String phone     = entry.getPhone();

        Predicate p1 = lastName .equals("") ? pt : cb.equal(root.get("lastName"),  lastName);
        Predicate p2 = firstName.equals("") ? pt : cb.equal(root.get("firstName"), firstName);
        Predicate p3 = phone    .equals("") ? pt : cb.equal(root.get("phone"),     phone);

        cq.select(root).where(p1, p2, p3).orderBy(cb.asc(root.get("index")));

        TypedQuery<Entry> tq = em.createQuery(cq);
        return tq.getResultList();
    }

}
