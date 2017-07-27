package com.lxy.pink.ui.Text;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.source.db.DaoMasterHelper;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuan on 2017/5/12.
 */

public class java {

    public Observable<File> createFile(final String filePath) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                File file = new File(filePath);
                try {
                    boolean res = file.createNewFile();
                    if(res){
                        subscriber.onNext(new File(filePath));
                        subscriber.onCompleted();
                    }else {
                        subscriber.onError(new Throwable("创建文件失败"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    //使用

    public void main(){
        createFile("xxxxxxx")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(File file) {

                    }
                });
    }

}
