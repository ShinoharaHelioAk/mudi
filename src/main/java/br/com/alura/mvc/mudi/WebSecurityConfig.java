package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.anyRequest().authenticated()
//			.and()
//				.httpBasic();
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//Linha abaixo: Exceção para páginas que podem carregar independente de ter usuário logado.
				.antMatchers("/home/**").permitAll()
				//Linha abaixo: Requisições que só carregam se existir autenticação de usuário.
				.anyRequest().authenticated()
			.and()
//				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/usuario/pedidos", true).permitAll())
//				.logout(logout -> logout.logoutUrl("/logout"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home"))
				.csrf().disable()
		;
	}
	
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			//Linha abaixo utilizada para fazer a criação de um usuário.
//			UserDetails user =
//				 User.builder()
//					.username("maria")
//					.password(passwordEncoder.encode("maria"))
//					.roles("ADM")
//					.build();
		
			auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder)
				//Linha abaixo utilizada para fazer a criação de um usuário.
//				.withUser(user)
				;
		}
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("joao")
//				.password("joao")
//				.roles("ADM")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
