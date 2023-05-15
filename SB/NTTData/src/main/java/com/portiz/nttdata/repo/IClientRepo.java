package com.portiz.nttdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portiz.nttdata.model.Client;

public interface IClientRepo extends JpaRepository<Client, String> {

}
