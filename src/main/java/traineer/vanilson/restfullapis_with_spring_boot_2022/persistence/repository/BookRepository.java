package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
