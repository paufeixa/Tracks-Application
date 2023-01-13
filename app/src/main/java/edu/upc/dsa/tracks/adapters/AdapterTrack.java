package edu.upc.dsa.tracks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.tracks.R;
import edu.upc.dsa.tracks.domain.Track;

public class AdapterTrack extends RecyclerView.Adapter<AdapterTrack.ViewHolder>{
    private List<Track> listTracks;

    final OnItemClickListener listener;

    public AdapterTrack(List<Track> listTracks, OnItemClickListener listener) {
        this.listTracks=listTracks;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(Track track);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView singer;
        private TextView id;
        private TextView details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            singer = itemView.findViewById(R.id.singer);
            id = itemView.findViewById(R.id.id);
            details = itemView.findViewById(R.id.details);
        }
        void binData(final Track track){
            title.setText(track.getTitle());
            singer.setText(track.getSinger());
            id.setText(track.getId());
            details.setText("Click to edit the fields");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(track);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binData(listTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return listTracks.size();
    }
}