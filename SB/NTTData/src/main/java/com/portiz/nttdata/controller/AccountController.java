package com.portiz.nttdata.controller;

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

import com.portiz.nttdata.exception.BusinessException;
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
	public ResponseEntity<Account> getAccountById(@PathVariable("id") String id) {
		Optional<Account> acc = accountRepo.findById(id);
		if (acc.isEmpty()) {
			throw new BusinessException("E-200", HttpStatus.BAD_REQUEST, "La cuenta no existe");
		} else {
			try {
				return new ResponseEntity<Account>(acc.get(), HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}

	@PostMapping
	public ResponseEntity<Object> insertAccount(@RequestBody Account acc) {
		Optional<Account> exist = accountRepo.findById(acc.getNumber());
		if (exist.isEmpty()) {
			try {
				accountRepo.save(acc);
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		} else {
			throw new BusinessException("E-201", HttpStatus.BAD_REQUEST, "La cuenta ya existe");
		}
	}

	@PutMapping
	public ResponseEntity<Object> updateAccount(@RequestBody Account acc) {
		Optional<Account> exist = accountRepo.findById(acc.getNumber());
		if (exist.isEmpty()) {
			throw new BusinessException("E-200", HttpStatus.BAD_REQUEST, "La cuenta no existe");
		} else {
			try {
				accountRepo.save(acc);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteAccount(@PathVariable("id") String id) {
		Optional<Account> exist = accountRepo.findById(id);
		if (exist.isEmpty()) {
			throw new BusinessException("E-200", HttpStatus.BAD_REQUEST, "La cuenta no existe");
		} else {
			try {
				accountRepo.deleteById(id);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}
}
