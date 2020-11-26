package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.AppComponent;
import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.DaggerAppComponent;
import com.elegion.test.behancer.di.NetworkModule;
import com.elegion.test.behancer.di.PresenterComponent;
import com.elegion.test.behancer.di.PresenterModule;
import com.elegion.test.behancer.di.ViewModule;
import com.elegion.test.behancer.ui.profile.ProfileView;

/**
 * Created by Vladislav Falzan.
 */

public class AppDelegate extends Application {

    private static AppDelegate instance;

    public static AppDelegate getInstance(){
        return instance;
    }

    private AppComponent sAppComponent;
    private PresenterComponent sPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return sAppComponent;
    }

    public PresenterComponent plusPresenterComponent(ProfileView view){
        if (sPresenterComponent == null){
//           sPresenterComponent = sAppComponent.plusPresenterComponent(new PresenterModule());
            sPresenterComponent = sAppComponent.presenterComponentBuilder()
                    .presenterModule(new PresenterModule())
                    .viewModule(new ViewModule(view))
                    .build();
        }
        return sPresenterComponent;
    }

    public void clearPresenterComponent(){
        sPresenterComponent = null;
    }
}
