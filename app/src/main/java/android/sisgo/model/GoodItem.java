package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class GoodItem {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("priceSelling")
	private String priceSelling;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("stock")
	private String stock;

	@SerializedName("barcode")
	private String barcode;

	@SerializedName("pricePurchase")
	private String pricePurchase;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPriceSelling(String priceSelling){
		this.priceSelling = priceSelling;
	}

	public String getPriceSelling(){
		return priceSelling;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	public String getBarcode(){
		return barcode;
	}

	public void setPricePurchase(String pricePurchase){
		this.pricePurchase = pricePurchase;
	}

	public String getPricePurchase(){
		return pricePurchase;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}