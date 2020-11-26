package com.elegion.test.behancer.ui.profile;

import android.util.Log;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {

    private ProfileView mView;

    private String mUsername;

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    public void setView(ProfileView view){
        mView = view;
    }


    public ProfilePresenter(Storage storage, BehanceApi api, ProfileView view){
        Log.d("PRESENTER", "ProfilePresenter created");
        mStorage = storage;
        mApi = api;
        mView = view;
    }

//    @Inject
//    public ProfilePresenter(String username, ProfileView view){
//        mUsername = username;
//        mView = view;
//    }

    public void getProfile(String mUsername){
         mCompositeDisposable.add(mApi.getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable ->mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> {
                            mView.showProfile();
                            mView.bind(response.getUser());
                        },
                        throwable -> {
                            mView.showError();
                        })
        );
    }
}
