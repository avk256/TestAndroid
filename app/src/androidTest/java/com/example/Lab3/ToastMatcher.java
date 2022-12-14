package com.example.Lab3;

import android.os.IBinder;
//import android.provider.DocumentsContract;
//import android.provider.DocumentsContract.Root;
import android.view.WindowManager;

//import androidx.test.espresso.Root;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
//        super.describeTo(description);
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                //means this window isn't contained by any other windows.
                return true;
            }
        }
        return false;
    }
}
