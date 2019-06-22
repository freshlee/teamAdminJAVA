package app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private 
    @GetMapping("getTaskList")
    String getTaskList () {
        return  "sss";
    }
}
