package com.pfl.contentprovidertest.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Query("SELECT * FROM content_provider_table")
    List<EntityElement> getAll();

    @Query("SELECT * FROM content_provider_table")
    Cursor getCursorAll();

    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<EntityElement> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    EntityElement findByName(String first, String last);*/


    @Insert
    void insert(EntityElement entityElement);

    @Delete
    void delete(EntityElement entityElement);
}
