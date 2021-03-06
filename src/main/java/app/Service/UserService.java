package app.Service;

import app.Entity.Ok;
import app.Entity.RoleEntiry;
import app.Entity.User;
import app.Enum.ResType;
import app.Mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User findUser(User req_user) {
        User user = userDao.getUser(req_user.username);
        return user;
    }
    public ArrayList<User> getUserList() {
        ArrayList users = userDao.getUserList();
        return users;
    }
    public ArrayList<User> getPermissionList(int role_id) {
        ArrayList permissions = userDao.getPermissionList(role_id);
        return permissions;
    }
    public RoleEntiry getRole(User req_user) {
        RoleEntiry role = userDao.getRole(req_user);
        return role;
    }
}
