package com.justinplane.spellcaster.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import java.util.ArrayList;

@Entity
public class PlayerData {
    @PrimaryKey(autoGenerate = true)
    public long pid;
    @ColumnInfo(name = "player_name")
    public String playerName;
    @ColumnInfo
    public int health;
    @ColumnInfo
    public String leftHand;
    @ColumnInfo
    public String rightHand;
    @ColumnInfo
    public String monsters;
    @ColumnInfo
    public String effects;
}
