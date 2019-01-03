package dao;

import domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2019/1/3
 * @Description: dao
 * @version: 1.0
 */
public interface UserCrudRepository extends CrudRepository<User,Integer>{

}
