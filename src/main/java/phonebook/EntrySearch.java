package phonebook;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;

interface EntrySearch {
    List<Entry> search(Entry entry);
}

class EntrySearchImpl implements EntrySearch {

    @PersistenceContext
    private EntityManager em;

    public List<Entry> search(Entry entry) {
        CriteriaBuilder cb     = em.getCriteriaBuilder();
        CriteriaQuery<Entry> q = cb.createQuery(Entry.class);
        Root<Entry> e          = q.from(Entry.class);

        Predicate t  = cb.isTrue(cb.literal(true));
        Predicate p1 = entry.getLastName() .equals("") ? t : cb.equal(e.get("lastName"),  entry.getLastName());
        Predicate p2 = entry.getFirstName().equals("") ? t : cb.equal(e.get("firstName"), entry.getFirstName());
        Predicate p3 = entry.getPhone()    .equals("") ? t : cb.equal(e.get("phone"),     entry.getPhone());

        q.select(e).where(p1, p2, p3);
        q.orderBy(cb.asc(e.get("index")));
        return em.createQuery(q).getResultList();
    }

}
