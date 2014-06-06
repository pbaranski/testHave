package com.vhealth.api.service;

import com.vhealth.api.dto.ItemDto;
import com.vhealth.api.utils.UserDetailsExtractor;
import helpers.TestHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@ActiveProfiles("testing")
@PrepareForTest(UserDetailsExtractor.class)
public class ItemServiceTestPartlyMock {
    private static final String ITEM_NAME = "itemNumber3";
    private static final String TEST_USER_ITEM_SERVICE = "testUserItemService";
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    @Autowired
    private ItemService itemService;

    //TODO what about running testng and junit tests in same package or in project?

    /**
     * added to vm options run configuration:  -XX:-UseSplitVerifier
     * due to Inconsistent stackmap frames at branch target error
     * caused by mocking static methods
     */
    @Test
    public void shouldDeleteItem() {
        PowerMockito.mockStatic(UserDetailsExtractor.class);
        when(UserDetailsExtractor.geUserNameOfLoggedUser()).thenReturn(TEST_USER_ITEM_SERVICE);

        //given
        itemService.saveItem(TestHelper.aValidItemDto(ITEM_NAME));
        ItemDto itemForDeletion = itemService.findByItemName(ITEM_NAME);
        assertThat(itemForDeletion).isNotNull();

        //when
        itemService.deleteItemById(itemForDeletion.getId());

        //then
        ItemDto itemFound = itemService.findByItemId(itemForDeletion.getId());

        assertThat(itemFound).isNull();
    }

}
