package domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: wangyan
 * @Date: 2019/1/4
 * @Description: 测试Lombok插件，自动生成get，set方法，在class文件生成，这里不显示
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @ManyToMany(mappedBy="teachers")
    private Set<User> users = new HashSet<>();

}
