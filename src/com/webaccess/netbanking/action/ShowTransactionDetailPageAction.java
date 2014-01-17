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
import com.webaccess.netbanking.delegate.HomePageDelegate;
import com.webaccess.netbanking.delegate.ViewAndSavePageDelegate;
import com.webaccess.netbanking.util.SaveInPDF;

/**
 * Started on: Nov 18 2013
 * 
 * Ended On: Nov 20 2013
 * 
 * @author Jay Prakash
 */
public class ShowTransactionDetailPageAction extends ActionSupport implements
		SessionAware {

	Map<String, Object> session;
	HomePageDelegate delegate = new HomePageDelegate();
	ViewAndSavePageDelegate viewAndSavePageDelegate = new ViewAndSavePageDelegate();
	ArrayList<?> transactionDeatailList = new ArrayList<Object>();
	ArrayList<?> stateList = new ArrayList<Object>();
	ArrayList<?> billerList = new ArrayList<Object>();
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

	public ArrayList<?> getTransactionDeatailList() {
		return transactionDeatailList;
	}

	public void setTransactionDeatailList(ArrayList<?> transactionDeatailList) {
		this.transactionDeatailList = transactionDeatailList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// forwarding to home page
	public String back() {
		return "back";
	}

	// forwarding to home page
	public String myAccount() {
		return "myAccount";
	}

	// forwarding to transfer page
	public String transfer() {
		return "transfer";
	}

	// forwarding to bill payment with state and biller list
	public String billPayment() {
		BillDelegate billDelegate = new BillDelegate();
		billerList = billDelegate.getBiller();
		stateList = billDelegate.getState();
		return "billPayment";
	}

	// forwarding to card detail page with with card list
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

	// forwarding to personal detail page
	public String personalDetail() {
		return "personalDetail";
	}

	// forwarding to mini statement page with statement list
	public String miniStatement() {
		UserInfoBean bean = new UserInfoBean();
		bean = (UserInfoBean) session.get("userInfo");
		if (bean == null) {
			return "error";
		} else {
			transactionDeatailList = delegate.getTransactionDetail(bean
					.getAccountNumber());
			return "miniStatement";
		}
	}

	// forward to account detail page
	public String accountDetail() {
		return "accountDetail";
	}

	// forwarding to view and save satement page
	public String viewaAndSaveStatement() {
		return "viewaAndSaveStatement";
	}

	// saving statement
	public String save() {
		ArrayList<?> list = new ArrayList<Object>();
		list = (ArrayList<?>) session.get("transactionList");
		UserInfoBean bean = new UserInfoBean();
		bean = (UserInfoBean) session.get("userInfo");
		if (bean == null) {
			return "error";
		} else {
			SaveInPDF test1 = new SaveInPDF();
			test1.savePDF(bean, list);
			return "myAccount";
		}
	}
}
