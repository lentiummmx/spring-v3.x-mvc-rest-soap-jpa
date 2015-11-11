/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;

/**
 * @author lentiummmx
 *
 */
public interface UserMapper {

	@Select("SELECT user_id as id, username, password, first_name, last_name, email, birth_date FROM Users WHERE user_id = #{userId}")
	User getUserById(@Param("userId") Integer userId);
	
}
