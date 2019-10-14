package aim.helmi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvModel implements Parcelable {

    private String name;
    private String first_air_date;
    private String overview;
    private String poster_path;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(first_air_date);
        dest.writeString(overview);
        dest.writeString(poster_path);
    }

    public TvModel(JSONObject object) {
        try{
            String name = object.getString("name");
            String first_air_date = object.getString("first_air_date");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");

            this.name = name;
            this.first_air_date = first_air_date;
            this.overview = overview;
            this.poster_path = poster_path;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected TvModel(Parcel in) {
        name = in.readString();
        first_air_date = in.readString();
        overview = in.readString();
        poster_path = in.readString();
    }

    public static final Creator<TvModel> CREATOR = new Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel in) {
            return new TvModel(in);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };
}
