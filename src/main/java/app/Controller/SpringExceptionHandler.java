package app.Controller;

import app.Entity.Ok;
import app.Enum.ResType;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.jws.WebResult;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;
// UnknownAccountException e
@ControllerAdvice
public class SpringExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String handleOtherExceptions(final Exception ex) {
        Ok error = new Ok(ResType.Fail);
        error.setMsg("系统报错");
        return error.toString();
    }
    @ExceptionHandler
    @ResponseBody
    public String handleOtherExceptions(final UnknownAccountException ex) {
        Ok error = new Ok(ResType.Fail);
        error.setMsg("用户名不存在");
        return error.toString();
    }
    @ExceptionHandler
    @ResponseBody
    public String handleOtherExceptions(final IncorrectCredentialsException ex) {
        Ok error = new Ok(ResType.Fail);
        error.setMsg("密碼錯誤");
        return error.toString();
    }
    @ExceptionHandler
    @ResponseBody
    public String handleOtherExceptions(final UnavailableSecurityManagerException ex, HttpServletResponse response) {
        Ok error = new Ok(ResType.Logout);
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        error.setMsg("您已登出");
        return error.toString();
    }
}
