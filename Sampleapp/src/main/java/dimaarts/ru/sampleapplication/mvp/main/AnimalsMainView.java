package dimaarts.ru.sampleapplication.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.boundaries.IAnimalsUseCaseOutputPort;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;
import dimaarts.ru.sampleapplication.entities.CatEntity;
import dimaarts.ru.sampleapplication.entities.DogEntity;
import dimaarts.ru.sampleapplication.mvp.IViewManager;
import dimaarts.ru.sampleapplication.ui.list.AnimalsFragment;
import dimaarts.ru.sampleapplication.ui.list.FragmentType;
import dimaarts.ru.sampleapplication.useceses.AnimalsUseCase;
import dimaarts.ru.sampleapplication.util.Observable;
import dimaarts.ru.sampleapplication.util.Observer;

import java.util.ArrayList;
import java.util.List;

import static dimaarts.ru.sampleapplication.ui.list.AnimalsFragment.FRAGMENT_TYPE_BUNDLE;

public class AnimalsMainView implements IAnimalsMainView, TabLayout.BaseOnTabSelectedListener {
    private FragmentType selectedTab = FragmentType.Cats;
    private TabLayout tabLayout;
    private IViewManager viewManager;
    private AnimalsFragment catsFragment;
    private AnimalsFragment dogsFragment;
    private List<AnimalsFragment> allFragments = new ArrayList<AnimalsFragment>();
    private static final String CATS_FRAGMENT_TAG = "CATS_FRAGMENT_TAG";
    private static final String DOGS_FRAGMENT_TAG = "DOGS_FRAGMENT_TAG";
    private static final String SAVED_SELECTED_TAB = "SAVED_SELECTED_TAB";

    public AnimalsMainView(IViewManager viewManager, @Nullable Bundle savedInstanceState, AnimalsUseCase useCase) {
        this.viewManager = viewManager;
        tabLayout = viewManager.findViewById(R.id.tabLayout);
        if (tabLayout != null) {
            tabLayout.addOnTabSelectedListener(this);
        }
        catsFragment = (AnimalsFragment) viewManager.getSupportFragmentManager().findFragmentByTag(CATS_FRAGMENT_TAG);
        dogsFragment = (AnimalsFragment) viewManager.getSupportFragmentManager().findFragmentByTag(DOGS_FRAGMENT_TAG);

        if(dogsFragment == null) {
            dogsFragment = new AnimalsFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(FRAGMENT_TYPE_BUNDLE, FragmentType.Dogs.getFragmentType());
            dogsFragment.setArguments(arguments);
            viewManager.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, dogsFragment, DOGS_FRAGMENT_TAG)
                    .commit();
        }
        if(catsFragment == null) {
            catsFragment = new AnimalsFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(FRAGMENT_TYPE_BUNDLE, FragmentType.Cats.getFragmentType());
            catsFragment.setArguments(arguments);
            viewManager.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, catsFragment, CATS_FRAGMENT_TAG)
                    .commit();
        }
        allFragments.add(catsFragment);
        allFragments.add(dogsFragment);
        if(savedInstanceState!=null) {
            selectedTab = FragmentType.values()[savedInstanceState.getInt(SAVED_SELECTED_TAB)];
        }
        tabLayout.getTabAt(selectedTab.getFragmentType()).select();
        useCase.addCatsObserver(new Observer<List<CatEntity>>() {
            @Override
            public void update(Observable<List<CatEntity>> o, List<CatEntity> arg) {
                List<AnimalEntity> animals = new ArrayList<>();
                animals.addAll(arg);
                for (IAnimalsUseCaseOutputPort fragment:
                allFragments) {
                    fragment.getCats(animals);
                }
            }
        });
        useCase.addDogsObserver(new Observer<List<DogEntity>>() {
            @Override
            public void update(Observable<List<DogEntity>> o, List<DogEntity> arg) {
                List<AnimalEntity> animals = new ArrayList<>();
                animals.addAll(arg);
                for (IAnimalsUseCaseOutputPort fragment:
                        allFragments) {
                    fragment.getDogs(animals);
                }
            }
        });
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment selectedFragment = catsFragment;
        selectedTab = FragmentType.Cats;
        if(tab.parent.getTabAt(1) == tab) {
            selectedFragment = dogsFragment;
            selectedTab = FragmentType.Dogs;
        }
        viewManager.getSupportFragmentManager()
                .beginTransaction()
                .show(selectedFragment)
                .commit();
        for (AnimalsFragment fragment: allFragments) {
            if(fragment!=selectedFragment) {
                viewManager.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_SELECTED_TAB, selectedTab.getFragmentType());
    }
}
