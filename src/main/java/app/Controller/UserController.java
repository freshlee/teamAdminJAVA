package app.Controller;

import app.Entity.Ok;
import app.Entity.Permissions;
import app.Entity.User;
import app.Enum.ResType;
import app.Mapper.UserDao;
import app.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            subject.login(userToken);
            return "redirect:/what.do";
        } catch (UnknownAccountException e) {
            return "用户名不存在";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
    }
    @PostMapping("user/login")
    @ResponseBody
    public String Login(@RequestBody User req_user, HttpServletResponse response) throws Exception {
        User user = userDao.getUser(req_user.getUsername());
        if (user.getPassword().equals(req_user.getPassword())) {
            Ok res_ok = new Ok(ResType.Success);
            return res_ok.toString();
        } else {
            Ok res_err = new Ok(ResType.Logout);
            return res_err.toString();
        }
    }
    @GetMapping("userlist")
    public String getUserList () {
        ArrayList<User> userList = userService.getUserList();
        JSONArray userList_json = new JSONArray(userList);
        return  userList_json.toString();
    }
    @GetMapping("user")
    public String user() throws Exception{
        Ok ok = new Ok(ResType.Success);
        User user = new User();
        user.setAge(26);
        user.setUsername("lee");
        Map<String, Object> user_map = javaBean2Map(user);
        Permissions permissions = new Permissions();
        permissions.setRole("admin");
        user_map.put("permissions", permissions);
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
