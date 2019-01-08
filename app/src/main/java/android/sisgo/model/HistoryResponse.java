package android.sisgo.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse{

	@SerializedName("data")
	private ArrayList<HistoryItem> data;

	public void setData(ArrayList<HistoryItem> data){
		this.data = data;
	}

	public ArrayList<HistoryItem> getData(){
		return data;
	}
}