package com.mindhub.homebaking.configurations;


import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
//@RequestMapping("api/clients")
public class WebAuthetication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
     public ClientRepository clientRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName ->{
            Client client = clientRepository.findByEmail(inputName);
            if(client != null){
                return new User(client.getEmail(), client.getPassword(),
                                AuthorityUtils.createAuthorityList(client.getRole().toString()));

            }else {
                throw new UsernameNotFoundException("Quien eres?");
            }


        });
    }


 @Bean
    public PasswordEncoder passwordEncoder (){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
 }

}