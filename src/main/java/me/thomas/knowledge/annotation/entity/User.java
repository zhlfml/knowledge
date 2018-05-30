package me.thomas.knowledge.annotation.entity;

import me.thomas.knowledge.annotation.def.Column;
import me.thomas.knowledge.annotation.def.Table;

/**
 * @author zhaoxinsheng
 * @date 8/7/16.
 */
@Table("user")
public class User implements Entity {

    private static final long serialVersionUID = 2850902565667929223L;

    @Column("id")
    private int id;

    @Column("username")
    private String username;

    @Column("age")
    private int age;

    @Column("email")
    private String email;

    private String admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column("admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
