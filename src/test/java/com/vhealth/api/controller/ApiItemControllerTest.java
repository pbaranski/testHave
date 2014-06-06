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

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
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
public class ApiItemControllerTest {
    private static final int ITEM_ID = 2;
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
    public void shouldSaveItemDto() throws Exception {
        mockMvc.perform(post("/api/items/").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"testItem\"}"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldUpdateItemDto() throws Exception {
        //Given
        //item in DB

        mockMvc.perform(put("/api/items/" + ITEM_ID).header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"testItemNew\"}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("itemName").value("testItemNew"));
    }

    @Test
    public void shouldGetItemDto() throws Exception {
        //given
        //item in DB

        mockMvc.perform(get("/api/items/" + ITEM_ID).header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(ITEM_ID));
    }

    @Test
    public void shouldGetItemsDtoBelongsToLoggedUser() throws Exception {
        //given
        //x items in  DB but only 3 belongs to current user

        mockMvc.perform(get("/api/items/").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldDeleteItem() throws Exception {
        //given item in DB

        mockMvc.perform(delete("/api/items/" + ITEM_ID).header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("\"Item id " + ITEM_ID + " deleted = true\""));
    }
}

