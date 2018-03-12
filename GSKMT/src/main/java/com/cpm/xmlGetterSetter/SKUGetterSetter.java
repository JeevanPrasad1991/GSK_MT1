package com.cpm.xmlGetterSetter;

import java.util.ArrayList;



public class SKUGetterSetter {
	
	public ArrayList<String> getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id.add(sku_id);
	}
	public ArrayList<String> getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name.add(sku_name);
	}
	public ArrayList<String> getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id.add(brand_id);
	}
	public ArrayList<String> getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand.add(brand);
	}
	public ArrayList<String> getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id.add(category_id);
	}
	public ArrayList<String> getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name.add(category_name);
	}
	public ArrayList<String> getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id.add(company_id);
	}
	
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	
	ArrayList<String> sku_id = new ArrayList<String>();
	ArrayList<String> sku_name = new ArrayList<String>();
	ArrayList<String> brand_id = new ArrayList<String>();
	ArrayList<String> brand = new ArrayList<String>();
	ArrayList<String> category_id = new ArrayList<String>();
	ArrayList<String> category_name = new ArrayList<String>();
	ArrayList<String> company_id = new ArrayList<String>();
	String meta_data, data_entry_tables, stock_entry_table, stock_image_table, tot_entry_table, tot_image_table;
	
	public String getTot_entry_table() {
		return tot_entry_table;
	}
	public void setTot_entry_table(String tot_entry_table) {
		this.tot_entry_table = tot_entry_table;
	}
	public String getTot_image_table() {
		return tot_image_table;
	}
	public void setTot_image_table(String tot_image_table) {
		this.tot_image_table = tot_image_table;
	}
	public String getStock_entry_table() {
		return stock_entry_table;
	}
	public void setStock_entry_table(String stock_entry_table) {
		this.stock_entry_table = stock_entry_table;
	}
	public String getStock_image_table() {
		return stock_image_table;
	}
	public void setStock_image_table(String stock_image_table) {
		this.stock_image_table = stock_image_table;
	}
	public String getData_entry_tables() {
		return data_entry_tables;
	}
	public void setData_entry_tables(String data_entry_tables) {
		this.data_entry_tables = data_entry_tables;
	}
	
	
}
