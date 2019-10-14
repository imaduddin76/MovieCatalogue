package aim.helmi.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import aim.helmi.moviecatalogue.adapter.TvAdapter;
import aim.helmi.moviecatalogue.R;
import aim.helmi.moviecatalogue.model.TvModel;
import aim.helmi.moviecatalogue.viewmodel.TVViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {


    private TvAdapter adapter;
    private RecyclerView recyclerView;
    private TVViewModel moviesViewModel;

    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = rootView.findViewById(R.id.rv_tv_show);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new TvAdapter();
        recyclerView.setAdapter(adapter);

        progressBar = rootView.findViewById(R.id.progressBar2);

        moviesViewModel = ViewModelProviders.of(this).get(TVViewModel.class);
        moviesViewModel.getTvShow().observe(this, getTVShow);
        moviesViewModel.setMovies("EXTRA_TV_SHOW");

        showLoading(true);

        return rootView;
    }

    private android.arch.lifecycle.Observer<ArrayList<TvModel>> getTVShow= new Observer<ArrayList<TvModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvModel> tvModels) {
            if (tvModels != null) {
                adapter.setData(tvModels);
            }

            showLoading(false);
        }

    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
