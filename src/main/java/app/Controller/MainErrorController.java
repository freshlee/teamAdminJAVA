package app.Controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class MainErrorController {
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    Map<String,Object> runtimeExceptionHandler(RuntimeException runtimeException) {
        Map model = new TreeMap();
        model.put("status", false);
        return model;
    }
}
