package com.mindhub.homebaking;

import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

    @Bean
	public CommandLineRunner initData (ClientRepository clientRepository,
									   AccountRepository accountRepository,
									   TransactionRepository transactionRepository,
									   LoanRepository loanRepository,
									   ClientLoanRepository clientLoanRepository) {


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

			Transaction trans1 = new Transaction(TransactionType.CREDIT, 1200, "Happy birthday, aint Melba!", LocalDate.now() );
			Transaction trans2 = new Transaction(TransactionType.DEBIT, -850, "Rent payment", LocalDate.now());

			Transaction trans3 = new Transaction(TransactionType.CREDIT, 345, "Pension payment", LocalDate.now() );
			Transaction trans4 = new Transaction(TransactionType.DEBIT, -1500, "Plane tickets", LocalDate.now());

			account1.addTransaction (trans1);
			account1.addTransaction(trans2);

			account2.addTransaction (trans3);
			account2.addTransaction(trans4);


			transactionRepository.save(trans1);
			transactionRepository.save(trans2);

			transactionRepository.save(trans3);
			transactionRepository.save(trans4);



			Loan hipotecario = new Loan("Hipotecario", 500000, List.of(12,24,36,48,60));
     		Loan personal = new Loan("Personal", 100000, List.of(6,12,24));
			Loan automotriz = new Loan("automotriz", 300000, List.of(6,12,24,36));



			loanRepository.save(hipotecario);
			loanRepository.save(personal);
			loanRepository.save(automotriz);

			ClientLoan melbaHipotecario = new ClientLoan("hipotecario",400000d, 60);
			ClientLoan melbaPersonal = new ClientLoan("personal",50000d, 12);

			ClientLoan juanPersonal = new ClientLoan("personal", 50000d,12);


			melba.addClientLoan(melbaHipotecario);
			hipotecario.addClientLoan (melbaHipotecario);

			melba.addClientLoan(melbaPersonal);
			personal.addClientLoan(melbaPersonal);

			juanTopo.addClientLoan((juanPersonal));
			personal.addClientLoan(juanPersonal);

			clientLoanRepository.save(juanPersonal);
			clientLoanRepository.save(melbaHipotecario);
			clientLoanRepository.save(melbaPersonal);






		};
	}
}
