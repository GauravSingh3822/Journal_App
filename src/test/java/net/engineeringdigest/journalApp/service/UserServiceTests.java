package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Resp.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    @Test
    public void testSaveNewUSerName(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    public void test( int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
