package dao;

import domain.User;
import org.junit.Before;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2019/1/2
 * @Description: dao
 * @version: 1.0
 */
public interface UserJpaRepository extends JpaRepository<User,Integer> {

    /**
     * spring-data-jpa会根据方法的名字来自动生成sql语句，所以，我们必须按照规则定义方法名
     * 比如findByUsername：select * from user where username = ?;
     */
    User findByUsername(String name);

    /**
     * 使用注解自己定义sql
     * jpa风格:jpql
     */
    @Query("select u from User u where u.username = :username")
    List<User> queryListByName(@Param("username") String username);

    /**
     *  本地sql：nativeQuery = true
     */
    @Query(nativeQuery = true,value = "select * from user u where u.username = :username")
    List<User> queryListByName2(@Param("username") String username);

    /**
     *  使用对象传递参数
     *  SPEL表达式：#{#user.username}
     */
    @Query("select u from User u where u.username = :#{#user.username}")
    List<User> queryListByName3(@Param("user") User user);

    @Modifying
    @Query("update User set username = :username where id = :id")
    void updateUserById(@Param("username")String username ,@Param("id")Integer id);
}
