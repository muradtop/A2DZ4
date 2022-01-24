package com.example.a2dz4;

import android.app.Application;

import androidx.room.Room;

import com.example.to_dolistproject.data.NoteDatabase;

public class App extends Application {
    static App app;
    NoteDatabase db;

    public static App getApp() {
        return app;
    }

    public void onCreate() {
        super.onCreate();
        app = this;
        db = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "database_").allowMainThreadQueries().build();

    }

    public NoteDatabase getDb() {
        return db;
    }


}
