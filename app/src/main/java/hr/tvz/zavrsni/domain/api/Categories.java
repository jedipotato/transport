package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hr.tvz.zavrsni.domain.Category;
import hr.tvz.zavrsni.util.Const;

/**
 * Predstavlja model / domenu dobivenu preko API-a.
 */
public class Categories extends BasicModel {

    @SerializedName(Const.CATEGORIES) private ArrayList<Category> categoriesList;

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categories that = (Categories) o;

        if (categoriesList != null ? !categoriesList.equals(that.categoriesList) : that.categoriesList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoriesList != null ? categoriesList.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoriesList=" + categoriesList +
                '}';
    }
}
