/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;

/**
 * @author lentiummmx
 *
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom<User, Long> {

	User findByUsername(String username);
	
	User findById(Long id);
	
}
