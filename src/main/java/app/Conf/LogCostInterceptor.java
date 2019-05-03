package app.Conf;

import app.Entity.Ok;
import app.Enum.ResType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogCostInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();
    // 拦截前端用户权限
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object mappedValue) throws Exception {
        start = System.currentTimeMillis();
        Subject subject = SecurityUtils.getSubject();
//        System.out.println(mappedValue);
//
//        String[] rolesArray = (String[]) mappedValue;
//        if (rolesArray == null || rolesArray.length == 0) {
//            //no roles specified, so nothing to check - allow access.
//            return true;
//        }
//        for (int i = 0; i < rolesArray.length; i++) {
//            if (subject.isPermitted(rolesArray[i])) {
//                return true;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
