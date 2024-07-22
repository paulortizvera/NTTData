package com.portiz.nttdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portiz.nttdata.model.Movement;

public interface IMovementRepo extends JpaRepository<Movement, Integer> {

	Movement findByAccountAndStatus(String account, String status);

}
