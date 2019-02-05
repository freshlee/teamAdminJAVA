package app.Controller;

import app.Entity.Ok;
import app.Entity.Permissions;
import app.Entity.User;
import app.Enum.ResType;
import app.Mapper.UserDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @PostMapping("user/login")
    @ResponseBody
    public String Login(@RequestBody User req_user) throws Exception {
        User user = userDao.getUser(req_user.username);
        if (user.getPassword().equals(req_user.password)) {
            Ok res_ok = new Ok(ResType.Success);
            return res_ok.toString();
        } else {
            Ok res_err = new Ok(ResType.Logout);
            return res_err.toString();
        }
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
