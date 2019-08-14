package app.Mapper;

import app.Entity.Permissions;
import app.Entity.RoleEntiry;
import app.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface UserDao {
    @Results({ //2
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "authorizationId", column = "authorizationId")
    })
    @Select("select * from user where name = #{username}")
//    @Cacheable(value = "users", key = "#p0")
    User getUser(String username);
    @Results({ //2
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "authorizationId", column = "authorizationId")
    })
    @Select("select * from user")
    ArrayList<User> getUserList();
    @Select("SELECT allow_page FROM role_permission as p, re_role_permission as s WHERE s.role_id = #{role_id} AND s.permission_id = p.id")
    ArrayList<Permissions> getPermissionList(int role_id);
    @Select("SELECT * FROM role_type WHERE id = #{role_type}")
    RoleEntiry getRole(User user);
}
