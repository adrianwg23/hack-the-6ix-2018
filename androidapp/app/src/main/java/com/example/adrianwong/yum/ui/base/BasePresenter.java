package com.example.adrianwong.yum.ui.base;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V> {

    protected V mView;

    public final void attachView(@NonNull V view) { mView = view; }

    public final void detachView() {
        mView = null;
    }
}
