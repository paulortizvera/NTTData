package com.portiz.nttdata.dto;

import java.util.List;

import com.portiz.nttdata.model.Account;
import com.portiz.nttdata.model.Movement;

import lombok.Data;

@Data
public class ReportAccountStatus {
	private List<Account> accounts;
	private List<Movement> movements;
}
