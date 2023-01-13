package edu.upc.dsa.tracks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import edu.upc.dsa.tracks.domain.Track;
import edu.upc.dsa.tracks.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {
    Button btnAdd;
    EditText id, title, singer;
    private ProgressBar progressBarAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        btnAdd = rootView.findViewById(R.id.btnAdd);
        id = rootView.findViewById(R.id.id);
        title = rootView.findViewById(R.id.title);
        singer = rootView.findViewById(R.id.singer);
        progressBarAdd = rootView.findViewById(R.id.progressBarAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarAdd.setVisibility(View.VISIBLE);
                Api service = Api.retrofit.create(Api.class);


                Call<Track> call = service.createTrack(new Track(id.getText().toString(), title.getText().toString(), singer.getText().toString()));

                call.enqueue(new Callback<Track>() {
                    @Override
                    public void onResponse(Call<Track> call, Response<Track> response) {
                        progressBarAdd.setVisibility(View.GONE);
                        switch (response.code()) {
                            case 201:
                                Toast.makeText(getContext(), "Track added correctly", Toast.LENGTH_LONG).show();
                                break;
                            case 500:
                                Toast.makeText(getContext(), "Missing Information", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Track> call, Throwable t) {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
                        progressBarAdd.setVisibility(View.GONE);
                    }
                });
            }
        });
        return rootView;
    }
}