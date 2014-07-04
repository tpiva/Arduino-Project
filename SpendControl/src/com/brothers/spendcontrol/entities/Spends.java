package com.brothers.spendcontrol.entities;

public class Spends {

	private String payDay;
	private String description;
	private String nameOfSpend;
	private String currentMounth;
	private String constantlySpend;
	private Integer idInframe;
	private Integer IdSpends;
	private Double value;
	private String dateRegister;
	private String duoDate;
	
	public Spends(String payDay, String description, String nameOfSpend,
			String currentMounth) {
		this.payDay = payDay;
		this.description = description;
		this.nameOfSpend = nameOfSpend;
		this.currentMounth = currentMounth;
	}
	
	public Spends(String nameOfSpend, Double value) {
		this.nameOfSpend = nameOfSpend;
		this.value = value;
	}
	
	public Spends(String payDay, String description, String nameOfSpend,
			String currentMounth, String constantlySpend, Integer idInframe,
			Integer idSpends, Double value, String dateRegister, String duoDate) {
		super();
		this.payDay = payDay;
		this.description = description;
		this.nameOfSpend = nameOfSpend;
		this.currentMounth = currentMounth;
		this.constantlySpend = constantlySpend;
		this.idInframe = idInframe;
		IdSpends = idSpends;
		this.value = value;
		this.dateRegister = dateRegister;
		this.duoDate = duoDate;
	}
	
	public Spends(String payDay, String description, String nameOfSpend,
			String currentMounth, String constantlySpend, Integer idInframe,
			Double value, String dateRegister, String duoDate) {
		super();
		this.payDay = payDay;
		this.description = description;
		this.nameOfSpend = nameOfSpend;
		this.currentMounth = currentMounth;
		this.constantlySpend = constantlySpend;
		this.idInframe = idInframe;
		this.value = value;
		this.dateRegister = dateRegister;
		this.duoDate = duoDate;
	}

	public String getPayDay() {
		return payDay;
	}
	
	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNameOfSpend() {
		return nameOfSpend;
	}
	public void setNameOfSpend(String nameOfSpend) {
		this.nameOfSpend = nameOfSpend;
	}
	
	public String getCurrentMounth() {
		return currentMounth;
	}
	
	public void setCurrentMounth(String currentMounth) {
		this.currentMounth = currentMounth;
	}
	
	public String getConstantlySpend() {
		return constantlySpend;
	}
	
	public void setConstantlySpend(String constantlySpend) {
		this.constantlySpend = constantlySpend;
	}
	
	public Integer getIdInframe() {
		return idInframe;
	}
	
	public void setIdInframe(Integer idInframe) {
		this.idInframe = idInframe;
	}
	
	public Integer getIdSpends() {
		return IdSpends;
	}
	
	public void setIdSpends(Integer idSpends) {
		IdSpends = idSpends;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

	public String getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getDuoDate() {
		return duoDate;
	}

	public void setDuoDate(String duoDate) {
		this.duoDate = duoDate;
	}
}
