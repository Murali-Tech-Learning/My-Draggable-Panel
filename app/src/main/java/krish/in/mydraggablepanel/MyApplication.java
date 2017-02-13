package krish.in.mydraggablepanel;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import nonuithreads.AppLib;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by divum on 24/1/17.
 */

public class MyApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppLib.getInstance(this);
        CalligraphyConfig builder = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Helvetica_Neue_CE_55_Roman.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(builder);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
