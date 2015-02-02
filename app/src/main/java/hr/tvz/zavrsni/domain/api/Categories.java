package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.tvz.zavrsni.domain.Category;
import hr.tvz.zavrsni.util.Const;

/**
 * Predstavlja model / domenu dobivenu preko API-a.
 */
public class Categories {

    @SerializedName(Const.CATEGORIES) private List<Category> categoriesList;
    @SerializedName(Const.SUCCESS) private int success;

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public boolean isSuccess() {
        return success == 1;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categories that = (Categories) o;

        if (success != that.success) return false;
        if (categoriesList != null ? !categoriesList.equals(that.categoriesList) : that.categoriesList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoriesList != null ? categoriesList.hashCode() : 0;
        result = 31 * result + success;
        return result;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoriesList=" + categoriesList +
                ", success=" + success +
                '}';
    }
}
