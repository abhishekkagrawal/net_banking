/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BeneficiaryDelegate;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;

/**
 * Started on: Oct 29 2013
 * 
 * Ended on: Nov 10 2013
 * 
 * @author Jay prakash
 */
public class RegisteredBeneficiaryNEFTAction extends ActionSupport implements
		SessionAware {

	Map<String, Object> session;
	ArrayList<?> deactiveBeneficiaryList = new ArrayList<Object>();
	ArrayList<?> activeBeneficiaryList = new ArrayList<Object>();
	BeneficiaryDelegate delegate = new BeneficiaryDelegate();
	ArrayList<?> stateList = new ArrayList<Object>();
	ArrayList<?> billerList = new ArrayList<Object>();
	CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
	ArrayList<?> creditCardDetailList = new ArrayList<Object>();
	ArrayList<?> bankList = new ArrayList<Object>();

	public ArrayList<?> getBankList() {
		return bankList;
	}

	public void setBankList(ArrayList<?> bankList) {
		this.bankList = bankList;
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

	public ArrayList<?> getActiveBeneficiaryList() {
		return activeBeneficiaryList;
	}

	public void setActiveBeneficiaryList(ArrayList<?> activeBeneficiaryList) {
		this.activeBeneficiaryList = activeBeneficiaryList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<?> getDeactiveBeneficiaryList() {
		return deactiveBeneficiaryList;
	}

	public void setDeactiveBeneficiaryList(ArrayList<?> deactiveBeneficiaryList) {
		this.deactiveBeneficiaryList = deactiveBeneficiaryList;
	}

	// forwarding to home page
	public String myAccount() {
		return "myAccount";
	}

	// forwarding to transfer page
	public String transfer() {
		return "transfer";
	}

	// forwarding to bill payment page with biller and state list
	public String billPayment() {
		BillDelegate billDelegate = new BillDelegate();
		billerList = billDelegate.getBiller();
		stateList = billDelegate.getState();
		return "billPayment";
	}

	// forwarding to card detail page with card list
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

	// forwarding to personal detaiol page
	public String personalDetail() {
		return "personalDetail";
	}

	// forwarding to register beneficiary page with bank list
	public String registerBeneficary() {
		bankList = delegate.getBankDetail();
		return "registerBeneficary";
	}

	// forwarding to confirm beneficiary page with deactive beneficiary list
	public String confirAndRejectBeneficiary() {
		LoginInfoBean loginInfoBean = new LoginInfoBean();
		loginInfoBean = (LoginInfoBean) session.get("loginInfo");
		if (loginInfoBean == null) {
			return "error";
		} else {
			int accountNumber = loginInfoBean.getAccountNumber();
			String transactionType = "NEFT";
			deactiveBeneficiaryList = delegate.getDeactiveBeneficiary(
					accountNumber, transactionType);
			return "confirAndRejectBeneficiary";
		}
	}

	// forwarding to transfer page with active beneficiary list
	public String newTransaction() {
		LoginInfoBean loginInfoBean = new LoginInfoBean();
		loginInfoBean = (LoginInfoBean) session.get("loginInfo");
		if (loginInfoBean == null) {
			return "error";
		} else {
			int accountNumber = loginInfoBean.getAccountNumber();
			String transactionType = "NEFT";
			activeBeneficiaryList = delegate.getActiveBeneficiary(
					accountNumber, transactionType);
			return "newTransaction";
		}
	}

	// forwarding to registered beneficiary page with active beneficiary list
	public String registeredBeneficiary() {
		LoginInfoBean loginInfoBean = new LoginInfoBean();
		loginInfoBean = (LoginInfoBean) session.get("loginInfo");
		if (loginInfoBean == null) {
			return "error";
		} else {
			int accountNumber = loginInfoBean.getAccountNumber();
			String transactionType = "NEFT";
			activeBeneficiaryList = delegate.getActiveBeneficiary(
					accountNumber, transactionType);
			return "registeredBeneficiary";
		}
	}

	@Override
	public String execute() {
		return SUCCESS;
	}
}
