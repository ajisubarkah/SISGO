package android.sisgo.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

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