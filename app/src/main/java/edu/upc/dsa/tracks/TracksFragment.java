package edu.upc.dsa.tracks;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import edu.upc.dsa.tracks.adapters.AdapterTrack;
import edu.upc.dsa.tracks.domain.Track;
import edu.upc.dsa.tracks.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksFragment extends Fragment {
    public List<Track> listTracks;
    private RecyclerView recycler;
    private AdapterTrack adapterTrack;
    private ProgressBar progressBarTracks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_tracks,container,false);
        recycler=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        progressBarTracks = rootView.findViewById(R.id.progressBarTracks);
        getListTracks();
        return rootView;
    }
    private void getListTracks(){
        progressBarTracks.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<Track>> call=service.getTracks();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                progressBarTracks.setVisibility(View.GONE);
                listTracks=response.body();
                adapterTrack=new AdapterTrack(listTracks, new AdapterTrack.OnItemClickListener() {
                    @Override
                    public void onItemClick(Track track) {
                        moveToDescription(track);
                    }
                });
                recycler.setAdapter(adapterTrack);
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                progressBarTracks.setVisibility(View.GONE);
            }
        });
    }

    private void moveToDescription(Track track){
        Intent i=new Intent(getActivity(),TrackDetails.class);
        i.putExtra("Details", track);
        startActivity(i);
    }
}