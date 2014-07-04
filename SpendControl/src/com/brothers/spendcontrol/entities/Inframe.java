package com.brothers.spendcontrol.entities;

public class Inframe {

	private Integer id;
	private Double inframeValue;
	
	public Inframe(Integer id, Double inframeValue) {
		super();
		this.id = id;
		this.inframeValue = inframeValue;
	}
	
	public Inframe(){};
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getInframeValue() {
		return inframeValue;
	}
	public void setInframeValue(Double inframeValue) {
		this.inframeValue = inframeValue;
	}
	
}
