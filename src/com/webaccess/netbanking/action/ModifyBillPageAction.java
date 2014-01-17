/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;

/**
 * Started On: Nov 06 2013
 * 
 * Ended On: Nov 06 2013
 * 
 * @author jay Prakash
 */
public class ModifyBillPageAction extends ActionSupport implements SessionAware {

	BillDelegate billDelegate = new BillDelegate();
	ArrayList<?> stateList = new ArrayList<Object>();
	ArrayList<?> billerList = new ArrayList<Object>();
	Map<String, Object> session;
	ArrayList<?> electricityBillList = new ArrayList<Object>();
	ArrayList<?> sbiLifeInsurenceList = new ArrayList<Object>();
	ArrayList<?> bsnlCelloneBillList = new ArrayList<Object>();
	CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
	ArrayList<?> creditCardDetailList = new ArrayList<Object>();

	public ArrayList<?> getCreditCardDetailList() {
		return creditCardDetailList;
	}

	public void setCreditCardDetailList(ArrayList<?> creditCardDetailList) {
		this.creditCardDetailList = creditCardDetailList;
	}

	public ArrayList<?> getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList<?> stateList) {
		this.stateList = stateList;
	}

	public ArrayList<?> getBillerList() {
		return billerList;
	}

	public void setBillerList(ArrayList<?> billerList) {
		this.billerList = billerList;
	}

	public ArrayList<?> getElectricityBillList() {
		return electricityBillList;
	}

	public void setElectricityBillList(ArrayList<?> electricityBillList) {
		this.electricityBillList = electricityBillList;
	}

	public ArrayList<?> getSbiLifeInsurenceList() {
		return sbiLifeInsurenceList;
	}

	public void setSbiLifeInsurenceList(ArrayList<?> sbiLifeInsurenceList) {
		this.sbiLifeInsurenceList = sbiLifeInsurenceList;
	}

	public ArrayList<?> getBsnlCelloneBillList() {
		return bsnlCelloneBillList;
	}

	public void setBsnlCelloneBillList(ArrayList<?> bsnlCelloneBillList) {
		this.bsnlCelloneBillList = bsnlCelloneBillList;
	}

	// forwarding to home page
	public String myAccount() {
		return "myAccount";
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// forwarding to transfer page
	public String transfer() {
		return "transfer";
	}

	// forwarding to bill page with state and biller list
	public String billPayment() {
		billerList = billDelegate.getBiller();
		stateList = billDelegate.getState();
		return "billPayment";
	}

	// forwarding to card detail page with card detail list
	public String cardDetail() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean = (UserInfoBean) session.get("userInfo");
		if (userInfoBean == null) {
			return "error";
		} else {
			int accountNumber = userInfoBean.getAccountNumber();
			creditCardDetailList = creditCardDelegate
					.getCreditCardetailList(accountNumber);
			return "cardDetail";
		}
	}

	// forwarding personal detail page
	public String personalDetail() {
		return "personalDetail";
	}

	// forwarding to add bill page with state and biller list
	public String addBill() {
		billerList = billDelegate.getBiller();
		stateList = billDelegate.getState();
		return "billPayment";
	}

	// forwarding to modify bill page
	public String modifyBill() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean = (UserInfoBean) session.get("userInfo");
		if (userInfoBean == null) {
			return "error";
		} else {
			int accountnumber = userInfoBean.getAccountNumber();
			// getting all electric bill list
			electricityBillList = billDelegate
					.getElectricityBillList(accountnumber);
			// getting all BSNL bill list
			bsnlCelloneBillList = billDelegate
					.getBSNLCelloneBillList(accountnumber);
			// getting all SBI life insurence bill list
			sbiLifeInsurenceList = billDelegate
					.getSBILifeInsurenceList(accountnumber);
			return "modifyBill";
		}
	}

	// forwarding to pay bill page
	public String payBill() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean = (UserInfoBean) session.get("userInfo");
		if (userInfoBean == null) {
			return "error";
		} else {
			int accountnumber = userInfoBean.getAccountNumber();
			// getting all electric bill list
			electricityBillList = billDelegate
					.getElectricityBillList(accountnumber);
			// getting all BSNL bill list
			bsnlCelloneBillList = billDelegate
					.getBSNLCelloneBillList(accountnumber);
			// getting all SBi life insurence bill list
			sbiLifeInsurenceList = billDelegate
					.getSBILifeInsurenceList(accountnumber);
			return "payBill";
		}
	}
}
