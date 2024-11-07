package com.bank.service;

import java.sql.SQLException;
import java.sql.*;

public interface RBI {

	void createAccount()throws ClassNotFoundException , SQLException;
	void viewAccountDetails() throws ClassNotFoundException, SQLException;
	void Depositemoney() throws ClassNotFoundException, SQLException;
	void WithdrawlMoney() throws ClassNotFoundException, SQLException;
	void Showbalances() throws ClassNotFoundException, SQLException;
	void updateDetails() throws ClassNotFoundException, SQLException;
}
