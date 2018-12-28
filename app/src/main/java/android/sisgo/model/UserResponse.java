package android.sisgo.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    @SerializedName("token")
    private String token;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "message = '" + message + '\'' +
                ",status = '" + status + '\'' +
                ",token = '" + token + '\'' +
                "}";
    }
}