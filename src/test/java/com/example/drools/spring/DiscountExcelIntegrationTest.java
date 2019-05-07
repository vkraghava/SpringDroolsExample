package com.example.drools.spring;

import com.example.accountproject.services.KnowledgeSessionHelper;
import com.example.excel.customer.model.Customer;
import com.example.excel.customer.model.DroolsBeanFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DiscountExcelIntegrationTest {

    private KieSession kSession;
    static KieContainer kieContainer;
   /* @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }*/
    @Before
    public void setup() {
        Resource dt
                = ResourceFactory
                .newClassPathResource("rules/excel/Discount.xls",
                        getClass());

        kSession =new DroolsBeanFactory().getKieSession(dt);
    }

    @Test
    public void
    giveIndvidualLongStanding_whenFireRule_thenCorrectDiscount()
            throws Exception {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5);
        kSession.insert(customer);

        kSession.fireAllRules();

       Assert.assertEquals(customer.getDiscount(), 15);
    }

    @Test
    public void giveIndvidualRecent_whenFireRule_thenCorrectDiscount()
            throws Exception {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 1);
        kSession.insert(customer);

        kSession.fireAllRules();

        Assert.assertEquals(customer.getDiscount(), 5);
    }

    @Test
    public void giveBusinessAny_whenFireRule_thenCorrectDiscount()
            throws Exception {
        Customer customer = new Customer(Customer.CustomerType.BUSINESS, 0);
        kSession.insert(customer);

        kSession.fireAllRules();

        Assert. assertEquals(customer.getDiscount(), 20);
    }
}
