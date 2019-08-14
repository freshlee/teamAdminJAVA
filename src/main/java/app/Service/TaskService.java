package app.Service;

import app.Entity.TaskEntity;
import app.Mapper.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;

    public void addNewTask(TaskEntity task) {
        taskDao.addNewTask(task);
    }
}
