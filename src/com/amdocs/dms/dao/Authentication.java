package com.amdocs.dms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Authentication{
    public static boolean auth(String userID, String password) {

        boolean response = false;
        Connection con= null;

        try {
           
        	con = DBConnection.getConnection();
            String query = "Select * from Auth where USERID='"+userID+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
        	
            if(rs.getString("PASS").equals(password)) {
            	response=true;
            }
           
        } 
        
        catch (Exception e) {
        	 e.getMessage();
        }
        finally {
        	 DBConnection.closeConnection(con);
        }
        
        return response;
    }
}
