package edu.upc.dsa.tracks.retrofit;

import java.util.List;

import edu.upc.dsa.tracks.domain.Track;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String URL="http://192.168.43.194:80/dsaApp/";

    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST("tracks")
    Call<Track> createTrack(@Body Track track);

    @PUT("tracks")
    Call<Void> updateTrack(@Body Track track);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") String id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}