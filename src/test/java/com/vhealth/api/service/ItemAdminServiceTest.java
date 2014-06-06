package com.vhealth.api.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vhealth.api.entity.Item;
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

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("ItemServiceTest.xml")
@ActiveProfiles("testing")
public class ItemAdminServiceTest extends AbstractTestNGSpringContextTests {
    private static final String TEST_ITEM_SERVICE_NAME = "testItemServiceName";
    private static final String TEST_USER_ITEM_SERVICE = "testUserItemService";
    private static final String ITEM_FOR_SAVE = "itemForSave";
    private static final String OLD_NAME = "oldName";
    private static final String NEW_NAME = "newName";
    private static final int ITEM_ID = 1;
    @Autowired
    private ItemAdminService itemAdminService;

    @Test
    public void shouldFindItemById() {
        //given
        //item in database

        //when
        Item itemFound = itemAdminService.findByItemId(ITEM_ID);

        //then
        assertThat(itemFound.getId()).isEqualTo(ITEM_ID);
    }

    @Test
    public void shouldFindItemByName() {
        //given
        //item in database

        //when
        Item itemFound = itemAdminService.findByItemName(TEST_ITEM_SERVICE_NAME);

        //then
        assertThat(itemFound.getItemName()).isEqualTo(TEST_ITEM_SERVICE_NAME);
    }

    @Test(dependsOnMethods = {"shouldFindItemByName"})
    public void shouldAddItem() {
        //given
        Item itemForSave = TestHelper.aValidItem(ITEM_FOR_SAVE, TEST_USER_ITEM_SERVICE);

        //when
        itemAdminService.saveItem(itemForSave);

        //then
        Item itemFound = itemAdminService.findByItemName(ITEM_FOR_SAVE);
        assertThat(itemFound).isNotNull();
    }

    @Test(dependsOnMethods = {"shouldAddItem"})
    public void shouldUpdateItem() {
        //given
        Item itemForUpdate = TestHelper.aValidItem(OLD_NAME, TEST_USER_ITEM_SERVICE);
        itemForUpdate = itemAdminService.saveItem(itemForUpdate);
        itemForUpdate.setItemName(NEW_NAME);

        //when
        itemAdminService.updateItem(itemForUpdate);

        //then
        Item itemFound = itemAdminService.findByItemName(NEW_NAME);
        assertThat(itemFound).isNotNull();
        itemFound = itemAdminService.findByItemName(OLD_NAME);
        assertThat(itemFound).isNull();
    }

    @Test(dependsOnMethods = {"shouldAddItem"})
    public void shouldDeleteItem() {
        //given
        Item itemForDeletion = itemAdminService.saveItem(TestHelper.aValidItem(ITEM_FOR_SAVE, TEST_USER_ITEM_SERVICE));
        Item itemFound = itemAdminService.findByItemId(itemForDeletion.getId());
        assertThat(itemFound).isNotNull();
        //when
        itemAdminService.deleteItemById(itemForDeletion.getId());

        //then
        itemFound = itemAdminService.findByItemId(itemForDeletion.getId());
        assertThat(itemFound).isNull();
    }


}
