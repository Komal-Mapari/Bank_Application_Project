package com.bank.admin;

import java.sql.SQLException;

import java.util.Scanner;
import com.bank.service.RBI;
import com.bank.serviceImple.Axis;

public class BankApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RBI r = new Axis();
		Scanner sc=new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("Press 1 for create Account ");
			System.out.println("Press 2 for View Account Details ");
			System.out.println("Press 3 for Deposite Money ");
			System.out.println("Press 4 for withdrawl Money ");
			System.out.println("Press 5 for Show Balance ");
			System.out.println("Press 6 for Upadate Details");
			
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				 r.createAccount();
				break;
			case 2:
				 r.viewAccountDetails();
				break;
			case 3:
				 r.Depositemoney();
				break;
			case 4:
				 r.WithdrawlMoney();
				break;
			case 5:
				 r.Showbalances();
				break;
			case 6:
				 r.updateDetails();
				break;
            default:
				break;
			}
			
		}
		
	}
}
