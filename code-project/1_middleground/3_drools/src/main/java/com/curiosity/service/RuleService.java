package com.curiosity.service;

import com.curiosity.domain.Calculation;
import com.curiosity.domain.CreditCardApplyInfo;
import com.curiosity.domain.InsuranceInfo;
import com.curiosity.utils.KieSessionUtils;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleService {


    private final KieBase kieBase;


    public Calculation deal(Calculation calculation) {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(calculation);
        kieSession.fireAllRules();
        kieSession.dispose();
        return calculation;
    }

    //调用Drools规则引擎实现信用卡申请
    public CreditCardApplyInfo creditCardApply(CreditCardApplyInfo creditCardApplyInfo){
        KieSession session = kieBase.newKieSession();
        session.insert(creditCardApplyInfo);
        session.fireAllRules();
        session.dispose();
        return creditCardApplyInfo;
    }

    public List<String> insuranceInfoCheck(InsuranceInfo insuranceInfo) throws Exception{
        KieSession session = KieSessionUtils.getKieSessionFromXLS( this.getClass().getClassLoader().getResource("insuranceInfoCheck.xls").getPath());//获取文件路径);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(insuranceInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();

        return listRules;
    }
}
