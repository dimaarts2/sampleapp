package dimaarts.ru.sampleapplication.mvp.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseOutputPort;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;
import dimaarts.ru.sampleapplication.ui.detail.DetailsActivity;
import dimaarts.ru.sampleapplication.ui.list.FragmentType;

import java.util.List;

public class AnimalsListPresenter implements IAnimalsUseCaseOutputPort, IAnimalsListPresenter {
    private IAnimalsListView view;
    private FragmentType fragmentType;

    public AnimalsListPresenter(IAnimalsListView view, List<AnimalEntity> animals, FragmentType fragmentType) {
        this.view = view;
        this.fragmentType = fragmentType;
        view.setAnimals(animals);
    }

    @Override
    public void getCats(List<AnimalEntity> cats) {
        if(fragmentType == FragmentType.Cats)
            view.setAnimals(cats);
    }

    @Override
    public void getDogs(List<AnimalEntity> dogs) {
        if(fragmentType == FragmentType.Dogs)
            view.setAnimals(dogs);
    }

    @Override
    public void onSelect(AnimalEntity selectedEntity, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DetailsActivity.DETAIL_ANIMAL, selectedEntity);
        context.startActivity(intent);
    }
}
