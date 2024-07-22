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

import com.portiz.nttdata.model.Client;
import com.portiz.nttdata.repo.IClientRepo;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	/*@Autowired
	private IPersonRepo personRepo;*/

	@Autowired
	private IClientRepo clientRepo;

	@GetMapping
	public ResponseEntity<List<Client>> getClients() {
		try {
			List<Client> cli = clientRepo.findAll();
			if (cli == null) {
				return new ResponseEntity<List<Client>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<Client>>(cli, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Client>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Client>> getClientById(@PathVariable("id") String id) {
		try {
			Optional<Client> cli = clientRepo.findById(id);
			if (cli == null) {
				return new ResponseEntity<Optional<Client>>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Optional<Client>>(cli, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Optional<Client>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public void insertClient(@RequestBody Client cli) {
		clientRepo.save(cli);
	}

	@PutMapping
	public void updateClient(@RequestBody Client cli) {
		clientRepo.save(cli);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteClient(@PathVariable("id") String id) {
		clientRepo.deleteById(id);
	}
}
