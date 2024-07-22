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
import com.portiz.nttdata.model.Movement;
import com.portiz.nttdata.repo.IAccountRepo;
import com.portiz.nttdata.repo.IMovementRepo;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

	@Autowired
	private IMovementRepo movementRepo;
	@Autowired
	private IAccountRepo accountRepo;

	@GetMapping
	public ResponseEntity<List<Movement>> getMovement() {
		try {
			List<Movement> mov = movementRepo.findAll();
			if (mov == null) {
				return new ResponseEntity<List<Movement>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<Movement>>(mov, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Movement>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Movement>> getMovementById(@PathVariable("id") Integer id) {
		try {
			Optional<Movement> mov = movementRepo.findById(id);
			if (mov == null) {
				return new ResponseEntity<Optional<Movement>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Optional<Movement>>(mov, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Optional<Movement>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<Movement> insertMovement(@RequestBody Movement mov) {
		Movement exist = movementRepo.findByAccountAndStatus(mov.getAccount(), "T");
		double oldBalance = 0, newBalance = 0;
		if (exist == null) {
			Optional<Account> acc = accountRepo.findById(mov.getAccount());
			if (acc.isEmpty()) {
				throw new BusinessException("E-200", HttpStatus.BAD_REQUEST, "La cuenta no existe");
			} else {
				oldBalance = acc.get().getInitialBalance();
			}
		} else {
			oldBalance = exist.getBalance();
			exist.setStatus("F");
			movementRepo.save(exist);
		}
		newBalance = oldBalance + mov.getValue();
		mov.setType("D");
		if (newBalance < 0) {
			throw new BusinessException("E-303", HttpStatus.BAD_REQUEST, "Saldo no disponible");
		} else if (newBalance < oldBalance) {
			mov.setType("R");
		}

		mov.setBalance(newBalance);
		movementRepo.save(mov);
		return new ResponseEntity<Movement>(mov, HttpStatus.OK);
	}
}
