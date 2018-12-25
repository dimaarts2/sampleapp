package dimaarts.ru.sampleapplication.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

public interface IViewManager {
    @Nullable
    public <T extends View> T findViewById(@IdRes int id);
    public FragmentManager getSupportFragmentManager();}
