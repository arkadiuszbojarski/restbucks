package org.restbucks.shopping.web;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.restbucks.shopping.application.ShoppingFacade;
import org.restbucks.shopping.application.customer.CreateCartForCustomerCommand;
import org.restbucks.shopping.application.item.AddItemToOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseRestTest {

    private static final String OUTPUT = "target/generated-snippets";
    private static final String SEPARATOR = "_";

    @Rule
    public JUnitRestDocumentation documentation = new JUnitRestDocumentation(OUTPUT);

    @Rule
    public TestName test = new TestName();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ShoppingFacade facade;

    @Before
    public void setup() {
        RestAssuredMockMvc
            .mockMvc(MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(documentation))
                .alwaysDo(document(getIdentifier()))
                .build());
    }

    @Before
    public void prepare() {
        cartCreated("00570c06-38a0-43a1-a690-d3792fe83b36");
        itemAddedToOffer("489e9fbc-3675-4910-b684-b02b31930d52", 10.50, "PLN");
    }

    private void itemAddedToOffer(String itemID, double price, String currency) {
        facade.accept(addItemToOffer(itemID, price, currency));
    }

    private void cartCreated(String customerID) {
        facade.accept(createCart(customerID));
    }

    private AddItemToOfferCommand addItemToOffer(String itemID, double price, String currency) {
        return AddItemToOfferCommand.of(randomUUID(), fromString(itemID), new BigDecimal(price), currency);
    }

    private CreateCartForCustomerCommand createCart(String customerID) {
        return CreateCartForCustomerCommand.of(randomUUID(), fromString(customerID));
    }

    private String getIdentifier() {
        return getClass().getSimpleName() + SEPARATOR + test.getMethodName();
    }
}
