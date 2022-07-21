package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.userName FROM User u WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
