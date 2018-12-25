package dimaarts.ru.sampleapplication.mvp.list;

import android.content.Context;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;

public interface IAnimalsListPresenter {
    void onSelect(AnimalEntity selectedEntity, Context context);
}
