package me.thomas.knowledge.aop;

import org.springframework.stereotype.Service;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Service
public class DemoMethodService {

    public void add() {
        System.out.println("DemoMethodService.add() invoked");
    }
}
