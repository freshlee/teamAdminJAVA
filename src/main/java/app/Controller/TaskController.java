package app.Controller;

import app.Entity.Ok;
import app.Entity.TaskEntity;
import app.Entity.User;
import app.Enum.ResType;
import app.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    private 
    @GetMapping("getTaskList")
    String getTaskList () {
        return  "sss";
    }
    @PostMapping("addOverTime")
    String addOverTime (@RequestBody TaskEntity task) {
        taskService.addNewTask(task);
        Ok res = new Ok(ResType.Success);
        return  res.toString();
    }
}
