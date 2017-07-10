package com.example.agnes.fischlogger;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Agnes on 10.07.2017.
 */

public class FishLogger extends Application {
    private FishDataSource dataSource;
    public FishDataSource getDataSource(){
        return dataSource;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        dataSource = new FishDataSource(context);
    }

    @Override
    public void onTerminate() {
        if (dataSource != null) {
            try{dataSource.close();}
            catch (java.lang.Exception e){}
        }

        super.onTerminate();
    }
}
