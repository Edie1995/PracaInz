package pl.kruko.PracaInz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.kruko.PracaInz.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByLogin(String login);
}
