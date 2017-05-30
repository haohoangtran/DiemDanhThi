package lachongmedia.vn.demodiemdanh;

import android.app.Application;

/**
 * Created by tranh on 5/29/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypeFaceUtils.overrideFont(getApplicationContext(), "SERIF", "fonts/Arimo-Bold.ttf");
    }
}
