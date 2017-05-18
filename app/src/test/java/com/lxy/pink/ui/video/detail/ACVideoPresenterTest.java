package com.lxy.pink.ui.video.detail;

import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.utils.schedulers.BaseSchedulerProvider;
import com.lxy.pink.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by homelajiang on 2017/5/17 0017.
 */
public class ACVideoPresenterTest {

    @Mock
    private ACVideoContract.View mView;
    @Mock
    private AppRepository appRepository;

    private BaseSchedulerProvider mSchedulerProvider;
    private ACVideoPresenter acVideoPresenter;
    private ACVideoInfo.DataBean.VideosBean videoInfo;

    @Before
    public void setupVideoPresenter(){
        MockitoAnnotations.initMocks(this);
        mSchedulerProvider = new ImmediateSchedulerProvider();
        acVideoPresenter = new ACVideoPresenter(mView,appRepository,mSchedulerProvider,3529332);
        videoInfo = new ACVideoInfo.DataBean.VideosBean();
    }

    @Test
    public void getVideoInfo() throws Exception {
//            when(appRepository.getVideoInfo(2321321)).thenReturn(Observable.create(videoInfo));
//        acVideoPresenter.getVideoInfo(2321434);
//        verify(mView).getVideoInfoFail();
    }

}