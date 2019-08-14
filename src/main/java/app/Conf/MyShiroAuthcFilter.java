package app.Conf;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyShiroAuthcFilter extends FormAuthenticationFilter {
    public MyShiroAuthcFilter() {
        super();
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return super.onAccessDenied(request, response);
        } else {
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            httpServletResponse.addHeader("REQUIRE_AUTH", "true");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }
}
