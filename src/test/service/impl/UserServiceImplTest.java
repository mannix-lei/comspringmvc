package service.impl;

import com.dao.UserDaoInf;
import junit.framework.TestCase;
import org.junit.Test;
import com.pojo.User;

/**
 * Created by Tellyes_worker on 2017/12/5/0005.
 */
public class UserServiceImplTest extends TestCase {
    @Test
    public void testGetUserById() throws Exception {
        UserDaoInf userDao = new UserDaoInf();
        User user = userDao.findUserById("003");
    }

    @Test
    public void testAdd(){
        UserDaoInf userDao = new UserDaoInf();
        User user = new User();
        user.setId("001");
        user.setName("001");
        user.setPassword("001");
        userDao.addUser(user);
    }
}