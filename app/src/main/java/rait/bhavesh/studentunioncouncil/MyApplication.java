package rait.bhavesh.studentunioncouncil;

import com.firebase.client.Firebase;

/**
 * Created by Archana on 6/5/2016.
 */
public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Firebase.setAndroidContext(this);
    }
}
