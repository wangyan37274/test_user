package service;

import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @Auther: wangyan
 * @Date: 2019/1/3
 * @Description: service
 * @version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userJpaRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    public void updateUser(String name , Integer id){
        userJpaRepository.updateUserById(name,id);
    }

    /**
     * 测试Jpa
     */
    public void testJPA(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        //select * from user where name = ?
        /*
        Predicate predicate = cb.equal(root.get("username"),"wangyan1321");
        cq.select(root);
        cq.from(User.class);
        cq.where(predicate);
        */

        //select * from user where name = ? and age = ?
        /*
        Predicate predicate1 = cb.equal(root.get("username"),"wangyan1321");
        Predicate predicate2 = cb.equal(root.get("age"),30);
        //cq.where(predicate1,predicate2);
        Predicate predicate = cb.and(predicate1,predicate2);
        cq.where(predicate);
        */

        //select sum(age),sex from user where xuexing = ? group by sex
        Predicate predicate = cb.equal(root.get("xuexing"),"A");
        cq.multiselect(cb.sum(root.get("age")),root.get("sex"));
        cq.where(predicate);
        cq.groupBy(root.get("sex"));

        //Count number of rows in Account Table
        //Number num = (Number) em.createNativeQuery("SELECT count(id) FROM user ").getResultList();
        //System.out.println(num);

        //遍历
        List<User> userList = em.createQuery(cq).getResultList();
        System.out.println(userList.size());
        for (User user : userList){
            System.out.println(user);
        }
    }

}
