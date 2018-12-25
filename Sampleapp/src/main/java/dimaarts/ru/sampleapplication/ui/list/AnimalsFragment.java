package dimaarts.ru.sampleapplication.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseOutputPort;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;
import dimaarts.ru.sampleapplication.mvp.IViewManager;
import dimaarts.ru.sampleapplication.mvp.list.AnimalsListPresenter;
import dimaarts.ru.sampleapplication.mvp.list.AnimalsListView;
import dimaarts.ru.sampleapplication.mvp.list.IAnimalsListView;

import java.util.ArrayList;
import java.util.List;

public class AnimalsFragment extends Fragment implements IAnimalsUseCaseOutputPort, IViewManager {
    private FragmentType fragmentType;
    private AnimalsListPresenter presenter;
    private IAnimalsListView view;
    public final static String FRAGMENT_TYPE_BUNDLE = "FRAGMENT_TYPE_BUNDLE";
    public final static String FRAGMENT_LIST_SAVE_STATE = "FRAGMENT_LIST_SAVE_STATE";
    private View fragmentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            fragmentType = FragmentType.values()[getArguments().getInt(FRAGMENT_TYPE_BUNDLE)];
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animals_fragment, container, false);
        fragmentView = view;
        this.view = new AnimalsListView(this);
        List<AnimalEntity> savedAnimals = new ArrayList<>();
        if(savedInstanceState!=null) {
            List<AnimalEntity> savedStateAnimals = (List<AnimalEntity>) savedInstanceState.getSerializable(FRAGMENT_LIST_SAVE_STATE);
            if(savedStateAnimals!=null) {
                savedAnimals = savedStateAnimals;
            }
        }

        presenter = new AnimalsListPresenter(this.view, savedAnimals, fragmentType);
        this.view.setCallbackPresenter(presenter);
        fragmentView = null;
        return view;
    }

    @Override
    public void getCats(List<AnimalEntity> cats) {
        presenter.getCats(cats);
    }

    @Override
    public void getDogs(List<AnimalEntity> dogs) {
        presenter.getDogs(dogs);
    }

    @Nullable
    @Override
    public <T extends View> T findViewById(int id) {
        return fragmentView.findViewById(id);
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getChildFragmentManager();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        view.onSaveInstanceState(outState);
    }
}
