package com.mindhub.homebaking;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.repositories.AccountRepository;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

    @Bean
	public CommandLineRunner initData (ClientRepository clientRepository,
									   AccountRepository accountRepository){


		return args -> {


		   Client melba = new Client ("Melba", "Morel", "melba@mindhub.com");
		   clientRepository.save(melba);

		   Client juanTopo = new Client("Juan Topo", "Makelele", "juantopoMake@lele.com");
		   clientRepository.save(juanTopo);

		   Client farfaria = new Client ("Farafaria", "Lacomilla", "fafafa@laco.com");
		   clientRepository.save(farfaria);


		   Account account1= new Account("VIN-001",5000d, LocalDate.now());


		   Account account2 = new Account("VIN-002", 7500d,LocalDate.now().plusDays(1));


		   melba.addAccount(account1);
		   melba.addAccount(account2);

			accountRepository.save(account1);
			accountRepository.save(account2);
			System.out.println(melba);
		};
	}
}
