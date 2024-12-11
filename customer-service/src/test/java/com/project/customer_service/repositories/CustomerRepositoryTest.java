package com.project.customer_service.repositories;

import com.project.customer_service.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // indiquer à spring de demarrer tous qui concerne de JPA comme le DB
@ActiveProfiles("test")
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach // avant d'éxécutée  chaque test , on va ajouter quelque données dans la base
    void setUp(){
        customerRepository.save(Customer.builder()
                .firstName("Tantely")
                .lastName("Ravoson")
                .email("ravo@gmail.com")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("James")
                .lastName("Lodwige")
                .email("james@gmail.com")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Julin")
                .lastName("Nambinintsoa")
                .email("julien@gmail.com")
                .build());
    }

    @Test
    public void shouldFindCustomerByEmail(){
        String givenEmail="ravo@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isPresent();
    }

    @Test
    public void shouldNotFindCustomerByEmail(){
        String givenEmail="ravosoa@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }


    @Test
    public void shouldFindByCustomerByFirstName(){
        String keyword="e";
        List<Customer> expected = List.of(
                Customer.builder()
                        .firstName("Tantely")
                        .lastName("Ravoson")
                        .email("ravo@gmail.com")
                        .build(),
                Customer.builder()
                        .firstName("James")
                        .lastName("Lodwige")
                        .email("james@gmail.com")
                        .build()
        );
        List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);

    }



}