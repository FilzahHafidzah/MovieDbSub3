package com.filzah.moviedbsub3duty.views;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.filzah.moviedbsub3duty.R;
import com.filzah.moviedbsub3duty.adapter.MainAdapter;
import com.filzah.moviedbsub3duty.model.Movie;
import com.filzah.moviedbsub3duty.model.ResponseMovie;
import com.filzah.moviedbsub3duty.server.InterfaceClass;
import com.filzah.moviedbsub3duty.server.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvshow extends Fragment {
    private RecyclerView rvShow;
    private MainAdapter adapter;
    private ProgressBar progressBar;
    private List<Movie> listMovies = new ArrayList<>();

    InterfaceClass apiInterface;

    private final String api_key = Server.API_KEY;
    private final String sort_by = "popularity.desc";
    private final String page = "1";
    private final String language = "en-US";
    private final String include_video = "false";
    private final String inluce_adult = "false";


    public FragmentTvshow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tvshow, container, false);

        rvShow = rootView.findViewById(R.id.rv_tvshow);
        rvShow.setHasFixedSize(true);
        rvShow.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvShow.setAdapter(adapter);

        progressBar = rootView.findViewById(R.id.progress_tvshow);

        adapter = new MainAdapter(getContext(), listMovies);
        apiInterface = Server.getApiClient();

        loadTvshow();
//

        return rootView;
    }

    private void loadTvshow() {
        apiInterface.getTvshow(api_key, language, sort_by, inluce_adult, include_video, page)
                .enqueue(new Callback<ResponseMovie>() {
                    @Override
                    public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            listMovies = response.body().getMovies();
                            rvShow.setAdapter(new MainAdapter(getContext(), listMovies));
                            adapter.notifyDataSetChanged();
                            Log.d("sd" , "onResponse :"+listMovies);
                        }else {
                            Log.d("sd", "OnConnect : "+listMovies);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMovie> call, Throwable t) {
                        Log.d("sd", "onFailure :"+t);
                    }
                });
    }



}
