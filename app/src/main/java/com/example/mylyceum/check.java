package com.example.mylyceum;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mylyceum.ui.home.HomeFragment;

public class check
{
    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_CLASS = "class";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setClass(Context ctx, String userClass){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_CLASS, userClass);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserClass(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_CLASS, "");
    }
}
