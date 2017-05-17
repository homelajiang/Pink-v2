package com.lxy.pink.ui.video.detail;

import com.lxy.pink.utils.schedulers.BaseSchedulerProvider;
import com.lxy.pink.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by homelajiang on 2017/5/17 0017.
 */
public class ACVideoPresenterTest {

    @Mock
    private ACVideoContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;
    private ACVideoPresenter acVideoPresenter;

    @Before
    public void setupVideoPresenter(){
        MockitoAnnotations.initMocks(this);
        mSchedulerProvider = new ImmediateSchedulerProvider();
        acVideoPresenter = new ACVideoPresenter(mView,mSchedulerProvider,3529332);
    }

    @Test
    public void getVideoInfo() throws Exception {

    }

}