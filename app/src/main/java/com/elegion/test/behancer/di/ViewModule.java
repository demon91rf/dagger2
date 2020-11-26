package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.profile.ProfileView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private ProfileView view;

    public ViewModule(ProfileView view){
        this.view = view;
    }

    @Provides
    @PerFragment
    ProfileView provideView(){
        return  view;
    }
}
