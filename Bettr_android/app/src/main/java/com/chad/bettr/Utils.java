package com.chad.bettr;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.TypedValue;

public class Utils {

    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String SONG_STRING_QUALIFIER = "%1$s";
    private static final String ARTIST_STRING_QUALIFIER = "%2$s";

    @SuppressLint("StaticFieldLeak")
    public static void executeAsync(final Runnable doInBackgroundRunnable) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                doInBackgroundRunnable.run();
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void executeAsync(final Runnable doInBackgroundRunnable, final Runnable onPostExecuteRunnable) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                doInBackgroundRunnable.run();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                onPostExecuteRunnable.run();
            }
        }.execute();
    }

    public static String getTimestampString(long timestamp) {
        return DateUtils.getRelativeTimeSpanString(
                timestamp,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL).toString();
    }

    public static float dpToPx(float dp) {
        Resources r = App.getContext().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }




}
