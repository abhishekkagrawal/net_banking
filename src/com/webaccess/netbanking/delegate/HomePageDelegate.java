/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import java.util.ArrayList;

import com.webaccess.netbanking.dao.HomePageDao;

/**
 * 
 * @author Administrator
 */
public class HomePageDelegate {

	HomePageDao dao;

	public HomePageDelegate() {
		dao = new HomePageDao();
	}

	public ArrayList<?> getTransactionDetail(int accountNumber) {

		return dao.getTransactionDetail(accountNumber);
	}
}
