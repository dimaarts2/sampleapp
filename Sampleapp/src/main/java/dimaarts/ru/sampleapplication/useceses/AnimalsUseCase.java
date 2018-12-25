package dimaarts.ru.sampleapplication.useceses;

import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseInputPort;
import dimaarts.ru.sampleapplication.entities.CatEntity;
import dimaarts.ru.sampleapplication.entities.DogEntity;
import dimaarts.ru.sampleapplication.util.Observable;
import dimaarts.ru.sampleapplication.util.Observer;

import java.util.List;

public class AnimalsUseCase implements IAnimalsUseCaseInputPort {
    private List<CatEntity> cats;
    private List<DogEntity> dogs;
    private Observable<List<CatEntity>> catsObservable = new Observable<>();
    private Observable<List<DogEntity>> dogsObservable = new Observable<>();

    @Override
    public void setCats(List<CatEntity> cats) {
        this.cats = cats;
        catsObservable.notifyObservers(this.cats);
    }

    @Override
    public void setDogs(List<DogEntity> dogs) {
        this.dogs = dogs;
        dogsObservable.notifyObservers(this.dogs);
    }

    @Override
    public void addCatsObserver(Observer<List<CatEntity>> observer) {
        catsObservable.addObserver(observer);
    }

    @Override
    public void addDogsObserver(Observer<List<DogEntity>> observer) {
        dogsObservable.addObserver(observer);
    }

    @Override
    public void removeCatsObserver(Observer<List<CatEntity>> observer) {
        catsObservable.deleteObserver(observer);
    }

    @Override
    public void removeDogsObserver(Observer<List<DogEntity>> observer) {
        dogsObservable.deleteObserver(observer);
    }
}
