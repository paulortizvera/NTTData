package com.portiz.nttdata.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portiz.nttdata.dto.ReportAccountStatus;
import com.portiz.nttdata.exception.BusinessException;
import com.portiz.nttdata.model.Account;
import com.portiz.nttdata.model.Client;
import com.portiz.nttdata.model.Movement;
import com.portiz.nttdata.repo.IAccountRepo;
import com.portiz.nttdata.repo.IClientRepo;
import com.portiz.nttdata.repo.IMovementRepo;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	@Autowired
	private IClientRepo clientRepo;
	@Autowired
	private IAccountRepo accountRepo;
	@Autowired
	private IMovementRepo movementRepo;

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
	public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
		Optional<Client> cli = clientRepo.findById(id);
		if (cli.isEmpty()) {
			throw new BusinessException("E-100", HttpStatus.BAD_REQUEST, "El cliente no existe");
		} else {
			try {
				return new ResponseEntity<Client>(cli.get(), HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}

	@PostMapping
	public ResponseEntity<Client> insertClient(@RequestBody Client cli) {
		Optional<Client> exist = clientRepo.findById(cli.getIdentification());
		if (exist.isEmpty()) {
			try {
				clientRepo.save(cli);
				return new ResponseEntity<Client>(cli, HttpStatus.CREATED);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		} else {
			throw new BusinessException("E-101", HttpStatus.BAD_REQUEST, "El cliente ya existe");
		}
	}

	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client cli) {
		Optional<Client> exist = clientRepo.findById(cli.getIdentification());
		if (exist.isEmpty()) {
			throw new BusinessException("E-100", HttpStatus.BAD_REQUEST, "El cliente no existe");
		} else {
			try {
				clientRepo.save(cli);
				return new ResponseEntity<Client>(cli, HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable("id") String id) {
		Optional<Client> exist = clientRepo.findById(id);
		if (exist.isEmpty()) {
			throw new BusinessException("E-100", HttpStatus.BAD_REQUEST, "El cliente no existe");
		} else {
			try {
				clientRepo.deleteById(id);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch (Exception e) {
				throw new BusinessException("E-000", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
			}
		}
	}

	/* REPORTE DE ESTADO DE CUENTA */
	@GetMapping(value = "/{id}/reportes")
	public ResponseEntity<ReportAccountStatus> getReportClient(@PathVariable("id") String id,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicial,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal) {

		Optional<Client> cli = clientRepo.findById(id);
		if (cli.isEmpty()) {
			throw new BusinessException("E-100", HttpStatus.BAD_REQUEST, "El cliente no existe");
		} else {
			// try {
			List<Account> accs = accountRepo.findAllByClientId(cli.get().getClientId());
			if (accs.isEmpty()) {
				throw new BusinessException("E-202", HttpStatus.BAD_REQUEST, "No existen cuentas asociadas al cliente");
			} else {
				List<Movement> movs = movementRepo.findAll();
				if (movs.isEmpty()) {
					throw new BusinessException("E-100", HttpStatus.BAD_REQUEST, "El cliente no existe");
				} else {
					List<Movement> finalMovements = new ArrayList<Movement>();
					for (Movement movement : movs) {
						if (movement.getDate().compareTo(fechaInicial) >= 0
								&& movement.getDate().compareTo(fechaFinal) <= 0) {
							finalMovements.add(movement);
						}
					}
					ReportAccountStatus report = new ReportAccountStatus();
					report.setAccounts(accs);
					report.setMovements(finalMovements);
					return new ResponseEntity<ReportAccountStatus>(report, HttpStatus.OK);
				}
			}
			/*
			 * } catch (Exception e) { throw new BusinessException("E-000",
			 * HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor"); }
			 */
		}
	}
}
