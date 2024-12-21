package com.semicolon.easyaccount.data.repository;

import com.semicolon.easyaccount.data.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
