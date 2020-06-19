package com.recipe.vo;

public class Recipe_InfoVO {
	private int recipe_code = 0;
	private String recipe_name = "";
	private String recipe_summ = "";
	private int recipe_price = 0;
	private String recipe_process = "";
	private String recipe_status = "";
	private String rd_id = "";
	
	public Recipe_InfoVO() {
		super();
	}

	public Recipe_InfoVO(int recipe_code, String recipe_name, String recipe_summ, int recipe_price,
			String recipe_process, String recipe_status, String rd_id) {
		super();
		this.recipe_code = recipe_code;
		this.recipe_name = recipe_name;
		this.recipe_summ = recipe_summ;
		this.recipe_price = recipe_price;
		this.recipe_process = recipe_process;
		this.recipe_status = recipe_status;
		this.rd_id = rd_id;
	}

	public int getRecipe_code() {
		return recipe_code;
	}

	public void setRecipe_code(int recipe_code) {
		this.recipe_code = recipe_code;
	}

	public String getRecipe_name() {
		return recipe_name;
	}

	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}

	public String getRecipe_summ() {
		return recipe_summ;
	}

	public void setRecipe_summ(String recipe_summ) {
		this.recipe_summ = recipe_summ;
	}

	public int getRecipe_price() {
		return recipe_price;
	}

	public void setRecipe_price(int recipe_price) {
		this.recipe_price = recipe_price;
	}

	public String getRecipe_process() {
		return recipe_process;
	}

	public void setRecipe_process(String recipe_process) {
		this.recipe_process = recipe_process;
	}

	public String getRecipe_status() {
		return recipe_status;
	}

	public void setRecipe_status(String recipe_status) {
		this.recipe_status = recipe_status;
	}

	public String getRd_id() {
		return rd_id;
	}

	public void setRd_id(String rd_id) {
		this.rd_id = rd_id;
	}

	@Override
	public String toString() {
		return "Recipe_InfoVO [recipe_code=" + recipe_code + ", recipe_name=" + recipe_name + ", recipe_summ="
				+ recipe_summ + ", recipe_price=" + recipe_price + ", recipe_process=" + recipe_process
				+ ", recipe_status=" + recipe_status + ", rd_id=" + rd_id + "]";
	}
	
}
