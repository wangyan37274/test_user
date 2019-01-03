package domain;

import javax.persistence.*;

/**
 * @Auther: wangyan
 * @Date: 2019/1/2
 * @Description: jpa规范的实体类
 * @version: 1.0
 */
@Entity
@Table(name="user")
public class User {
    //配置表的id，并且是使用自增
    @Id
    /*
        用于标注主键的生成策略
        GenerationType.TABLE:
        使用一个特定的数据库表格来保存主键，持久化引擎通过关系数据库的一张特定的表格来生成主键。
        策略的优点：不依赖于外部环境和数据库的具体实现，在不同数据库间可以很容易的进行移植。
        缺点：不能充分利用数据库的特性，一般不会优先使用。
        GenerationType.SEQUENCE：
        在某些数据库中，不支持主键自增长，比如Oracle，其提供了一种叫做"系列(sequence)"的机制生成主键。
        该策略只要部分数据库（Oracle/PostgreSQL/DB2）支持序列对象，所以该策略一般不应用与其他数据库。
        GenerationType.IDENTITY：
        此种主键生成策略就是通常所说的主键自增长，数据库在插入数据时，会自动给主键赋值，
        比如Mysql可以在创建表时声明"auto_increment"来指定主键自增长。大部分数据库都提供了该支持。
        GenerationType.AUTO：
        把主键生成策略交给持久化引擎，持久化引擎会根据数据库在以上三种主键生成策略中选择其中一种。
        因为这种策略比较常用，所以JPA默认的生成策略就是AUTO.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //设置列的长度为15，并且不能为空
    @Column(name = "username")
    private String username ;

    @Column(name = "password")
    private String password ;

    @Column(name="age")
    private Integer age;

    @Column(name="xuexing")
    private String xuexing;

    @Column(name="sex")
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getXuexing() {
        return xuexing;
    }

    public void setXuexing(String xuexing) {
        this.xuexing = xuexing;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", xuexing='" + xuexing + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
