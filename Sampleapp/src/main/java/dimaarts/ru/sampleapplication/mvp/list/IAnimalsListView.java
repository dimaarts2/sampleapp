package dimaarts.ru.sampleapplication.mvp.list;

import android.os.Bundle;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;

import java.util.List;

public interface IAnimalsListView {
    void setAnimals(List<AnimalEntity> animals);
    void onSaveInstanceState(Bundle outState);
    void setCallbackPresenter(IAnimalsListPresenter callbackPresenter);
}
