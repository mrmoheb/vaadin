package com.aimsio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DBDriver {
	
	private static String 
			hostname = "aimsio-assignment.cutnmbp0viai.us-west-2.rds.amazonaws.com",
			username = "user0326",
			password = "9g5G4RK",
			database = "assets";
	
	public List<String> assetUNs(){  
		List <String> assets = new ArrayList<String>();
		try{   
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://"+DBDriver.hostname+":3306/"+DBDriver.database+"",""+DBDriver.username+"",""+DBDriver.password+"");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT AssetUN from assets.signal GROUP BY AssetUN ORDER BY AssetUN asc");  
			while(rs.next())  
				assets.add(rs.getString("AssetUN"));
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
	return assets;		
	}
	
	public List <AssetUN> getAssetData (String assetUN,String status){
		List <AssetUN> data = new ArrayList();
		try{
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://"+DBDriver.hostname+":3306/"+DBDriver.database+"",""+DBDriver.username+"",""+DBDriver.password+"");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT AssetUN,status,entry_date from assets.signal where AssetUN='"+assetUN+"' AND status='"+status+"' ORDER BY status asc");  
			while(rs.next()) {
				AssetUN temp = new AssetUN();
				temp.assetUN = rs.getString("AssetUN");
				temp.entryDate = rs.getString("entry_date");
				temp.status = rs.getString("status");
				data.add(temp);
			}
			con.close();  
			}
		catch(Exception e){ 
				System.out.println(e);
			}
		return data;
	}
}
