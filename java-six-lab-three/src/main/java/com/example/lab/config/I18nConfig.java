package com.example.lab.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("utf-8");
        ms.setBasenames("classpath:i18n/messages", "classpath:i18n/global");
        return ms;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(new Locale("vi"));
        cookieLocaleResolver.setCookieDomain("myAppLocaleCookie");
        cookieLocaleResolver.setCookiePath("/");
        cookieLocaleResolver.setCookieMaxAge(60 * 60);
        return cookieLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry
                .addInterceptor(lci)
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**");
    }
}
