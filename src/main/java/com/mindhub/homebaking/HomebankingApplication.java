package com.mindhub.homebaking;

import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

    @Bean
	public CommandLineRunner initData (ClientRepository clientRepository){
		return args -> {


		   Client melba = new Client ("Melba", "Morel", "melba@mindhub.com");
		   clientRepository.save(melba);

		   Client juanTopo = new Client("Juan Topo", "Makelele", "juantopoMake@lele.com");
		   clientRepository.save(juanTopo);

		   Client farfaria = new Client ("Farafaria", "Lacomilla", "fafafa@laco.com");
		   clientRepository.save(farfaria);
		};
	}
}
