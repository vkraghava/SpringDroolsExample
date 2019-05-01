package com.example.drools.spring;

import com.example.accountproject.services.KnowledgeSessionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

@SuppressWarnings("restriction")
public class FirstTry {
    StatelessKieSession sessionStateLess = null;
    KieSession sessionStatefull = null;
    static KieContainer kieContainer;
    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }
    @Test
    public void testFirstOne() {
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessions(kieContainer, "ksessionrule");
        sessionStatefull.fireAllRules();
    }
}
