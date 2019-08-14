package app.Mapper;

import app.Entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TaskDao {
    @Select("INSERT INTO task (reason, beginDate, endDate, userId) VALUES (#{reason}, #{beginDate}, #{endDate}, #{userId})")
    void addNewTask(TaskEntity task);
}
