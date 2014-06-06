package com.vhealth.api.dao;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vhealth.api.entity.Item;
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
public class ItemDaoTest extends AbstractTestNGSpringContextTests {
    private static final int NONEXISTENT_ID = 123456;
    private static final String NONEXISTENT_ITEM_NAME = "fakeItemName";
    private static final String EXISTING_ITEM_NAME = "testItemDaoName";
    private static final int EXISTING_ITEM_ID = 2;
    @Autowired
    private ItemDao itemDao;

    @Test
    public void shouldFindItemById() {
        //given
        //item in DB

        Item found = itemDao.findItemById(EXISTING_ITEM_ID);
        assertThat(found.getId()).isEqualTo(EXISTING_ITEM_ID);
    }

    @Test
    public void shouldFindItemItemName() {
        //given
        //item in DB

        //when
        Item found = itemDao.findItemByName(EXISTING_ITEM_NAME);

        //then
        assertThat(found.getItemName()).isEqualTo(EXISTING_ITEM_NAME);
    }

    @Test
    public void shouldReturnNullIfNotFindItemById() {
        //given
        //item with given id NOT in DB

        //when
        Item found = itemDao.findItemById(NONEXISTENT_ID);

        //then
        assertThat(found).isNull();
    }

    @Test
    public void shouldReturnNullIfNotFindItemByName() {
        //given
        //item with given itemName NOT in DB

        //when
        Item found = itemDao.findItemByName(NONEXISTENT_ITEM_NAME);

        //then
        assertThat(found).isNull();
    }


}
