package com.portiz.nttdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portiz.nttdata.model.Account;

public interface IAccountRepo extends JpaRepository<Account, String> {

}
