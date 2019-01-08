package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class HistoryItem {

	@SerializedName("intId")
	private int intId;

	@SerializedName("strBarcode")
	private String strBarcode;

	@SerializedName("strName")
	private String strName;

	@SerializedName("intAddStok")
	private String intAddStok;

	public void setIntId(int intId){
		this.intId = intId;
	}

	public int getIntId(){
		return intId;
	}

	public void setStrBarcode(String strBarcode){
		this.strBarcode = strBarcode;
	}

	public String getStrBarcode(){
		return strBarcode;
	}

	public void setStrName(String strName){
		this.strName = strName;
	}

	public String getStrName(){
		return strName;
	}

	public void setIntAddStok(String intAddStok){
		this.intAddStok = intAddStok;
	}

	public String getIntAddStok(){
		return intAddStok;
	}
}