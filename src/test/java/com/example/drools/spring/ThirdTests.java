package com.example.drools.spring;

import com.example.accountproject.model.Account;
import com.example.accountproject.model.AccountingPeriod;
import com.example.accountproject.model.CashFlow;
import com.example.accountproject.services.KnowledgeSessionHelper;
import com.example.accountproject.util.DateHelper;
import com.example.accountproject.util.OutputDisplay;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;



@SuppressWarnings("restriction")
public class ThirdTests {
    static KieContainer kieContainer;
    StatelessKieSession sessionStateless = null;
    KieSession  sessionStatefull = null;

    @BeforeClass
    public static void beforeClass(){
        kieContainer= KnowledgeSessionHelper.createRuleBase();
    }

    @Before
    public void setUp() throws Exception{
        System.out.println("------------Before------------");
    }
    @After
    public void tearDown() throws Exception{
        System.out.println("------------After------------");
    }

    @Test
    public void testdeuxFait1() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSessionWithCallback(kieContainer,"ksession-lesson2");

        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResults", display);
        Account a = new Account();
        sessionStatefull.insert(a);
        AccountingPeriod period = new AccountingPeriod();
        sessionStatefull.insert(period);
        sessionStatefull.fireAllRules();
    }
    @Test
    public void testTwoFacts() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSessionWithCallback(kieContainer,"ksession-lesson2");
        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResults", display);
        Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        sessionStatefull.insert(a);
        CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);
        cash1.setAmount(1000);
        cash1.setType(CashFlow.CREDIT);
        sessionStatefull.insert(cash1);
        sessionStatefull.fireAllRules();
        Assert.assertEquals(a.getBalance(), 1000,0);

    }

    @Test
    public void testcalculateBalance() throws Exception {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSessionWithCallback(kieContainer, "ksession-lesson2");
        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResults", display);
        Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        sessionStatefull.insert(a);
        CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);
        cash1.setAmount(1000);
        cash1.setMvtDate(DateHelper.getDate("2016-01-15"));
        cash1.setType(CashFlow.CREDIT);
        sessionStatefull.insert(cash1);
        CashFlow cash2 = new CashFlow();
        cash2.setAccountNo(1);
        cash2.setAmount(500);
        cash2.setMvtDate(DateHelper.getDate("2016-02-15"));
        cash2.setType(CashFlow.DEBIT);
        sessionStatefull.insert(cash2);
        CashFlow cash3 = new CashFlow();
        cash3.setAccountNo(1);
        cash3.setAmount(1000);
        cash3.setMvtDate(DateHelper.getDate("2016-04-15"));
        cash3.setType(CashFlow.CREDIT);
        sessionStatefull.insert(cash3);
        AccountingPeriod period = new AccountingPeriod();
        period.setStartDate(DateHelper.getDate("2016-01-01"));
        period.setEndDate(DateHelper.getDate("2016-03-31"));
        sessionStatefull.insert(period);
        sessionStatefull.fireAllRules();
        Assert.assertTrue(a.getBalance()==500);
    }

}
