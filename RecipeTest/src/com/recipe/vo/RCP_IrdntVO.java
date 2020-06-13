package com.recipe.vo;

public class RCP_IrdntVO {
	private int row_num = 0;
	private int recipe_id = 0;
	private int irdnt_sn = 0;
	private String irdnt_nm = "";
	private String irdnt_cpcty = "";
	private String irdnt_ty_code = "";
	private String irdnt_ty_nm = "";
	
	public RCP_IrdntVO() {
		super();
	}

	public RCP_IrdntVO(int row_num, int recipe_id, int irdnt_sn, String irdnt_nm, String irdnt_cpcty,
			String irdnt_ty_code, String irdnt_ty_nm) {
		super();
		this.row_num = row_num;
		this.recipe_id = recipe_id;
		this.irdnt_sn = irdnt_sn;
		this.irdnt_nm = irdnt_nm;
		this.irdnt_cpcty = irdnt_cpcty;
		this.irdnt_ty_code = irdnt_ty_code;
		this.irdnt_ty_nm = irdnt_ty_nm;
	}

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public int getIrdnt_sn() {
		return irdnt_sn;
	}

	public void setIrdnt_sn(int irdnt_sn) {
		this.irdnt_sn = irdnt_sn;
	}

	public String getIrdnt_nm() {
		return irdnt_nm;
	}

	public void setIrdnt_nm(String irdnt_nm) {
		this.irdnt_nm = irdnt_nm;
	}

	public String getIrdnt_cpcty() {
		return irdnt_cpcty;
	}

	public void setIrdnt_cpcty(String irdnt_cpcty) {
		this.irdnt_cpcty = irdnt_cpcty;
	}

	public String getIrdnt_ty_code() {
		return irdnt_ty_code;
	}

	public void setIrdnt_ty_code(String irdnt_ty_code) {
		this.irdnt_ty_code = irdnt_ty_code;
	}

	public String getIrdnt_ty_nm() {
		return irdnt_ty_nm;
	}

	public void setIrdnt_ty_nm(String irdnt_ty_nm) {
		this.irdnt_ty_nm = irdnt_ty_nm;
	}

	@Override
	public String toString() {
		return "RCP_IrdntVO [row_num=" + row_num + ", recipe_id=" + recipe_id + ", irdnt_sn=" + irdnt_sn + ", irdnt_nm="
				+ irdnt_nm + ", irdnt_cpcty=" + irdnt_cpcty + ", irdnt_ty_code=" + irdnt_ty_code + ", irdnt_ty_nm="
				+ irdnt_ty_nm + "]";
	}
		
}