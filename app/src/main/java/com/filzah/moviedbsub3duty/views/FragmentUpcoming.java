package com.filzah.moviedbsub3duty.views;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.filzah.moviedbsub3duty.R;
import com.filzah.moviedbsub3duty.adapter.MainAdapter;
import com.filzah.moviedbsub3duty.model.Model;
import com.filzah.moviedbsub3duty.server.MainData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUpcoming extends Fragment {
    private MainAdapter adapter;
    private ProgressBar progressBar;
    RecyclerView rvShow;


    public FragmentUpcoming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        progressBar = rootView.findViewById(R.id.progress_ar);

        MainData viewModel = ViewModelProviders.of(this).get(MainData.class);
        viewModel.getDatas().observe(this, getMoviesData);

        showLoading(true);
        viewModel.getDataList("movie");

        adapter = new MainAdapter();
        adapter.notifyDataSetChanged();

        rvShow = rootView.findViewById(R.id.rv_show);
        rvShow.setHasFixedSize(true);
        rvShow.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvShow.setAdapter(adapter);


        return rootView;
    }

    private Observer<ArrayList<Model>> getMoviesData = new Observer<ArrayList<Model>>() {
        @Override
        public void onChanged(ArrayList<Model> models) {
            if (models != null){
                adapter.setData(models);
                showLoading(false);
            }
        }
    };

    private void showLoading(boolean b) {
        if (b){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
