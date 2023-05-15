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

import com.portiz.nttdata.model.Movement;
import com.portiz.nttdata.repo.IMovementRepo;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

	@Autowired
	private IMovementRepo movementRepo;

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
	public void insertMovement(@RequestBody Movement mov) {
		movementRepo.save(mov);
	}

	@PutMapping
	public void updateMovement(@RequestBody Movement mov) {
		movementRepo.save(mov);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteMovement(@PathVariable("id") Integer id) {
		movementRepo.deleteById(id);
	}
}
