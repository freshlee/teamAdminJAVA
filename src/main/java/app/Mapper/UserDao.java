package app.Mapper;

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

import java.util.List;

@Mapper
@Component
@CacheConfig(cacheNames = "users")
public interface UserDao {
    @Results({ //2
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "authorizationId", column = "authorizationId")
    })
    @Select("select * from user where name = #{username}")
    @Cacheable(value = "users", key = "#p0")
    User getUser(String username);
//    @CachePut(key = "#p0")
//    void UpdareRedis();
}
