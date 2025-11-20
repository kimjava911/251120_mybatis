package kr.java.mybatis;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import kr.java.mybatis.config.WebConfig;
import kr.java.mybatis.filter.AuthFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // filter 인스턴스 등록
        FilterRegistration.Dynamic registration =
                servletContext.addFilter("authFilter", new AuthFilter());
        registration.addMappingForUrlPatterns(null, true, "/post/*");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic app = servletContext.addServlet("dispatcher", dispatcherServlet);
        app.setLoadOnStartup(1);
        app.addMapping("/");
    }
}