package com.lxy.pink.ui.video;

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

    private ACRecommend data;

    VideoFragmentAdapter() {
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
        ACHeaderModel headerModel = new ACHeaderModel_()
                .dataBean(dataBean);
        addModel(headerModel);

        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACVideoModel videoModel = new ACVideoModel_()
                    .contentBean(contentsBean);
            addModel((videoModel));
        }
    }

    private void addArticleModel(ACRecommend.DataBean dataBean) {
        ACHeaderModel headerModel = new ACHeaderModel_()
                .dataBean(dataBean);
        addModel(headerModel);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACArticleModelH articleModelH = new ACArticleModelH_()
                    .contentBean(contentsBean);
            addModel((articleModelH));
        }
    }

    private void addBangumiModel(ACRecommend.DataBean dataBean) {
        ACHeaderModel headerModel = new ACHeaderModel_()
                .dataBean(dataBean);
        addModel(headerModel);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACFunModel funModel = new ACFunModel_()
                    .contentBean(contentsBean);
            addModel((funModel));
        }
    }

    private void addCarouselModel(ACRecommend.DataBean dataBean) {
        ACCarouselModel carouselModel = new ACCarouselModel_()
                .dataBean(dataBean);
        addModel(carouselModel);

        addFunTimeModel();
        addLoginModel();
    }

    private void addBannerModel(ACRecommend.DataBean dataBean) {
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACBannerModel bannerModel = new ACBannerModel_()
                    .contentBean(contentsBean);
            addModel((bannerModel));
        }
    }

    private void addVideoBananaListModel(ACRecommend.DataBean dataBean) {
        ACHeaderModel headerModel = new ACHeaderModel_()
                .dataBean(dataBean);
        addModel(headerModel);
        for (ACRecommend.DataBean.ContentsBean contentsBean : dataBean.getContents()) {
            ACBananaVideoModel bananaVideoModel = new ACBananaVideoModel_()
                    .contentBean(contentsBean);
            addModel((bananaVideoModel));
        }
    }

    private void addFunTimeModel() {
        ACFunTimeModel funTimeModel = new ACFunTimeModel();
        addModel(funTimeModel);
    }

    private void addLoginModel() {
        ACLoginModel loginModel = new ACLoginModel_();
        addModel(loginModel);
    }
}
