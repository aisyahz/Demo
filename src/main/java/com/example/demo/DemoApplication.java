package com.example.demo;


import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Employee;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;


@SpringBootApplication
@EntityScan("com.example.demo.model")
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;


	@Override
	public void run(String... args) throws Exception{

		/*Employee employee = new Employee();
		employee.setFirstName("Aisyah");
		employee.setLastName("Fadatare");
		employee.setEmailID("ramesh@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("Kim");
		employee1.setLastName("Fadatare");
		employee1.setEmailID("kim@gmail.com");
		employeeRepository.save(employee1);
*/
		// Create a customer
		Customer customer = new Customer();
		customer.setCustomerId("C123");
		customer.setPhoneNumber("1234567890");
		customer.setEmail("customer@example.com");
		customer.setAddress("123 Main St");

		// Create an account for the customer
		Account account = new Account();
		account.setAccountNumber("A12345");
		account.setAccountType("Savings");
		account.setAccountName("Primary");
		account.setAccountBalance(1000.0);
		account.setCustomer(customer);

		//customer.setAccounts(List.of(account));

		// Save the customer and the associated account
		customerRepository.save(customer);
		accountRepository.save(account);
	}
}
