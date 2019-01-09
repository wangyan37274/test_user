package domain;

import javax.persistence.*;

/**
 * @Auther: wangyan
 * @Date: 2019/1/6
 * @Description: domain
 * @version: 1.0
 */
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="roleid")
    private Integer roleid;

    @Column(name="rolename")
    private String rolename;

    @OneToOne(mappedBy="role")
    private User user;


    public Integer getRoleid() {
        return roleid;
    }
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    public String getRolename() {
        return rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", user=" + user +
                '}';
    }
}
