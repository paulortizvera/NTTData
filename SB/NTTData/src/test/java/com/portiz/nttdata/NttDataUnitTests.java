package com.portiz.nttdata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.portiz.nttdata.model.Client;
import com.portiz.nttdata.repo.IClientRepo;

@SpringBootTest
class NttDataUnitTests {

	@Autowired
	private IClientRepo clientRepo;
	@MockBean
	private IClientRepo localRepo;

	@BeforeEach
	void setUpd() {
		Client cli = new Client();
		cli.setClientId(1);
		cli.setIdentification("0804362903");
		cli.setName("Marianela Montalvo");
		cli.setGender("F");
		cli.setAge(34);
		cli.setAddress("Amazonas y NNUU");
		cli.setPhone("097548965");
		cli.setPassword("12348/");
		cli.setStatus("T");

		Mockito.when(localRepo.findById("0804362903")).thenReturn(Optional.of(cli));
	}

	@Test
	public void findById() {
		String id = "0804362903";
		Client cli = clientRepo.findById(id).get();
		assertEquals(id, cli.getIdentification());
		System.out.println("Objet cli: " + cli);
	}

}
