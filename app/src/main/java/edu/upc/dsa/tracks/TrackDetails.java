package edu.upc.dsa.tracks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import edu.upc.dsa.tracks.domain.Track;

public class TrackDetails extends AppCompatActivity {
    TextView title;
    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        title=findViewById(R.id.title);
        details=findViewById(R.id.details);
        Track track = (Track) getIntent().getSerializableExtra("Details");
        title.setText(track.getTitle());
        details.setText("Singer: "+track.getSinger());
    }
}