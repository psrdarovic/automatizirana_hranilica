package com.nizetic.yuumi;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={Zdjela.class}, version= 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ZdjelaDAO zdjelaDAO();
}
