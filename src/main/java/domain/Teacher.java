package domain;

import lombok.Data;

/**
 * @Auther: wangyan
 * @Date: 2019/1/4
 * @Description: 测试Lombok插件，自动生成get，set方法，在class文件生成，这里不显示
 * @version: 1.0
 */
@Data
public class Teacher {
    private String name;
    private int age;

}
