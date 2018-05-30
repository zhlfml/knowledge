package me.thomas.knowledge.annotation;

import me.thomas.knowledge.annotation.entity.Admin;
import me.thomas.knowledge.annotation.entity.User;

/**
 * @author zhaoxinsheng
 * @date 8/7/16.
 */
public class Tutorial {

    public static void main(String[] args) {
        User user = new User();
        user.setId(100);
        user.setUsername("thomas");

        Admin admin = new Admin();
        admin.setId(101);
        admin.setUsername("admin");
        admin.setAdmin("true");

        Selector selector = new Selector();
        String sql = selector.query(user);
        String sql2 = selector.query(admin);

        System.out.println(sql);
        System.out.println(sql2);
    }
}
