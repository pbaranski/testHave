package com.vhealth.api.service;

import com.vhealth.api.dao.ItemDao;
import com.vhealth.api.dao.UserDao;
import com.vhealth.api.entity.Item;
import com.vhealth.api.entity.User;
import com.vhealth.api.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceUnitTest {

    private static final int VALUE = 1;
    private static final String TEST_USERNAME = "testUserItemService";
    private static final String TEST_USERNAME2 = "some name";
    @InjectMocks
    public ItemService itemService = new ItemServiceImpl();
    @Mock
    private ItemDao itemDao;
    @Mock
    private UserDao userDao;

    @Test
    public void shouldDeleteItem() {
        //given
        Item item = mock(Item.class);
        when(item.getUserName()).thenReturn(TEST_USERNAME);
        when(item.getId()).thenReturn(VALUE);

        User user = mock(User.class);
        when(user.getUserName()).thenReturn(TEST_USERNAME);

        when(itemDao.findItemById(VALUE)).thenReturn(item);

        when(userDao.getLoggedUser()).thenReturn(user);

        //when
        Boolean deleted = itemService.deleteItemById(VALUE);

        //then
        assertThat(deleted).isTrue();
    }

    @Test
    public void shouldNotDeleteItemWhenLoggedUserDifferFromItemOwner() {
        //given
        Item item = mock(Item.class);
        when(item.getUserName()).thenReturn(TEST_USERNAME);
        when(item.getId()).thenReturn(VALUE);

        User user = mock(User.class);
        when(user.getUserName()).thenReturn(TEST_USERNAME2);

        when(itemDao.findItemById(VALUE)).thenReturn(item);

        when(userDao.getLoggedUser()).thenReturn(user);

        //when
        Boolean deleted = itemService.deleteItemById(VALUE);

        //then
        assertThat(deleted).isFalse();
    }

}
