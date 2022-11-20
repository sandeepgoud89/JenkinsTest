package com.dividend.test;

import java.util.ArrayList;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Excelreadwrite {

	Fillo fillo = new Fillo();

	public List<String> GetTickervalues(String filepath) throws FilloException {

		List<String> ticker = new ArrayList<String>();

		Connection conn = fillo.getConnection(filepath);
		String strquery = "Select * from TestData";
		Recordset rs = conn.executeQuery(strquery);

		while (rs.next()) {
			ticker.add(rs.getField("TICKER"));
		}

		rs.close();
		conn.close();

		return ticker;
	}

	public void UpdateData(String filepath, String ExDividendDate, String DividendYied, String AnnualDividend,
		String PEratio, String Ticker) throws FilloException {

		Connection conn = fillo.getConnection(filepath);

		String strQuery = "Update TestData Set EX_DIVIDEND_DATE='" + ExDividendDate + "',DIVIDEND_YIELD='"
				+ DividendYied + "',ANNUAL_DIVIDEND='" + AnnualDividend + "',PE_RATIO='" + PEratio + "' where TICKER='"
				+ Ticker + "'";

		conn.executeUpdate(strQuery);

		conn.close();
	}

}
