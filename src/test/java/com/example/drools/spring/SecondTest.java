package com.example.drools.spring;

import com.example.accountproject.model.Account;
import com.example.accountproject.model.CashFlow;
import com.example.accountproject.util.OutputDisplay;
import com.example.accountproject.services.KnowledgeSessionHelper;
import org.drools.compiler.testframework.RuleCoverageListener;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

public class SecondTest {
    StatelessKieSession sessionStateless = null;
    KieSession sessionStatefull = null;
    static KieContainer kieContainer;

    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }

    @Test
    public void testFirstOne() {
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessions(kieContainer, "ksessionrule");
        Account a = new Account();
        sessionStatefull.insert(a);
        sessionStatefull.fireAllRules();
    }

    @Test
    public void testSecondOne() {
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessions(kieContainer, "ksessionrule");
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStatefull.setGlobal("showResults", outputDisplay);
        Account a = new Account();
        sessionStatefull.insert(a);
        sessionStatefull.fireAllRules();
    }

    @Test
    public void testRuleOneFactWithFactAndUsageOfGlobalAndCallBack() {
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessions(kieContainer, "ksessionrule");

        sessionStatefull.addEventListener(
                new RuleRuntimeEventListener() {
                    @Override
                    public void objectInserted(org.kie.api.event.rule.ObjectInsertedEvent event) {
                        System.out.println("Object inserted \n" + event.getObject().toString());
                    }

                    @Override
                    public void objectUpdated(org.kie.api.event.rule.ObjectUpdatedEvent event) {
                        System.out.println("Object was update \n" + "new Content \n" + event.getObject().toString());
                    }

                    @Override
                    public void objectDeleted(ObjectDeletedEvent event) {
                        System.out.println("Object retracted \n" + event.getOldObject().toString());
                    }
                });

        Account a = new Account();
        a.setAccountno(10);
        FactHandle handlea = sessionStatefull.insert(a);
        a.setBalance(12.0);
        sessionStatefull.update(handlea, a);
        sessionStatefull.delete(handlea);
        sessionStatefull.fireAllRules();
        System.out.println("So you saw something");
    }


@Test
public void testRuleOneFactThatInsertObject() {
    sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessions(kieContainer, "ksessionrule");
    OutputDisplay display = new OutputDisplay();
    sessionStatefull.setGlobal("showResults", display);

    sessionStatefull.addEventListener(new RuleRuntimeEventListener() {
        @Override
        public void objectInserted(ObjectInsertedEvent event) {
            System.out.println("Object inserted \n" + event.getObject().toString());
        }

        public void objectUpdated(org.kie.api.event.rule.ObjectUpdatedEvent event) {
            System.out.println("Object was update \n" + "new Content \n" + event.getObject().toString());
        }

        @Override
        public void objectDeleted(ObjectDeletedEvent event) {
            System.out.println("Object retracted \n" + event.getOldObject().toString());
        }
    });
    CashFlow cashFlow = new CashFlow();
    FactHandle handlecashflow = sessionStatefull.insert(cashFlow);
    sessionStatefull.fireAllRules();


}
}