package domain;

import javax.persistence.*;

/**
 * @Auther: wangyan
 * @Date: 2019/1/12
 * @Description: domain，权限实体，一个角色对应多个权限
 * @version: 1.0
 */
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="roleid")
    private Integer id;

    @Column(name="auth_name")
    private String authName;

    //一个权限只能分给一个角色
    @ManyToOne
    @JoinColumn(name = "role_id")//维护一外键
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
