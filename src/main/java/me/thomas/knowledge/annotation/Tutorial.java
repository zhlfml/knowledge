package me.thomas.knowledge.annotation;

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

        Selector selector = new Selector();
        String sql = selector.query(user);

        System.out.println(sql);
    }
}
