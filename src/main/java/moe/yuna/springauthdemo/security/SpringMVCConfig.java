package moe.yuna.springauthdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.groovy.GroovyMarkupViewResolver;

/**
 * Created by rika on 2015/11/13.
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(groovyMarkupViewResolver());
    }

    @Bean
    public ViewResolver groovyMarkupViewResolver() {
        // springMVC模板默认屏蔽request的attribute
        // 但傻逼的spring-security却把csrftoken放在了request里
        // 必须强制指定允许模板获取request的attribute值
        // 这TM还是不是一个框架体系下的东西了?
        // 傻逼!
        GroovyMarkupViewResolver groovyMarkupViewResolver = new GroovyMarkupViewResolver();
        groovyMarkupViewResolver.setExposeRequestAttributes(true);
        groovyMarkupViewResolver.setSuffix(".tpl");
        return groovyMarkupViewResolver;
    }
}
