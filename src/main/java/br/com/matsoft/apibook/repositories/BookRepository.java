package br.com.matsoft.apibook.repositories;

import br.com.matsoft.apibook.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("UPDATE Book b SET b.enabled = false WHERE b.id =:id")
    void disableBook(@Param("id") Long id);
}
