package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoodResponse{

	@SerializedName("data")
	private ArrayList<GoodItem> data;

	public void setData(ArrayList<GoodItem> data){
		this.data = data;
	}

	public ArrayList<GoodItem> getData(){
		return data;
	}
}