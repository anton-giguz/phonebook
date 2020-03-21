package phonebook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface EntryRepository extends JpaRepository<Entry, Long>, EntrySearch {
    List<Entry> findAllByOrderByIndexAsc();
    void deleteByIndexIn(List<Long> indices);
}
