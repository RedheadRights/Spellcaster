package com.justinplane.spellcaster;

import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.justinplane.spellcaster.models.PlayerData;
import com.justinplane.spellcaster.viewmodels.PlayerDataViewModel;

public class GameController {

    ObservableArrayList<PlayerData> obsData;
    PlayerDataViewModel viewModel;


    public GameController(ObservableArrayList<PlayerData> obsData) {
        this.obsData = obsData;
    }


    public ObservableArrayList<PlayerData> doLogic(){



        return obsData;
    }


}
