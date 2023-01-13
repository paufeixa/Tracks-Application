package edu.upc.dsa.tracks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.upc.dsa.tracks.domain.Track;
import edu.upc.dsa.tracks.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackDetails extends AppCompatActivity {
    TextView title;
    TextView details;
    EditText titleUpdate, singerUpdate;
    Button btnUpdate, btnBack;
    private ProgressBar progressBarUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        title=findViewById(R.id.title);
        details=findViewById(R.id.details);
        titleUpdate=findViewById(R.id.titleUpdate);
        singerUpdate=findViewById(R.id.singerUpdate);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnBack=findViewById(R.id.btnBack);
        progressBarUpdate=findViewById(R.id.progressBarUpdate);

        Track track = (Track) getIntent().getSerializableExtra("Details");
        title.setText(track.getTitle());
        details.setText("Singer: "+track.getSinger()+"\n"+"id: "+track.getId());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleUpdate.getText().toString().equals("") && singerUpdate.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Nothing to update", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!titleUpdate.getText().toString().equals("")) {
                    track.setTitle(titleUpdate.getText().toString());
                }
                if (!singerUpdate.getText().toString().equals("")) {
                    track.setSinger(singerUpdate.getText().toString());
                }


                progressBarUpdate.setVisibility(View.VISIBLE);
                Api service = Api.retrofit.create(Api.class);

                Call<Void> call = service.updateTrack(track);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressBarUpdate.setVisibility(View.GONE);
                        switch (response.code()) {
                            case 201:
                                Toast.makeText(getApplicationContext(), "Track updated correctly", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(TrackDetails.this, MainActivity.class);
                                startActivity(i);
                                break;
                            case 404:
                                Toast.makeText(getApplicationContext(), "Track Not Found", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                        progressBarUpdate.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrackDetails.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}