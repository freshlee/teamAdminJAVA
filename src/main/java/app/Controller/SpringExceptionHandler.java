package app.Controller;

import app.Entity.Ok;
import app.Enum.ResType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.jws.WebResult;
import java.util.Map;
import java.util.TreeMap;

@ControllerAdvice
public class SpringExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String handleOtherExceptions(final Exception ex) {
        Ok error = new Ok(ResType.Fail);
        return error.toString();
    }
}
