package dao;

import domain.Role;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: wangyan
 * @Date: 2019/1/8
 * @Description: dao
 * @version: 1.0
 */
public interface RoleDao extends JpaRepository<Role,Integer>,JpaSpecificationExecutor<Role> {
//    default User findById(){
//        User user = new User();
//        user.setUsername("wangyan");
//        return user;
//    }



}
