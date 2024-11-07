package com.bank.serviceImple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.bank.configure.DBUtil;
import com.bank.model.Account;
import com.bank.service.RBI;

public class Axis implements RBI{

	Scanner sc=new Scanner(System.in);
	@Override
	public void createAccount() throws ClassNotFoundException, SQLException {
		Account ac = new Account();	
	
		System.out.println("Welcome To Account Opening Section In Axis Bank...!");
		System.out.println("      ");
		
		System.out.println("Enter Account Number");
		long acno=sc.nextLong();
		ac.setAcno(acno);
		
		System.out.println("Enter Account Holder Name ");
		String acname=sc.next();
		ac.setAcname(acname);
		
		System.out.println("Enter Account Holder Address");
		String address=sc.next();
		ac.setAddress(address);
		
		System.out.println("Enter Account Holder Mobile No ");
		long mobileno=sc.nextLong();
		ac.setMobileno(mobileno);
		
		System.out.println("Enter Account Holder Adhar No ");
		long adharno=sc.nextLong();
		ac.setAdharno(adharno);
		
		System.out.println("Enter Account Holder Pan No ");
		String panno=sc.next();
		ac.setPanno(panno);
		
		System.out.println("Enter Account Holder Gender ");
		String gender=sc.next();
		ac.setGender(gender);
		
		System.out.println("Enter Opening Balanced ");
		double balance=sc.nextDouble();
		ac.setBalance(balance);
		
		System.out.println("Account Has Been Created Successfully...!");
		System.out.println("============================");
		System.out.println("                     ");
		
		Connection con=DBUtil.getConnection();
		//String sql="create table Account(acno long, acname varChar(45), address varchar(56), mobileno long, adharno long, panno varChar(45), gender varchar(56), balance double)";
		String sql1 = "insert into Account values(?,?,?,?,?,?,?,?)";
	    //PreparedStatement ps = con.prepareStatement(sql);
		PreparedStatement ps1 = con.prepareStatement(sql1);
		
		ps1.setLong(1, acno);
		ps1.setString(2, ac.getAcname());
		ps1.setString(3, ac.getAddress());
		ps1.setLong(4, ac.getMobileno());
		ps1.setLong(5, ac.getAdharno());
		ps1.setString(6, ac.getPanno());
		ps1.setString(7, ac.getGender());
		ps1.setDouble(8, ac.getBalance());
		
	    //ps.execute();
		ps1.execute();
		
		
	}
	

	@Override
	public void viewAccountDetails() throws ClassNotFoundException, SQLException {
		
		Connection con = DBUtil.getConnection();
		
		System.out.println("Enter Your Account Number To See Details");
		
		String sql="select * from account where acno=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, sc.nextLong());
		ResultSet rs = ps.executeQuery();
 
		boolean flag = rs.next();
		
		if (flag) {
			
			long acno = rs.getLong(1);
			System.out.println("Account Number : " + acno);
			System.out.println("Account Holder Name : " + rs.getString(2));
			System.out.println("Account Holder Address : " + rs.getString(3));
			System.out.println("Account Holder MobileNo : " + rs.getLong(4));
			System.out.println("Account Holder AdharNo : " + rs.getLong(5));
			System.out.println("Account Holder PanNo : " + rs.getString(6));
			System.out.println("Account Holder Gender : " + rs.getString(7));
			System.out.println("Account Holder Balance : " + rs.getDouble("balance"));
			System.out.println( );
			
		}else {
			System.out.println("Invalide Account Number...! \n PLz Enter Correct Account Number...!");
			System.out.println(  );
			
		}
	}

	@Override
	public void Depositemoney() throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println("Enter Account Number To Deposite Money");
		long acno=sc.nextLong();
		
		String sql="select balance from Account where acno = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, acno);
		ResultSet rs = ps.executeQuery();
		boolean flag = rs.next();
		
		if (flag) {
			double avbalance = rs.getDouble("balance");
			System.out.println("Enter Amount to deposite : ");
			double demo = sc.nextDouble();
			
			double newbalance = avbalance+demo;
			
			PreparedStatement pr = con.prepareStatement("update account set balance = ? where acno = ? ");
			pr.setDouble(1, newbalance);
			pr.setLong(2, acno);
			
			pr.execute();
			
			System.out.println("Amount Deposited Succesfully...!");
			System.out.println();
		}else {
			System.out.println("Invalide Account Number \n Plz Enter Correct Account Number...!");
			System.out.println("           ");
			
		}
	}

	@Override
	public void WithdrawlMoney() throws ClassNotFoundException, SQLException {
				Connection con = DBUtil.getConnection();
				System.out.println("Enter Account Number To Withdrawl Money...");
				long acno=sc.nextLong();
				
				
				String sql="select balance from Account where acno = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, acno);
				ResultSet rs = ps.executeQuery();
				boolean flag = rs.next();
				
				if (flag) {
					double avbalance = rs.getDouble("balance");
					System.out.println("Enter Amount to wihdrawl : ");
					double demo = sc.nextDouble();
					
					if(avbalance > demo) {
					double newbalance = avbalance-demo;
					
					PreparedStatement pr = con.prepareStatement("update account set balance = ? where acno = ? ");
					pr.setDouble(1, newbalance);
					pr.setLong(2, acno);
					
					pr.execute();
					
					System.out.println("Amount Withdrewl Succesfully...!");
					System.out.println();
					}
					else {
						System.out.println("Insufficient Balance...");
					}
				}else {
					System.out.println("Invalide Account Number \n Plz Enter Correct Account Number...!");
					System.out.println("           ");
					
				}
	}

	@Override
	public void Showbalances() throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println("Enter Account Number To show Balance...");
		long acno=sc.nextLong();
		
		String sql="select balance from account where acno = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, acno);
		ResultSet rs = ps.executeQuery();
          boolean flag = rs.next();
          if (flag) {
			double avbalance = rs.getDouble("balance");
			System.out.println("Balance is : " + avbalance);
			}else {
				System.out.println("Invalide Account Number \n Plz Enter Correct Account Number...!");
				System.out.println("           ");
				
			}
		
	}

	@Override
	public void updateDetails() throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println("Enter Account Number To update Details...");
		long acno=sc.nextLong();
		Scanner sc=new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("Press 1 for update name : ");
			System.out.println("Press 2 for update address : ");
			
			int ch =sc.nextInt();
			switch (ch){
			case 1:
				System.out.println("Enter Your update name ");
				String sql="update account set acname = ? where acno = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, sc.next());
				ps.setLong(2, acno);
				ps.execute();
				
				System.out.println("Your Name Updated Successfully...!");
				
				break;
			case 2:
				System.out.println("Enter Your Address name ");
				String sql1="update account set address = ? where acno = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, sc.next());
				ps1.setLong(2, acno);
				ps1.execute();
				
				System.out.println("Your Address Updated Successfully...");
				
				break;


			default:
				break;
			}
		}
		
	}

}
