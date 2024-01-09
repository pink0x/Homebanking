package com.mindhub.homebaking;

import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

//    @Autowired
//    public PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner initData(ClientRepository clientRepository,
//                                      AccountRepository accountRepository,
//                                      TransactionRepository transactionRepository,
//                                      LoanRepository loanRepository,
//                                      ClientLoanRepository clientLoanRepository,
//                                      CardRepository cardRepository) {
//
//
//        return args -> {
//
////ADMIN
//
//
////CLIENTS
//            Client melba = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123456"), RoleType.CLIENT);
//            Client juanTopo = new Client("Juan Topo", "Makelele", "juantopoMake@lele.com", passwordEncoder.encode("123456"), RoleType.CLIENT);
//            Client farfaria = new Client("Farafaria", "Lacomilla", "fafafa@laco.com", passwordEncoder.encode("123456"), RoleType.CLIENT);
//
//
//            clientRepository.save(melba);
//            clientRepository.save(juanTopo);
//            clientRepository.save(farfaria);
//
////ACCOUNTS
//            Account account1 = new Account("VIN-001", 5000d, LocalDate.now());
//            Account account2 = new Account("VIN-002", 7500d, LocalDate.now().plusDays(1));
//
//
//            melba.addAccount(account1);
//            melba.addAccount(account2);
//
//            accountRepository.save(account1);
//            accountRepository.save(account2);
//
////TRANSACTIONS
//            Transaction trans1 = new Transaction(TransactionType.CREDIT, 1200, "Happy birthday, aint Melba!", LocalDate.now());
//            Transaction trans2 = new Transaction(TransactionType.DEBIT, -850, "Rent payment", LocalDate.now());
//
//            Transaction trans3 = new Transaction(TransactionType.CREDIT, 345, "Pension payment", LocalDate.now());
//            Transaction trans4 = new Transaction(TransactionType.DEBIT, -1500, "Plane tickets", LocalDate.now());
//
//            account1.addTransaction(trans1);
//            account1.addTransaction(trans2);
//
//            account2.addTransaction(trans3);
//            account2.addTransaction(trans4);
//
//
//            transactionRepository.save(trans1);
//            transactionRepository.save(trans2);
//
//            transactionRepository.save(trans3);
//            transactionRepository.save(trans4);
//
//
////LOANS
//            Loan hipotecario = new Loan("Hipotecario", 500000, List.of(12, 24, 36, 48, 60));
//            Loan personal = new Loan("Personal", 100000, List.of(6, 12, 24));
//            Loan automotriz = new Loan("automotriz", 300000, List.of(6, 12, 24, 36));
//
//
//            loanRepository.save(hipotecario);
//            loanRepository.save(personal);
//            loanRepository.save(automotriz);
//
//
////CLIENTS LOANS
//            ClientLoan melbaHipotecario = new ClientLoan("hipotecario", 400000d, 60);
//            ClientLoan melbaPersonal = new ClientLoan("personal", 50000d, 12);
//            ClientLoan juanPersonal = new ClientLoan("personal", 50000d, 12);
//
//
//            melba.addClientLoan(melbaHipotecario);
//            hipotecario.addClientLoan(melbaHipotecario);
//
//            melba.addClientLoan(melbaPersonal);
//            personal.addClientLoan(melbaPersonal);
//
//            juanTopo.addClientLoan((juanPersonal));
//            personal.addClientLoan(juanPersonal);
//
//            clientLoanRepository.save(juanPersonal);
//            clientLoanRepository.save(melbaHipotecario);
//            clientLoanRepository.save(melbaPersonal);
//
//
////CARDS
//            Card melbaCard1 = new Card(CardColor.GOLD, CardType.CREDIT, "2212 5896 0089 4563", 598, LocalDate.now(), LocalDate.now().plusYears(5));
//            Card melbaCard2 = new Card(CardColor.TITANIUM, CardType.DEBIT, "2254 8963 4598 6654", 859, LocalDate.now(), LocalDate.now().plusYears(5));
//            Card juanTopoCard = new Card(CardColor.SILVER, CardType.CREDIT, "2589 6932 5899 1145", 895, LocalDate.now(), LocalDate.now().plusYears(5));
//
//            melba.addCard(melbaCard1);
//            melba.addCard(melbaCard2);
//            juanTopo.addCard(juanTopoCard);
//
//            cardRepository.save(melbaCard1);
//            cardRepository.save(melbaCard2);
//            cardRepository.save(juanTopoCard);
//
//
//        };
//    }
}
