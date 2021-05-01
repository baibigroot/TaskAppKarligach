package com.example.taskappkarligach;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private final SharedPreferences preferences;

    public Prefs(Context context) {
        this.preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);

    }

    public void saveBoardState(){
      preferences.edit().putBoolean("key", true).apply();
    }

    public boolean getBoardState(){
        return preferences.getBoolean("key", false);
    }

    public void saveText(String textSave){
        preferences.edit().putString("key2", textSave).apply();
    }

    public String getText(){
        return preferences.getString("key2", "");
    }
}
