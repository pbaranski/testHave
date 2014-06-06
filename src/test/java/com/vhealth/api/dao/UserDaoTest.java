package com.vhealth.api.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vhealth.api.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("DaoTest.xml")
@ActiveProfiles("testing")
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    private static final Logger logger = Logger.getLogger(UserDaoTest.class);
    private static final String TEST_USER_NAME = "testUser";
    private static final String FALSE_NAME = "falseName";
    @Autowired
    private UserDao userDao;

    @Test
    public void shouldSearchUserByUsername() throws Exception {

        //given
        //user in DB

        //when
        User found = userDao.findById(TEST_USER_NAME);

        //then
        assertThat(found.getUserName()).isEqualTo(TEST_USER_NAME);
    }

    @Test
    public void shouldReturnNullIfUserNotFound() throws Exception {
        //given
        //user FALSE_NAME not in DB

        //when
        User found = userDao.findById(FALSE_NAME);


        //then
        assertThat(found).isNull();
    }


}
