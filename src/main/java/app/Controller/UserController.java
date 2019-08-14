package app.Controller;

import app.Entity.Ok;
import app.Entity.Permissions;
import app.Entity.RoleEntiry;
import app.Entity.User;
import app.Enum.ResType;
import app.Mapper.UserDao;
import app.Service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    public String getUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || subject.getPrincipals() == null) {
            return "DEFAULTUSER";
        }
        return (String) subject.getPrincipals().getPrimaryPrincipal();
    }
    @Autowired
    private  UserService userService;
    @Autowired
    private UserDao userDao;


    @PostMapping("toLogin")
    public String toLogin(@RequestBody User user) {

        //System.out.println(newUser.getName()+newUser.getPassword());
        //shiro用户认证
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken userToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //执行登录方法,用捕捉异常去判断是否登录成功
        subject.login(userToken);
        Ok res = new Ok(ResType.Success);
        return res.toString();
    }
    @GetMapping("userlist")
    public String getUserList () {
        ArrayList<User> userList = userService.getUserList();
        JSONArray userList_json = new JSONArray(userList);
        return  userList_json.toString();
    }
    @GetMapping("getUserInfo")
    public String user() throws Exception{
        Ok ok = new Ok(ResType.Success);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> user_map = javaBean2Map(user);
        RoleEntiry user_role = userService.getRole(user);
        ArrayList permissions_list = userService.getPermissionList(user_role.getId());
//        permissions.setRole("admin");
        user_role.setPermission_list(permissions_list);
        user_map.put("permissions", user_role);
        ok.setData(user_map);
        return ok.toString();
    }
    public static Map<String, Object> javaBean2Map(Object javaBean) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Method[] methods = javaBean.getClass().getMethods(); // 获取所有方法
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                String field = method.getName(); // 拼接属性名
                field = field.substring(field.indexOf("get") + 3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                Object value = method.invoke(javaBean, (Object[]) null);
                if (!field.equals("class")) {
                    map.put(field, value);
                }

            }
        }
        return map;
    }
}
