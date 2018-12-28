package android.sisgo.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class RestockResponse{

	@SerializedName("data")
	private ArrayList<RestockItem> data;

	public void setData(ArrayList<RestockItem> data){
		this.data = data;
	}

	public ArrayList<RestockItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"RestockResponse{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}