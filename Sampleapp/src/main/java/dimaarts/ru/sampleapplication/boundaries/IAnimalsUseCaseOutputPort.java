package dimaarts.ru.sampleapplication.boundaries;

import dimaarts.ru.sampleapplication.entities.AnimalEntity;

import java.util.List;

public interface IAnimalsUseCaseOutputPort {
    void getCats(List<AnimalEntity> cats);
    void getDogs(List<AnimalEntity> dogs);
}
