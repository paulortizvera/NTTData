package com.portiz.nttdata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portiz.nttdata.model.Account;
import com.portiz.nttdata.model.Client;

public interface IAccountRepo extends JpaRepository<Account, String> {

	List<Account> findAllByClientId(int id);

}
