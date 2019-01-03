import dao.UserCrudRepository;
import dao.UserJpaRepository;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: wangyan
 * @Date: 2019/1/2
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SpringDataTest {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;

     /**
    * 功能描述:直接执行这个测试方法，然后就再去看一下数据库就会发生对应实体中的内容到数据库中了
    */
    @Test
    public void testCreateTableAuto(){

    }

    /**
    * 功能描述:测试springdata中的findByName方法(没有任何的实现，这就是springdata的强大)
    */
    @Test
    public void testJpaRepository(){
        //userJpaRepository:org.springframework.data.jpa.repository.support.SimpleJpaRepository@24e08d59
        System.out.println("userJpaRepository:"+ userJpaRepository);

        /*User user = userJpaRepository.findByUsername("wangyan3");
        System.out.println(user);*/

        User user = new User();
        user.setUsername("wangyan");
        List<User> userList = userJpaRepository.queryListByName3(user);
        System.out.println(userList.get(0));


}

    @Test
    public void testInsertUsers(){
        User user = new User();
        user.setUsername("王岩");
        user.setPassword("321");
        User userResult = userCrudRepository.save(user);
        System.out.println(userResult);
    }

    @Test
    @Transactional
    @Rollback(false)//取消自动回滚
    public void testUpdate(){
        userJpaRepository.updateUserById("hehe",1);
    }

}
