package com.semicolon.easyaccount.data.repository;

import com.semicolon.easyaccount.data.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
}
