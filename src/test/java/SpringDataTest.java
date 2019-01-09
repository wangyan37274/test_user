import dao.RoleDao;
import dao.UserDao;
import domain.Role;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @PersistenceContext(name="entityManagerFactory")
    private EntityManager em;

    /**
     * 内部实现原理：为什么不用写接口的实现类，userJpaRepository注入的是什么？
     */
    @Test
    public void testImpl(){
        //userJpaRepository:org.springframework.data.jpa.repository.support.SimpleJpaRepository@24e08d59
        System.out.println("userJpaRepository:"+ userDao);
        //class com.sun.proxy.$Proxy29:代理对象是基于JDK的动态代理方式创建的
        System.out.println(userDao.getClass());
        /*
            以上说明，spring data jpa 做了什么？
            1，根据自己定义的接口UserCrudRepository生成了一个代理类
            2，利用这个代理类创建了一个SimpleJpaRepository对象
         */
        //自己实现
        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        //getRepository(UsersDao.class):可以帮助我们为接口生成实现类。而这个实现类是SimpleJpaRepository的对象
        //要求：该接口必须要是继承 Repository 接口
        UserDao ud = factory.getRepository(UserDao.class);
        System.out.println(ud);
        System.out.println(ud.getClass());

    }

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
    public void testMyMethod(){
        User user = userDao.findByUsername("wangyan3");
        System.out.println(user);
    }

    /**
     * 测试注解方式查询
     */
    @Test
    public void testAnnotation(){
        User user = new User();
        user.setUsername("wangyan");
        List<User> userList = userDao.queryListByName3(user);
        System.out.println(userList.get(0));
    }

    /**
     * 测试注解方式修改
     */
    @Test
    @Transactional
    @Rollback(false)//取消自动回滚，则测试类中，默认是自动回滚的
    public void testUpdate(){
        userDao.updateUserById("hehe",1);
    }


    /**
     * 测试CrudRepository接口中的方法
     * save：根据主键判断：有则修改，没有则添加
     */
    @Test
    public void testInsertUsers(){
        User user = new User();
        user.setUsername("王岩2");
        user.setPassword("321");
        user.setId(7);
        User userResult = userDao.save(user);
        System.out.println(userResult);
    }

    /**
     * 测试PagingAndSortingRepository接口
     * 分页：不带查询条件，对所有数据分页
     */
    @Test
    public void testPage(){
        int page = 2; //page:当前页的索引。 注意索引都是从 0 开始的。
        int size = 3;// size:每页显示 3 条数据
        Pageable pageable= new PageRequest(page, size);
        Page<User> p = this.userDao.findAll(pageable);
        System.out.println("数据的总条数： "+p.getTotalElements());
        System.out.println("总页数： "+p.getTotalPages());
        List<User> list = p.getContent();
        for (User users : list) {
            System.out.println(users);
        }
    }

    /**
     * /**
     * 测试PagingAndSortingRepository接口
     * 排序：单列
     */
    @Test
    public void testOrder(){
        //Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
        //direction:排序规则
        //properties:指定做排序的对象（不是表字段）属性
        Sort sort = new Sort(Sort.Direction.DESC,"userid");
        List<User> list = (List<User>)this.userDao.findAll(sort);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrder2(){
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"userage");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"username");
        Sort sort = new Sort(order1,order2);
        List<User> list = (List<User>)this.userDao.findAll(sort);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 测试JpaSpecificationExecutor接口
     * 多条件查询
     */
    @Test
    public void testSpecification(){
        //Specification接口其实就是封装查询条件的接口
        Specification spec = new Specification() {
            /*
                Predicate：定义了查询条件
                Root<Users> root：根对象。 封装了查询条件的对象
                CriteriaQuery<?> query：定义了一个基本的查询.一般不使用
                CriteriaBuilder cb：创建一个查询条件

                多条件：cb.and(predicate1,predicate2,...)
                Predicate pre = cb.and(cb.equal(root.get("username"), "王岩"),
                        cb.equal(root.get("age"), 0));
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate pre = cb.and(cb.equal(root.get("username"), "王岩"),
                        cb.equal(root.get("age"), 0));
                return pre;
            }
        };
        List<User> list = userDao.findAll(spec);
        for (User user : list){
            System.out.println(user);
        }
    }

    /**
     * 测试JpaSpecificationExecutor接口
     * 分页和排序
     */
    @Test
    public void testSpecificationPage(){
        //定义查询条件
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.like(root.get("username").as(String.class), "王%");
            }
        };
        //排序
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //分页的定义
        Pageable pageable = new PageRequest(2,2, sort);
        Page<User> page = this.userDao.findAll(spec, pageable);
        System.out.println("总条数： "+page.getTotalElements());
        System.out.println("总页数： "+page.getTotalPages());
        //遍历结果
        List<User> list = page.getContent();
        for (User user : list){
            System.out.println(user);
        }
    }

    /**
     * 测试一对一
     */
    @Test
    public void testOneToOne(){
        //创建角色
        Role roles = new Role();
        roles.setRolename("管理员");
        //创建用户
        User user = new User();
        user.setAge(30);
        user.setUsername("王岩");
        //建立关系
        user.setRole(roles);
        roles.setUser(user);
        //保存数据
        this.userDao.save(user);
    }

    /**
     * 查询一对一
     */
    @Test
    public void testQueryOneToOne(){
        User user = userDao.getOne(2);
        System.out.println("用户信息： "+user);
        Role role = user.getRole();
        System.out.println(role);
    }



    @Test
    public void testRoleDao(){
        roleDao.findById();
    }

}
