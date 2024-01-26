package br.com.felipeduarte.gerenciadordeestudo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguracao{
	
	private static String[] PATH_PUBLICO_POST = {"/api/v1/auth/login", "/api/v1/usuario"};
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Bean
    UserDetailsService userDetailsService() {
        return new UsuarioDetalheService();
    }
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
		return http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> 
                	auth.requestMatchers(HttpMethod.POST, PATH_PUBLICO_POST).permitAll().anyRequest().authenticated()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new JWTAutorizacaoFiltro(this.jwtUtil, (UsuarioDetalheService) userDetailsService()),
                        UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(bCryptPasswordEnconder());
	 
	    return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) 
			throws Exception {
	    return authConfig.getAuthenticationManager();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("PATCH");
		corsConfiguration.addAllowedMethod("DELETE");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
}
