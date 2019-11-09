package pl.kruko.PracaInz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.service.UserPrincipal;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByLogin(String login);
}
