package helpers;

import com.vhealth.api.dto.ItemDto;
import com.vhealth.api.dto.UserDto;
import com.vhealth.api.dto.UserDtoMapper;
import com.vhealth.api.entity.Item;
import com.vhealth.api.entity.User;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;

public class TestHelper {

    public static UserDto aValidUser(String userName) {
        UserDto userDto = new UserDto();
        userDto.setCreatedOn(DateTime.now());
        userDto.setFirstName("testFirstName");
        userDto.setLastName("testSurname");
        userDto.setPassword("testPassword");
        userDto.setUserName(userName);
        return userDto;
    }

    public static Item aValidItem(String itemName, String userName) {
        UserDto userDto = aValidUser(userName);
        User user = UserDtoMapper.generateUserFromDto(userDto);
        Item item = new Item();
        item.setItemName(itemName);
        item.setUser(user);
        return item;
    }

    public static ItemDto aValidItemDto(String itemName) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(itemName);
        return itemDto;
    }

    public static String encodeBase64(String userName, String password) {
        String forDecode = userName + ":" + password;
        String encoding = "Basic " + new String(Base64.encodeBase64(forDecode.getBytes()));
        return encoding;
    }
}
