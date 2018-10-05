package org.paingan.oauth2.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";
	
	private final TokenStore tokenStore;
	
	public ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		//resources.resourceId(RESOURCE_ID).stateless(false);
		resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		 http
         .exceptionHandling()
         .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
     .and()
         .csrf()
         .disable()
//         .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
         .headers()
         .frameOptions()
         .disable()
     .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     .and()
         .authorizeRequests()
         .antMatchers("/api/register").permitAll()
         .antMatchers("/api/activate").permitAll()
         .antMatchers("/api/authenticate").permitAll()
         .antMatchers("/api/account/reset-password/init").permitAll()
         .antMatchers("/api/account/reset-password/finish").permitAll()
         .antMatchers("/api/profile-info").permitAll()
         .antMatchers("/api/**").authenticated()
         .antMatchers("/management/health").permitAll()
         .antMatchers("/management/**").hasAuthority("ROLE_ADMIN")
         .antMatchers("/v2/api-docs/**").permitAll()
         .antMatchers("/swagger-resources/configuration/ui").permitAll()
         .antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN");
		 
//        http
//        	.anonymous().disable()
//            .authorizeRequests()
//                .antMatchers("/uaa/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
//                .antMatchers("/user/authenticate").permitAll()
//                .antMatchers("/h2-console").permitAll()
//                .antMatchers("/users/**").access("hasRole('ADMIN')")
//                .antMatchers("/member/**").access("hasRole('ADMIN')")
//                .antMatchers("/order/**").access("hasRole('ADMIN')")
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        
//        http.headers().frameOptions().disable(); //h2-console enable jdbc-url: jdbc:h2:mem:testdb
//    	http.headers().frameOptions().sameOrigin(); //h2-console enable
	}

}