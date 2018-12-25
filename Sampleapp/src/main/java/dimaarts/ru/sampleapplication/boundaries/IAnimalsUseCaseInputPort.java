package dimaarts.ru.sampleapplication.boundaries;

import dimaarts.ru.sampleapplication.entities.CatEntity;
import dimaarts.ru.sampleapplication.entities.DogEntity;
import dimaarts.ru.sampleapplication.util.Observer;

import java.util.List;

public interface IAnimalsUseCaseInputPort {
    void setCats(List<CatEntity> cats);
    void setDogs(List<DogEntity> dogs);
    void addCatsObserver(Observer<List<CatEntity>> observer);
    void addDogsObserver(Observer<List<DogEntity>> observer);
    void removeCatsObserver(Observer<List<CatEntity>> observer);
    void removeDogsObserver(Observer<List<DogEntity>> observer);
}
