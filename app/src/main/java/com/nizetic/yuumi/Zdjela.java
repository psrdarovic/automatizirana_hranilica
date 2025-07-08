package com.nizetic.yuumi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Zdjela {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stanje")
    private int stanje;

    public Zdjela(int stanje) {
        this.stanje = stanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }
}
