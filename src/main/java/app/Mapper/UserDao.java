package app.Mapper;

import app.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper
public interface UserDao {
    @Results({ //2
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "authorizationId", column = "authorizationId")
    })
    @Select("select * from user where name = #{username}")
    User getUser(String username);
}
