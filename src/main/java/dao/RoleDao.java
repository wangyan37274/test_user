package dao;

import domain.User;

/**
 * @Auther: wangyan
 * @Date: 2019/1/8
 * @Description: dao
 * @version: 1.0
 */
public interface RoleDao {
    default User findById(){
        User user = new User();
        user.setUsername("wangyan");
        return user;
    }

}
