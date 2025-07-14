package com.newer.jay.demo.service.user;

import com.newer.jay.demo.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDetailImpl 测试类
 */
public class UserDetailImplTest {

    @Test
    public void testUserDetailImplConstructor() {
        // 创建测试用户
        User user = new User();
        user.setUserId(1);
        user.setName("测试用户");
        user.setEmail("test@example.com");
        user.setHashedPassword("hashedPassword123");

        // 测试无参构造函数
        UserDetailImpl userDetail1 = new UserDetailImpl();
        assertNotNull(userDetail1);

        // 测试有参构造函数
        UserDetailImpl userDetail2 = new UserDetailImpl(user);
        assertNotNull(userDetail2);
        assertEquals(user, userDetail2.getUser());
        assertEquals("test@example.com", userDetail2.getUsername());
        assertEquals("hashedPassword123", userDetail2.getPassword());
        assertTrue(userDetail2.isEnabled());
        assertTrue(userDetail2.isAccountNonExpired());
        assertTrue(userDetail2.isAccountNonLocked());
        assertTrue(userDetail2.isCredentialsNonExpired());
    }
}
