package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class GoodDetailResponse {

	@SerializedName("data")
	private GoodData data;

	public void setGoodData(GoodData goodData){
		this.data = data;
	}

	public GoodData getGoodData(){
		return data;
	}
}