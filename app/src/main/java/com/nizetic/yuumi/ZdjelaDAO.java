package com.nizetic.yuumi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ZdjelaDAO {
    @Insert
    public long insert (Zdjela zdjela);
    @Insert
    public long[] insertALL (Zdjela ...zdjele);

    @Delete
    public void delete (Zdjela zdjela);
    @Delete
    public void deleteAll (Zdjela ...zdjele);

    @Update
    public void update (Zdjela zdjela);
    @Update
    public void updateAll (Zdjela ...zdjele);

    @Query("SELECT * FROM Zdjela ")
    public List<Zdjela> fetchAll();
    @Query("select * from Zdjela where id= :id ")
    public List <Zdjela> fetch(long id);


}
