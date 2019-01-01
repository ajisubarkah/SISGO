package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class GoodItem {

	@SerializedName("intId")
	private int intId;

	@SerializedName("intPurchase")
	private int intPurchase;

	@SerializedName("strBarcode")
	private String strBarcode;

	@SerializedName("intSelling")
	private int intSelling;

	@SerializedName("intStock")
	private int intStock;

	@SerializedName("strCreatedAt")
	private String strCreatedAt;

	@SerializedName("strName")
	private String strName;

	@SerializedName("strImg")
	private String strImg;

	@SerializedName("strCpdatedAt")
	private String strCpdatedAt;

	public void setIntId(int intId){
		this.intId = intId;
	}

	public int getIntId(){
		return intId;
	}

	public void setIntPurchase(int intPurchase){
		this.intPurchase = intPurchase;
	}

	public int getIntPurchase(){
		return intPurchase;
	}

	public void setStrBarcode(String strBarcode){
		this.strBarcode = strBarcode;
	}

	public String getStrBarcode(){
		return strBarcode;
	}

	public void setIntSelling(int intSelling){
		this.intSelling = intSelling;
	}

	public int getIntSelling(){
		return intSelling;
	}

	public void setIntStock(int intStock){
		this.intStock = intStock;
	}

	public int getIntStock(){
		return intStock;
	}

	public void setStrCreatedAt(String strCreatedAt){
		this.strCreatedAt = strCreatedAt;
	}

	public String getStrCreatedAt(){
		return strCreatedAt;
	}

	public void setStrName(String strName){
		this.strName = strName;
	}

	public String getStrName(){
		return strName;
	}

	public void setStrImg(String strImg){
		this.strImg = strImg;
	}

	public String getStrImg(){
		return strImg;
	}

	public void setStrCpdatedAt(String strCpdatedAt){
		this.strCpdatedAt = strCpdatedAt;
	}

	public String getStrCpdatedAt(){
		return strCpdatedAt;
	}
}