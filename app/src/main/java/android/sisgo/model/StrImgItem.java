package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class StrImgItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("goods_id")
	private int goodsId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setGoodsId(int goodsId){
		this.goodsId = goodsId;
	}

	public int getGoodsId(){
		return goodsId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}