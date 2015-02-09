package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import hr.tvz.zavrsni.util.Const;

/**
 * Defines basic model used by most API-s
 */
public class BasicModel {

    @SerializedName(Const.SUCCESS) private int success;
    @SerializedName(Const.MESSAGE) private String message;

    public boolean isSuccess() {
        return success == 1;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicModel that = (BasicModel) o;

        if (success != that.success) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = success;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasicModel{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
