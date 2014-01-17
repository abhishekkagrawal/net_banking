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
 * Started On :Oct 17 2013
 * 
 * Ended On :Oct 17 2013
 * 
 * @author Jay Prakash
 */
// preforming mini statement page action
public class MiniStatementPageAction extends ActionSupport implements
		SessionAware {

	ArrayList<?> transactionDeatailList = new ArrayList<Object>();
	ArrayList<?> stateList = new ArrayList<Object>();
	ArrayList<?> billerList = new ArrayList<Object>();
	CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
	ArrayList<?> creditCardDetailList = new ArrayList<Object>();
	Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

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

	public ArrayList<?> getTransactionDeatailList() {
		return transactionDeatailList;
	}

	public void setTransactionDeatailList(ArrayList<?> transactionDeatailList) {
		this.transactionDeatailList = transactionDeatailList;
	}

	// forwarding to account detail page
	public String accountDetail() {
		return "accountDetail";
	}

	// forwarding to view and save page
	public String viewaAndSaveStatement() {
		return "viewAndSaveStatemant";
	}

	// forwarding to home page
	public String myAccount() {
		return "myAccount";
	}

	// forwarding to transfer page
	public String transfer() {
		return "transfer";
	}

	// forwarding to bill page with state and biller list
	public String billPayment() {
		BillDelegate billDelegate = new BillDelegate();
		billerList = billDelegate.getBiller();
		stateList = billDelegate.getState();
		return "billPayment";
	}

	// forwarding to card detail page
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

	// forwarding persional detail page
	public String personalDetail() {
		return "personalDetail";
	}
}
