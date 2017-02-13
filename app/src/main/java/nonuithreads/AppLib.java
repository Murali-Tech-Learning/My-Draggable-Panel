package nonuithreads;

import android.content.Context;

/**
 * Created by divum on 24/1/17.
 */

public class AppLib {
    private static AppLib instance;
    private static Context context;

    private AppLib() {
        this(null);
    }

    private AppLib(Context context) {
        this.context = context;
        instance = this;
    }

    public static AppLib getInstance() {
        if (instance == null) {
            new AppLib();
        }
        return instance;
    }

    public static AppLib getInstance(Context context) {
        if (instance == null) {
            new AppLib(context);
        }
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return instance;
    }

}
