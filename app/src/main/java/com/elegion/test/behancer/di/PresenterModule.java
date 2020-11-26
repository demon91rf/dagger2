package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.profile.ProfileView;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @PerFragment
    @Provides
    public ProfilePresenter providePresenter(Storage storage, BehanceApi api, ProfileView view){ return new ProfilePresenter(storage, api, view);}
}
