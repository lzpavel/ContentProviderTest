package com.pfl.contentprovidertest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityElement.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
