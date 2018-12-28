package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class RestockItem {

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("updatedAt")
    private String updatedAt;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "RestockItem{" +
                "createdAt = '" + createdAt + '\'' +
                ",id = '" + id + '\'' +
                ",username = '" + username + '\'' +
                ",updatedAt = '" + updatedAt + '\'' +
                "}";
    }
}