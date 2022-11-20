package com.dividend.test;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class JenkinsUpdate {
	
	@Test
	public void Jenkinsupdate() throws FilloException {
		
		Fillo fillo = new Fillo();
		String filepath="C:\\Users\\Sandeep\\Desktop\\NewUpdate.xlsx";
		Connection conn = fillo.getConnection(filepath);
		String strquery = "Select * from Data where STATUS!='Done'";
		Recordset rs = conn.executeQuery(strquery);
		
		while (rs.next()) {
			
			System.out.print(rs.getField("APPNAME"));
			System.out.print("---");
			System.out.print(rs.getField("FIRSTNAME"));
			System.out.print("---");
			System.out.print(rs.getField("LASTNAME"));
			System.out.print("---");
			System.out.print(rs.getField("STATUS"));
			System.out.println();
		}
		

	}

}
