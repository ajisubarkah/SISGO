package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class RestockItem {

	@SerializedName("intId")
	private int intId;

	@SerializedName("strCreatedAt")
	private String strCreatedAt;

	@SerializedName("strUsername")
	private String strUsername;

	@SerializedName("strUpdatedAt")
	private String strUpdatedAt;

	public void setIntId(int intId){
		this.intId = intId;
	}

	public int getIntId(){
		return intId;
	}

	public void setStrCreatedAt(String strCreatedAt){
		this.strCreatedAt = strCreatedAt;
	}

	public String getStrCreatedAt(){
		return strCreatedAt;
	}

	public void setStrUsername(String strUsername){
		this.strUsername = strUsername;
	}

	public String getStrUsername(){
		return strUsername;
	}

	public void setStrUpdatedAt(String strUpdatedAt){
		this.strUpdatedAt = strUpdatedAt;
	}

	public String getStrUpdatedAt(){
		return strUpdatedAt;
	}

}