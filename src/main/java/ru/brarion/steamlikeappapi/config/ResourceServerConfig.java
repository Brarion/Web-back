package ru.brarion.steamlikeappapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import ru.brarion.steamlikeappapi.api.ApiRequestMapping;

@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server-rest-api";

    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";

    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, ApiRequestMapping.USER_ACCOUNTING + "/**").access(SECURED_READ_SCOPE)
                .antMatchers(ApiRequestMapping.USER_ACCOUNTING + "/**").access(SECURED_WRITE_SCOPE)
                .anyRequest().permitAll();
    }
}
