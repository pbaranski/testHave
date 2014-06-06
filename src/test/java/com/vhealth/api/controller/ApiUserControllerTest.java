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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("ApiControllerTest.xml")
@ActiveProfiles("testing")
public class ApiUserControllerTest {
    //Test user
    private final String userName = "testUserDetails";
    private final String userPassword = "testPassword";
    private String base64ForTestUser = TestHelper.encodeBase64(userName, userPassword);
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
    public void shouldReturnUserToUserWithAdminRights() throws Exception {
        mockMvc.perform(get("/api/users/" + userName).header("Authorization", base64ForTestUser).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("userName").value(userName));
    }

    @Test
    public void shouldReturnUserWhenCreatingUser() throws Exception {
        mockMvc.perform(post("/api/users").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"testUserNew\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("userName").value("testUserNew"));
    }

    @Test
    public void shouldReturnErrorMessageToAdminWhenUpdatingUserWithoutUserName() throws Exception {
        mockMvc.perform(put("/api/users/testUserDetails").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"userName shouldn't be empty or contain non alphanumerical symbols\""));
    }

    @Test
    public void shouldReturnErrorMessageToAdminWhenUpdatingUserWithNonexistentUserName() throws Exception {
        mockMvc.perform(put("/api/users/testUserNonExistent").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"testUserNonExistent\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"userName shouldn't be empty or contain non alphanumerical symbols\""));
    }

    @Test
    public void shouldReturnErrorMessageToAdminWhenCreatingUserWithUsedUserName() throws Exception {
        mockMvc.perform(post("/api/users").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"Creating user requires unique userName\""));
    }

    @Test
    public void shouldReturnErrorMessageToAdminWhenCreatingUserWithEmptyUserName() throws Exception {
        mockMvc.perform(post("/api/users").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"userName shouldn't be empty or contain non alphanumerical symbols\""));
    }

    @Test
    public void shouldReturnErrorMessageToAdminWhenDeletingNonexistentUserName() throws Exception {
        mockMvc.perform(delete("/api/users/trololo").header("Authorization", base64ForTestUser))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"Deleting nonexistent user\""));
    }

}
