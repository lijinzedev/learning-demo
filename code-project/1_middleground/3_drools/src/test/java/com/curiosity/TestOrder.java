package com.curiosity;

import com.curiosity.domain.ComparisonOperatorEntity;
import com.curiosity.domain.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

/**
 * @Classname TestOrder
 * @Description
 * @Date 2021/7/1 17:46
 * @Created by curiosity
 */
public class TestOrder {
    @Test
    public void Test() {
        final KieServices kieServices = KieServices.Factory.get();
        final KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        final KieSession kieSession = kieClasspathContainer.newKieSession();
        Order order = new Order();
        order.setOriginalPrice(120d);
        kieSession.insert(order);
        kieSession.fireAllRules();
        System.out.println(order);
        kieSession.dispose();

    }

    @Test
    public void Test2() {
        final KieServices kieServices = KieServices.Factory.get();
        final KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        final KieSession kieSession = kieClasspathContainer.newKieSession();
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("张三");
        entity.setList(Arrays.asList("张三"));
        kieSession.insert(entity);
        kieSession.fireAllRules();
        kieSession.dispose();

    }
}
