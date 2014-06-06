package helpers.zTestGround;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vhealth.api.dto.UserDto;
import com.vhealth.api.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

@ActiveProfiles("testing")
@Ignore
public class TestDBUnit {
    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup("a.xml")
    public void shouldFindExistingUser() throws Exception {
        //given
        //user in database
        //testUser1 added in import.sql script

        //when
        UserDto userDto = userService.findByUserName("testUser1");

        //then
        Assert.assertEquals("testUser1", userDto.getUserName());
        Assert.assertEquals("testFirstName", userDto.getFirstName());
    }

    @Test
    @DatabaseSetup("b.xml")
    public void shouldFindExistingUser2() throws Exception {
        //given
        //user in database
        //testUser1 added in import.sql script

        //when
        UserDto userDto = userService.findByUserName("testUser2");

        //then
        Assert.assertEquals("testUser2", userDto.getUserName());
        Assert.assertEquals("testFirstName", userDto.getFirstName());
    }


}

