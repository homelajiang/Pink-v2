package com.lxy.pink.core;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;

/**
 * Created by homelajiang on 2016/11/29 0029.
 */

public class CoreManager implements PinkServiceContract.View {
    private PinkServiceContract.Presenter presenter;
    private static CoreManager instance;

    private CoreManager() {

    }

    public static CoreManager getInstance() {
        if (instance == null) {
            synchronized (CoreManager.class) {
                if (instance == null) {
                    instance = new CoreManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void weatherLoadStart() {

    }

    @Override
    public void weatherLoadEnd() {

    }

    @Override
    public void weatherLoadError(Throwable e) {

    }

    @Override
    public void weatherLoaded(Weather weather) {

    }

    @Override
    public void weatherLocationReq() {
        //nothing to do
    }

    @Override
    public void todoListLoaded(TodoList todoList) {

    }

    @Override
    public void locationStart() {

    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {

    }

    @Override
    public void locationError() {

    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
