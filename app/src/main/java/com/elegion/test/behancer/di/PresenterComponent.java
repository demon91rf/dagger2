package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.profile.ProfileFragment;

import dagger.Component;
import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {PresenterModule.class, ViewModule.class})
public interface PresenterComponent {
    void inject(ProfileFragment injector);

    @Subcomponent.Builder
    interface Builder {
        PresenterComponent.Builder presenterModule(PresenterModule presenterModule);
        PresenterComponent.Builder viewModule(ViewModule viewModule);
        PresenterComponent build();
    }
}
