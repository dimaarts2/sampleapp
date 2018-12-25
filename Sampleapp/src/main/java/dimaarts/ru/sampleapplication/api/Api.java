package dimaarts.ru.sampleapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface Api {
        @GET("/xim/api.php")
        Call<AnimalResponse> getAnimals(@Query("query") String query);
}
