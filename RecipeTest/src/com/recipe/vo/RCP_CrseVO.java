package com.recipe.vo;

public class RCP_CrseVO {
	private int row_num = 0;
	private int recipe_id = 0;
	private int cooking_no = 0;
	private String cooking_dc = "";
	private String stre_step_image_url = "";
	private String step_tip = "";
	
	public RCP_CrseVO() {
		super();
	}

	public RCP_CrseVO(int row_num, int recipe_id, int cooking_no, String cooking_dc, String stre_step_image_url,
			String step_tip) {
		super();
		this.row_num = row_num;
		this.recipe_id = recipe_id;
		this.cooking_no = cooking_no;
		this.cooking_dc = cooking_dc;
		this.stre_step_image_url = stre_step_image_url;
		this.step_tip = step_tip;
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

	public int getCooking_no() {
		return cooking_no;
	}

	public void setCooking_no(int cooking_no) {
		this.cooking_no = cooking_no;
	}

	public String getCooking_dc() {
		return cooking_dc;
	}

	public void setCooking_dc(String cooking_dc) {
		this.cooking_dc = cooking_dc;
	}

	public String getStre_step_image_url() {
		return stre_step_image_url;
	}

	public void setStre_step_image_url(String stre_step_image_url) {
		this.stre_step_image_url = stre_step_image_url;
	}

	public String getStep_tip() {
		return step_tip;
	}

	public void setStep_tip(String step_tip) {
		this.step_tip = step_tip;
	}

	@Override
	public String toString() {
		return "RCP_CrseVO [row_num=" + row_num + ", recipe_id=" + recipe_id + ", cooking_no=" + cooking_no
				+ ", cooking_dc=" + cooking_dc + ", stre_step_image_url=" + stre_step_image_url + ", step_tip="
				+ step_tip + "]";
	}
	
}