package com.semicolon.easyaccount.data.repository;

import com.semicolon.easyaccount.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}
