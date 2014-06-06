package com.vhealth.api.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("ApiControllerTest.xml")
@ActiveProfiles("testing")
public class ApiUserDetailsControllerTest {

    private static final String TEST_ITEM_NAME = "testItemDetailsName";
    //Test user
    private final String USERNAME = "testUserDetails";
    private final String PASSWORD = "testPassword";
    private String base64ForTestUser = TestHelper.encodeBase64(USERNAME, PASSWORD);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void shouldReturnUserDetailsOfUserWhatLoggedIn() throws Exception {
        mockMvc.perform(get("/api/userDetails").header("Authorization", base64ForTestUser).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("userName").value(USERNAME));
    }

    @Test
    public void shouldReturnItemsDetailsOfUserWhatLoggedIn() throws Exception {
        mockMvc.perform(get("/api/userDetails/items").header("Authorization", base64ForTestUser).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].itemName").value(TEST_ITEM_NAME));
    }

    @Test
    public void shouldRejectUnauthorizedAccessToUserDetailsResources() throws Exception {
        mockMvc.perform(get("/api/userDetails"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldAcceptAccessWithoutAuthorization() throws Exception {
        mockMvc.perform(get("/ui")).andExpect(status().isOk());
    }


}
