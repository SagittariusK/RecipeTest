package com.recipe.vo;

public class JhhVO {
	private String fname = "";
	private String lname = "";
	
	public JhhVO() {
		super();
	}

	public JhhVO(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "JhhVO [fname=" + fname + ", lname=" + lname + "]";
	}
	
}
