package phonebook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface EntryRepository extends JpaRepository<Entry, Long> {
    public List<Entry> findAllByOrderByIndexAsc();
    void deleteByIndexIn(List<Long> indices);
}
