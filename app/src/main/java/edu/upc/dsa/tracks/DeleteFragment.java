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

import edu.upc.dsa.tracks.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteFragment extends Fragment {
    Button btnDelete;
    EditText id;
    private ProgressBar progressBarDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        btnDelete = rootView.findViewById(R.id.btnDelete);
        id = rootView.findViewById(R.id.id);
        progressBarDelete = rootView.findViewById(R.id.progressBarDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarDelete.setVisibility(View.VISIBLE);
                Api service = Api.retrofit.create(Api.class);


                Call<Void> call = service.deleteTrack(id.getText().toString());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressBarDelete.setVisibility(View.GONE);
                        switch (response.code()) {
                            case 201:
                                Toast.makeText(getContext(), "Track deleted correctly", Toast.LENGTH_LONG).show();
                                break;
                            case 404:
                                Toast.makeText(getContext(), "Track Not Found", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
                        progressBarDelete.setVisibility(View.GONE);
                    }
                });
            }
        });
        return rootView;
    }
}