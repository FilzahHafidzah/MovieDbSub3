package com.filzah.moviedbsub3duty.server;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.filzah.moviedbsub3duty.model.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainData extends ViewModel {
    private final ArrayList<Model> listiem = new ArrayList<>();

    private MutableLiveData<ArrayList<Model>> listDataItem = new MutableLiveData<>();

    public void getDataList(final String data){
        GetDataEndPoint getDataEndPoint = ApiService.getClient().create(GetDataEndPoint.class);
        Call<ResponseBody> call = getDataEndPoint.getData(data, ApiService.API_KEY, "en-US");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONObject responseJson = new JSONObject(result);
                    JSONArray list = responseJson.getJSONArray("results");

                    for (int a = 0; a < list.length(); a++){
                        JSONObject movies = list.getJSONObject(a);
                        Model model = new Model(movies);
                        listiem.add(model);
                    }
                    listDataItem.postValue(listiem);
                    Log.d("sd", "onResponse: "+list.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("sd", "onFailure: "+t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Model>> getDatas(){
        return listDataItem;
    }

    interface GetDataEndPoint{
        @GET("3/discover/{data}")
        Call<ResponseBody> getData(@Path("data")String data,
                                   @Path("api_key")String API_KEY,
                                   @Path("language")String language);
    }
}
