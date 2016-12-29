package com.lxy.pink.ui.video;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.models.ACArticleModelH;
import com.lxy.pink.ui.video.models.ACArticleModelH_;
import com.lxy.pink.ui.video.models.ACBananaVideoModel;
import com.lxy.pink.ui.video.models.ACBananaVideoModel_;
import com.lxy.pink.ui.video.models.ACBannerModel;
import com.lxy.pink.ui.video.models.ACBannerModel_;
import com.lxy.pink.ui.video.models.ACCarouselModel;
import com.lxy.pink.ui.video.models.ACCarouselModel_;
import com.lxy.pink.ui.video.models.ACFunModel;
import com.lxy.pink.ui.video.models.ACFunModel_;
import com.lxy.pink.ui.video.models.ACFunTimeModel;
import com.lxy.pink.ui.video.models.ACFunTimeModel_;
import com.lxy.pink.ui.video.models.ACHeaderModel;
import com.lxy.pink.ui.video.models.ACHeaderModel_;
import com.lxy.pink.ui.video.models.ACLoginModel;
import com.lxy.pink.ui.video.models.ACLoginModel_;
import com.lxy.pink.ui.video.models.ACVideoModel;
import com.lxy.pink.ui.video.models.ACVideoModel_;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class VideoFragmentAdapter extends EpoxyAdapter {

    private final Context context;
    private ACRecommend data;

    VideoFragmentAdapter(Context context) {
        this.context = context;
        enableDiffing();
//        ACLoginModel acLoginModel = new ACLoginModel_();
//        ACVideoModel acVideoModel = new ACVideoModel_();
//        addModels(acLoginModel,acVideoModel);
    }

    public void setData(ACRecommend data) {
        this.data = data;
        for (ACRecommend.DataBean dataBean : data.getData()) {
            switch (dataBean.getType().getId()) {
                case 1://videos
                    addVideoModel(dataBean);
                    break;
                case 2://article
                    addArticleModel(dataBean);
                    break;
                case 3://bangumi
                    addBangumiModel(dataBean);
                    break;
                case 4:
                    break;
                case 5://carousels
                    addCarouselModel(dataBean);
                    break;
                case 6://banner
                    addBannerModel(dataBean);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12://videos_banana_list
                    addVideoBananaListModel(dataBean);
                    break;
                default:
            }
        }

    }

    private void addVideoModel(ACRecommend.DataBean dataBean) {
        addHeaderModel(dataBean);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACVideoModel videoModel = new ACVideoModel_()
                    .contentBean(contentsBean)
                    .acItemClickListener(contentsBeanACItemClickListener);
            addModel((videoModel));
        }
    }

    private void addArticleModel(ACRecommend.DataBean dataBean) {
        addHeaderModel(dataBean);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACArticleModelH articleModelH = new ACArticleModelH_()
                    .contentBean(contentsBean)
                    .acItemClickListener(contentsBeanACItemClickListener);
            addModel((articleModelH));
        }
    }

    private void addBangumiModel(ACRecommend.DataBean dataBean) {
        addHeaderModel(dataBean);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACFunModel funModel = new ACFunModel_()
                    .contentBean(contentsBean)
                    .acItemClickListener(contentsBeanACItemClickListener);
            addModel((funModel));
        }
    }

    private void addCarouselModel(ACRecommend.DataBean dataBean) {
        ACCarouselModel carouselModel = new ACCarouselModel_()
                .dataBean(dataBean)
                .acItemClickListener(contentsBeanACItemClickListener);
        addModel(carouselModel);

        addFunTimeModel();
        addLoginModel();
    }

    private void addBannerModel(ACRecommend.DataBean dataBean) {
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACBannerModel bannerModel = new ACBannerModel_()
                    .contentBean(contentsBean)
                    .acItemClickListener(contentsBeanACItemClickListener);
            addModel((bannerModel));
        }
    }

    private void addVideoBananaListModel(ACRecommend.DataBean dataBean) {
        addHeaderModel(dataBean);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACBananaVideoModel bananaVideoModel = new ACBananaVideoModel_()
                    .contentBean(contentsBean)
                    .acItemClickListener(contentsBeanACItemClickListener);
            addModel((bananaVideoModel));
        }
    }

    private void addFunTimeModel() {
        ACFunTimeModel funTimeModel = new ACFunTimeModel_()
                .clickListener(clickListener);
        addModel(funTimeModel);
    }

    private void addLoginModel() {
        ACLoginModel_ loginModel = new ACLoginModel_()
                .clickListener(clickListener);
        addModel(loginModel);
    }

    private void addHeaderModel(ACRecommend.DataBean dataBean) {
        ACHeaderModel headerModel = new ACHeaderModel_()
                .dataBean(dataBean)
                .acItemClickListener(dataBeanACItemClickListener);
        addModel(headerModel);
    }

    private ACItemClickListener<ACRecommend.DataBean.ContentsBean> contentsBeanACItemClickListener
            = new ACItemClickListener<ACRecommend.DataBean.ContentsBean>() {
        @Override
        public void onItemClicked(View v, ACRecommend.DataBean.ContentsBean data) {
            Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };

    private ACItemClickListener<ACRecommend.DataBean> dataBeanACItemClickListener
            = new ACItemClickListener<ACRecommend.DataBean>() {
        @Override
        public void onItemClicked(View v, ACRecommend.DataBean data) {
            Toast.makeText(context, data.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, v.getClass().getName(), Toast.LENGTH_SHORT).show();
        }
    };

    public interface ACItemClickListener<T> {
        void onItemClicked(View v, T data);
    }

}
