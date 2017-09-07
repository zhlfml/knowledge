package me.thomas.knowledge.spring.aop;

import org.springframework.stereotype.Service;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Service
public class DemoAnnotationService {

    @Action(name = "add")
    public void add() {
        System.out.println("DemoAnnotationService.add() invoked");
    }
}
