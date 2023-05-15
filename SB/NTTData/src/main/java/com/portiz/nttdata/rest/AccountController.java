package com.portiz.nttdata.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portiz.nttdata.model.Account;
import com.portiz.nttdata.repo.IAccountRepo;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

	@Autowired
	private IAccountRepo accountRepo;

	@GetMapping
	public ResponseEntity<List<Account>> getAccount() {
		try {
			List<Account> acc = accountRepo.findAll();
			if (acc == null) {
				return new ResponseEntity<List<Account>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<Account>>(acc, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Account>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Account>> getAccountById(@PathVariable("id") String id) {
		try {
			Optional<Account> acc = accountRepo.findById(id);
			if (acc == null) {
				return new ResponseEntity<Optional<Account>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Optional<Account>>(acc, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Optional<Account>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public void insertAccount(@RequestBody Account acc) {
		accountRepo.save(acc);
	}

	@PutMapping
	public void updateAccount(@RequestBody Account acc) {
		accountRepo.save(acc);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteAccount(@PathVariable("id") String id) {
		accountRepo.deleteById(id);
	}
}
