package com.justinplane.spellcaster.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.justinplane.spellcaster.database.AppDatabase;
import com.justinplane.spellcaster.models.PlayerData;

import java.util.ArrayList;

public class PlayerDataViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<PlayerData> player1 = new MutableLiveData<>();
    private MutableLiveData<PlayerData> player2 = new MutableLiveData<>();
    private ObservableArrayList<PlayerData> obsPlayerData = new ObservableArrayList<>();

    public PlayerDataViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, "playerdb").build();

        new Thread(() -> {
            ArrayList<PlayerData> allPlayerData = (ArrayList<PlayerData>) database.getPlayerDataDao().getAll();
            obsPlayerData.addAll(allPlayerData);
        }).start();
    }

    public ObservableArrayList<PlayerData> getObsPlayerData() {
        return obsPlayerData;
    }

    public void setObsPlayerData(ObservableArrayList<PlayerData> obsPlayerData) {
        this.obsPlayerData = obsPlayerData;
    }

    public void createData(String playerName) {
        new Thread(() ->{
            PlayerData newData = new PlayerData();
            newData.playerName = playerName;
            newData.health = 15;
            newData.leftHand = "";
            newData.rightHand = "";
            newData.monsters = "";
            newData.effects = "";
            newData.pid = database.getPlayerDataDao().insert(newData);
            obsPlayerData.add(newData);
        }).start();

    }

    public void clean(){
        new Thread(() -> {
            int size = obsPlayerData.size();
            for(int i = 0; i < size; i++) {
                    database.getPlayerDataDao().delete(obsPlayerData.get(0));
                    obsPlayerData.remove(0);
                }
        }).start();
    }

    public void updatePlayerHands(int PlayerNum, String rightHand, String leftHand){
        new Thread(() -> {
            PlayerData newData = obsPlayerData.get(PlayerNum);
            newData.rightHand = rightHand;
            newData.leftHand = leftHand;
            database.getPlayerDataDao().update(newData);
        }).start();

    }

//    public void updateData(long pid, String playerName, int health, String leftHand,
//                           String rightHand, String monsters, String effects) {
//        new Thread(() ->{
//            PlayerData newData = new PlayerData();
//
//        })
//
//
//    }

}
