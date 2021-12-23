package com.justinplane.spellcaster.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.justinplane.spellcaster.models.PlayerData;

import java.util.List;

@Dao
public interface PlayerDataDao {
    @Insert
    public long insert(PlayerData data);

    @Query("SELECT * FROM playerdata")
    public List<PlayerData> getAll();

    @Query("SELECT * FROM playerdata WHERE pid = :id LIMIT 1")
    public PlayerData findById(long id);

    @Update
    public void update(PlayerData data);

    @Delete
    public void delete(PlayerData data);
}
