package dimaarts.ru.sampleapplication.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.api.AnimalElementResponse;
import dimaarts.ru.sampleapplication.api.AnimalResponse;
import dimaarts.ru.sampleapplication.api.ApiManager;
import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseInputPort;
import dimaarts.ru.sampleapplication.entities.CatEntity;
import dimaarts.ru.sampleapplication.entities.DogEntity;
import dimaarts.ru.sampleapplication.mvp.IViewManager;
import dimaarts.ru.sampleapplication.mvp.main.AnimalsMainPresenter;
import dimaarts.ru.sampleapplication.mvp.main.AnimalsMainView;
import dimaarts.ru.sampleapplication.mvp.main.IAnimalsMainPresenter;
import dimaarts.ru.sampleapplication.mvp.main.IAnimalsMainView;
import dimaarts.ru.sampleapplication.useceses.AnimalsUseCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IViewManager {
    private IAnimalsMainView view;
    private IAnimalsMainPresenter presenter;
    private ApiManager apiManager;
    private AnimalsUseCase useCase = new AnimalsUseCase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = new AnimalsMainView(this, savedInstanceState, useCase);
        presenter = new AnimalsMainPresenter(view);
        apiManager = new ApiManager(savedInstanceState);
        apiManager.process(useCase);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        view.onSaveInstanceState(outState);
        apiManager.onSaveInstanceState(outState);
    }
}
