package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hr.tvz.zavrsni.util.Const;

public class Bids extends BasicModel{

    @SerializedName(Const.BIDS) private ArrayList<Bid> bidsList;

    public ArrayList<Bid> getBidsList() {
        return bidsList;
    }

    public void setBidsList(ArrayList<Bid> bidsList) {
        this.bidsList = bidsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bids)) return false;
        if (!super.equals(o)) return false;

        Bids bids = (Bids) o;

        if (bidsList != null ? !bidsList.equals(bids.bidsList) : bids.bidsList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bidsList != null ? bidsList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bids{" +
                "bidsList=" + bidsList +
                '}';
    }
}
