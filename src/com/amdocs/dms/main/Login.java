package com.amdocs.dms.main;

import com.amdocs.dms.dao.Authentication;
import java.util.*;


public class Login {
	
	private static String loginId;
	private static String password; 
	
	public static void main(String args[]) {
		
		Scanner sc =  new Scanner(System.in);
		while(true) {
		System.out.print("***********************************************************************************"
				+ "\n Username: ");
		loginId=sc.nextLine();
		System.out.print(" Passsword: ");
		System.out.flush();
        System.err.print("\033[8m"); 
        password = sc.nextLine();
        System.err.print("\033[0m"); 
		
		if(Authentication.auth(loginId,password)==true) {
			System.out.println(" LOGIN SUCCESSFUL \n");
			Menu ui = new Menu();
			ui.menu();
			sc.close();
			break;
		}else {
			System.out.println("PLEASE ENTER CORRECT CREDENTIALS");
			continue;
		}
			
	}
		sc.close();
	}

}
