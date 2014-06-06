package com.vhealth.api.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vhealth.api.dto.UserDto;
import helpers.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("UserServiceTest.xml")
@ActiveProfiles("testing")
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private static final String TEST_USER_NAME = "testUser1";
    private static final int USERS_IN_DB = 3;
    private static final String TEST_USER_FOR_ADD = "testUser4";
    private static final String TEST_USER_FOR_UPDATE = "testUser3";
    private static final String TEST_USER_DELETE = "testUser3";
    @Autowired
    private UserService userService;

    @Test
    public void shouldFindExistingUser() throws Exception {
        //given
        //user in database

        //when
        UserDto userDto = userService.findByUserName(TEST_USER_NAME);

        //then
        assertThat(userDto.getUserName()).isEqualTo(TEST_USER_NAME);
    }

    @Test
    public void shouldFindAllUsers() throws Exception {
        //given
        //in database are 3 users

        //when
        List<UserDto> users = userService.findUsers();

        //then
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(USERS_IN_DB);
    }

    @Test(dependsOnMethods = "shouldFindExistingUser")
    public void shouldAddUser() throws Exception {
        //given

        UserDto userDto = TestHelper.aValidUser(TEST_USER_FOR_ADD);
        assertThat(userService.findByUserName(TEST_USER_FOR_ADD)).isNull();

        //when
        userService.saveUser(userDto);

        //then
        UserDto found = userService.findByUserName(TEST_USER_FOR_ADD);
        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo(userDto.getUserName());
    }

    @Test(dependsOnMethods = "shouldFindExistingUser")
    public void shouldUpdateUser() throws Exception {
        //given
        //user in database
        //testUser3 added in import.sql script
        UserDto testUser3 = userService.findByUserName(TEST_USER_FOR_UPDATE);
        testUser3.setFirstName("newFirstName");
        testUser3.setLastName("newLastName");

        //when
        userService.updateUser(testUser3);

        //then
        UserDto found = userService.findByUserName(TEST_USER_FOR_UPDATE);

        assertThat(found).isNotNull();
        assertThat(found.getFirstName()).isEqualTo("newFirstName");
        assertThat(found.getLastName()).isEqualTo("newLastName");

    }

    @Test(dependsOnMethods = "shouldFindExistingUser")
    public void shouldDeleteUser() throws Exception {
        //given
        //user in database

        //when
        userService.deleteUserByName(TEST_USER_DELETE);

        //then
        assertThat(userService.findByUserName(TEST_USER_DELETE)).isNull();
    }

    //validation on that layer is questionable
    @Test(enabled = false)
    public void shouldRejectAddingUserWithUsedUserName() throws Exception {
        //given
        //user in database
        UserDto userDtoNew = new UserDto();
        userDtoNew.setFirstName("newName");
        userDtoNew.setUserName("testUser5");

        //when
        userService.saveUser(userDtoNew);
        boolean added = userService.findByUserName(userDtoNew.getUserName()).equals(userDtoNew);

        //then
        UserDto userDto = userService.findByUserName("testUser5");
        assertThat(added).isFalse();
        assertThat(userDto.getFirstName()).isNotEqualTo(userDtoNew.getFirstName());
    }

}

