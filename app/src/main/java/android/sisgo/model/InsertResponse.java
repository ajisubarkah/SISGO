package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class InsertResponse{

	@SerializedName("strNameGoods")
	private String strNameGoods;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private int status;

	public void setStrNameGoods(String strNameGoods){
		this.strNameGoods = strNameGoods;
	}

	public String getStrNameGoods(){
		return strNameGoods;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}