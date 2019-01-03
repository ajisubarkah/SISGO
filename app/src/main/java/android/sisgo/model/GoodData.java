package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class GoodData {

	@SerializedName("intId")
	private int intId;

	@SerializedName("intPurchase")
	private String intPurchase;

	@SerializedName("strBarcode")
	private String strBarcode;

	@SerializedName("intSelling")
	private String intSelling;

	@SerializedName("intStock")
	private String intStock;

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

	public void setIntPurchase(String intPurchase){
		this.intPurchase = intPurchase;
	}

	public String getIntPurchase(){
		return intPurchase;
	}

	public void setStrBarcode(String strBarcode){
		this.strBarcode = strBarcode;
	}

	public String getStrBarcode(){
		return strBarcode;
	}

	public void setIntSelling(String intSelling){
		this.intSelling = intSelling;
	}

	public String getIntSelling(){
		return intSelling;
	}

	public void setIntStock(String intStock){
		this.intStock = intStock;
	}

	public String getIntStock(){
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