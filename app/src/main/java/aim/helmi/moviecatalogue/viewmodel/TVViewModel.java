package aim.helmi.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import aim.helmi.moviecatalogue.model.TvModel;
import cz.msebera.android.httpclient.Header;

public class TVViewModel extends ViewModel {
    public static final String API_KEY = "26bee7256a53f415bbed998171008a69";
    private MutableLiveData<ArrayList<TvModel>> listTvShow= new MutableLiveData<>();

    public void setMovies(final String tvShow){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvModel> listItem= new ArrayList<>();

        String url ="https://api.themoviedb.org/3/discover/tv?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i< list.length(); i++){
                        JSONObject tv = list.getJSONObject(i);
                        TvModel tvModelItems = new TvModel(tv);
                        listItem.add(tvModelItems);
                    }
                    listTvShow.postValue(listItem);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<TvModel>> getTvShow() {
        return listTvShow;
    }
}
