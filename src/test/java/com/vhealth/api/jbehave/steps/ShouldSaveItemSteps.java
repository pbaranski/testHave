package com.vhealth.api.jbehave.steps;

import helpers.TestHelper;
import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class ShouldSaveItemSteps {
    //Test user
    private final String userName = "testUserDetails";
    private final String userPassword = "testPassword";
    private String base64ForTestUser = TestHelper.encodeBase64(userName, userPassword);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @BeforeStories
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    @Given("user testUserDetails in database")
    public void givenUserTestUserDetailsInDatabase() throws Exception {
        mockMvc.perform(get("/api/userDetails")
                .header("Authorization", base64ForTestUser)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("userName").value(userName));
    }

    @When("I save item with details of disease: <DISEASE>")
    public void whenISaveItemWithDetailsOfDiseasedisease
            (@Named("DISEASE") String disease) throws Exception {
        mockMvc.perform(post("/api/items/")
                .header("Authorization", base64ForTestUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"" + disease + "\"}"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Then("the item with given disease: <DISEASE> should be available in application")
    public void thenTheItemWithGivenDiseasediseaseShouldBeAvailableInApplication
            (@Named("DISEASE") String disease) throws Exception {
        mockMvc.perform(get("/api/items/name/" + disease).
                header("Authorization", base64ForTestUser))
                .andDo(print())
                .andExpect(jsonPath("itemName").value(disease));
    }


}
