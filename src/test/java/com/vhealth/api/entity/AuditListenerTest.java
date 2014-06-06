package com.vhealth.api.entity;

import com.vhealth.api.dao.UserDao;
import com.vhealth.api.dto.UserDto;
import com.vhealth.api.service.UserService;
import helpers.TestHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@ActiveProfiles("testing")
public class AuditListenerTest extends AbstractTestNGSpringContextTests {

    public static final String TEST_USER_AUDIT = "testUserAudit";
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    private UserDto userDto;

    @Test
    public void shouldAddCreateOnDateAutomatically() throws Exception {
        //given
        //userDto createdOn == null
        userDto = TestHelper.aValidUser(TEST_USER_AUDIT);

        //when
        userService.saveUser(userDto);

        //then
        UserDto userDtoFound = userService.findByUserName(TEST_USER_AUDIT);
        assertThat(userDtoFound.getCreatedOn().isBeforeNow());

    }

    @Test(dependsOnMethods = "shouldAddCreateOnDateAutomatically")
    public void shouldAddModifiedOnDateAutomatically() throws Exception {
        //given
        //userDto with modifiedOn == null

        //when
        userService.updateUser(userDto);

        //then
        User userFound = userDao.findById(TEST_USER_AUDIT);
        DateTime timeUserModified = userFound.getModifiedOn();

        assertThat(timeUserModified).isNotNull();
        assertThat(userFound.getCreatedOn().isBefore(timeUserModified));
    }
}
