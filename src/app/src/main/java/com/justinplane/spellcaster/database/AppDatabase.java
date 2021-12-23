package com.justinplane.spellcaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.justinplane.spellcaster.models.PlayerData;

@Database(entities = {PlayerData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDataDao getPlayerDataDao();
}
