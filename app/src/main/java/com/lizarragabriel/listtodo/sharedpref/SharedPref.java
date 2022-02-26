package com.lizarragabriel.listtodo.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("todolistpreferences", Context.MODE_PRIVATE);
    }

    public void mSetUserSession(String username, int userid) {
        sharedPreferences.edit().putInt(username, userid).apply();
    }

    public int mGetUserSSession(String username) {
        return sharedPreferences.getInt(username, 0);
    }

}
