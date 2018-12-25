package dimaarts.ru.sampleapplication.mvp.list;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;
import dimaarts.ru.sampleapplication.mvp.IViewManager;

import java.util.ArrayList;
import java.util.List;

import static dimaarts.ru.sampleapplication.ui.list.AnimalsFragment.FRAGMENT_LIST_SAVE_STATE;

public class AnimalsListView implements IAnimalsListView {
    private RecyclerView mListView;
    private AnimalsAdapter mAdapter;
    private IAnimalsListPresenter callbackPresenter;

    public AnimalsListView(IViewManager viewManager) {
        mListView = viewManager.findViewById(R.id.list);
        if(mListView!=null) {
            mListView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mListView.getContext());
            mListView.setLayoutManager(layoutManager);
        }

        // specify an adapter (see also next example)
        mAdapter = new AnimalsAdapter();
        mAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = mListView.getChildLayoutPosition(v);
                AnimalEntity selectedEntity = mAdapter.getData().get(itemPosition);
                if(callbackPresenter!=null) {
                    callbackPresenter.onSelect(selectedEntity, v.getContext());
                }
            }
        });
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void setAnimals(List<AnimalEntity> animals) {
        mAdapter.setData(animals);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(FRAGMENT_LIST_SAVE_STATE, (ArrayList<AnimalEntity>) mAdapter.getData());
    }

    public void setCallbackPresenter(IAnimalsListPresenter callbackPresenter) {
        this.callbackPresenter = callbackPresenter;
    }
}
