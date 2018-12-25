package dimaarts.ru.sampleapplication.api;

import android.os.Bundle;
import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseInputPort;
import dimaarts.ru.sampleapplication.entities.CatEntity;
import dimaarts.ru.sampleapplication.entities.DogEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class ApiManager {

    private Api service;
    private Boolean catsProcessed = false;
    private Boolean dogsProcessed = false;
    private final static String CATS_PROCESSED_SAVE_STATE = "CATS_PROCESSED_SAVE_STATE";
    private final static String DOGS_PROCESSED_SAVE_STATE = "DOGS_PROCESSED_SAVE_STATE";

    public ApiManager(Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kot3.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Api.class);
        if(savedInstanceState!=null) {
            catsProcessed = savedInstanceState.getBoolean(CATS_PROCESSED_SAVE_STATE);
            dogsProcessed = savedInstanceState.getBoolean(DOGS_PROCESSED_SAVE_STATE);
        }
    }

    public void process(final IAnimalsUseCaseInputPort useCase) {
        if(!catsProcessed) {
            service.getAnimals("cat").enqueue(new Callback<AnimalResponse>() {
                @Override
                public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                    List<CatEntity> cats = new ArrayList<CatEntity>();
                    if (response.body() != null) {
                        for (AnimalElementResponse element :
                                response.body().getData()) {
                            CatEntity cat = new CatEntity();
                            cat.setName(element.getTitle());
                            cat.setImageUrl(element.getUrl());
                            cats.add(cat);
                        }
                    }
                    useCase.setCats(cats);
                    catsProcessed = true;
                }

                @Override
                public void onFailure(Call<AnimalResponse> call, Throwable t) {

                }
            });
        }

        if(!dogsProcessed) {
            service.getAnimals("dog").enqueue(new Callback<AnimalResponse>() {
                @Override
                public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                    List<DogEntity> dogs = new ArrayList<DogEntity>();
                    if (response.body() != null) {
                        for (AnimalElementResponse element :
                                response.body().getData()) {
                            DogEntity dog = new DogEntity();
                            dog.setName(element.getTitle());
                            dog.setImageUrl(element.getUrl());
                            dogs.add(dog);
                        }
                    }
                    useCase.setDogs(dogs);
                    dogsProcessed = true;
                }

                @Override
                public void onFailure(Call<AnimalResponse> call, Throwable t) {

                }
            });
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(CATS_PROCESSED_SAVE_STATE, catsProcessed);
        outState.putBoolean(DOGS_PROCESSED_SAVE_STATE, dogsProcessed);
    }
}
