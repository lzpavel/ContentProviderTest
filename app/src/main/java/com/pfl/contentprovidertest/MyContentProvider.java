package com.pfl.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.pfl.contentprovidertest.db.AppDatabase;
import com.pfl.contentprovidertest.db.EntityElement;
import com.pfl.contentprovidertest.db.MyDao;

import java.util.List;

public class MyContentProvider extends ContentProvider {

    private final String LOG_TAG = "MyContentProvider";

    static final String AUTHORITY = "com.pfl.contentprovidertest";
    static final String PATH = "table";
    public static final Uri COMMON_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + PATH);

    //MIME table types
    //multi
    static final String MIME_CONTENT_TYPE_MULTI = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PATH;

    //single
    static final String MIME_CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PATH;

    static final int URI_ALL = 1;
    static final int URI_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, PATH, URI_ALL);
        uriMatcher.addURI(AUTHORITY, PATH + "/#", URI_ID);
    }

    AppDatabase db;
    MyDao myDao;
    List<EntityElement> entityElements;

    @Override
    public boolean onCreate() {
        db = Room.databaseBuilder(getContext(),
                        AppDatabase.class, "content_provider_db")
                .allowMainThreadQueries()
                .build();
        myDao = db.myDao();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder)  {
        Log.d(LOG_TAG, "query: " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_ALL:
                Log.d(LOG_TAG, "URI for ALL");
                break;
            case URI_ID:
                Log.d(LOG_TAG, "URI by ID");
                break;
            default:
                Log.d(LOG_TAG, "Wrong URI");
                break;
        }

        Cursor cursor = myDao.getCursorAll();
        cursor.setNotificationUri(getContext().getContentResolver(), COMMON_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(LOG_TAG, "getType: " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_ALL:
                return MIME_CONTENT_TYPE_MULTI;
            case URI_ID:
                return MIME_CONTENT_TYPE_ITEM;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
