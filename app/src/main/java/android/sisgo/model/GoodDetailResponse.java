package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class GoodDetailResponse {

	@SerializedName("data")
	private GoodItem data;

	public void setGoodData(GoodItem goodData){
		this.data = data;
	}

	public GoodItem getGoodData(){
		return data;
	}
}