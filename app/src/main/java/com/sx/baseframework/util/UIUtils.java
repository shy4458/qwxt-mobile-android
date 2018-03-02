package com.sx.baseframework.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

public final class UIUtils {

    private UIUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    private static Toast toast;

    /**
     * Show single toast. Only one {@link Toast} instance.
     *
     * @param context Context
     * @param message Toast message.
     */
    public static void showToast(@NonNull Context context, @NonNull String message) {
        if(toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
