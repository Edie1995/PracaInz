
package pl.kruko.PracaInz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.httpBasic().and().authorizeRequests()
//					.anyRequest().authenticated()
////	                .antMatchers("/user-site").hasAnyRole("USER", "ADMIN")
////	                .antMatchers("/admin-site").hasRole("ADMIN")
////	                .antMatchers("/h2_console/**").permitAll()
////	                .antMatchers("/new-user").permitAll()
//	                .and()
//	                .formLogin().permitAll()
//	                	.loginPage("/home")
//	                	.permitAll()
//	                	.defaultSuccessUrl("/bad")
//	                	.failureUrl("/login? error=True")
//	                	.usernameParameter("login")
//	                	.passwordParameter("password")
//					.and()
//					.logout()
//					.permitAll();
////					.logoutSuccessUrl("/login? Logout")
////                    .invalidateHttpSession(true)
////                    .clearAuthentication(true)
////                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                    .logoutSuccessUrl("/login?logout");
////                    .permitAll();
////                    .and()
////					.csrf().disable();
////	                .headers().frameOptions().disable();
//	}
//	BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//		bCryptPasswordEncoder = new BCryptPasswordEncoder ();
//
//        return bCryptPasswordEncoder;
//    }
//
//	
//	@Autowired
//    MyUserDetailsService userDetailsService1;
//    @Autowired
//    public void ConfigureGlobal (AuthenticationManagerBuilder auth) throws Exception { 
//    	auth.userDetailsService(userDetailsService1)
//    	.passwordEncoder(passwordEncoder());
//        
//    }
//	 @Override
//	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	        auth.inMemoryAuthentication()
//	          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//	          .and()
//	          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//	          .and()
//	          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//	    }

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/patientHome/**").hasRole("PATIENT")
				.antMatchers("/doctorHome/**").hasRole("DOCTOR")
				.antMatchers("/anonymous*").anonymous().
				antMatchers("/login*").permitAll()
				.antMatchers("/templates*").permitAll()
				.antMatchers("/css/**").permitAll().anyRequest()
				.authenticated()
				.and()
				.formLogin().loginPage("/login.html")
				.defaultSuccessUrl("/home")
				.failureUrl("/login-error.html")
				.and()
				.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
