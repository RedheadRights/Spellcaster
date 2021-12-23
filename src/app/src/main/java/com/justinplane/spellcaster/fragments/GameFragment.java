package com.justinplane.spellcaster.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justinplane.spellcaster.R;
import com.justinplane.spellcaster.models.PlayerData;
import com.justinplane.spellcaster.viewmodels.PlayerDataViewModel;


public class GameFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        PlayerDataViewModel viewModel = new ViewModelProvider(getActivity()).get(PlayerDataViewModel.class);
        ObservableArrayList<PlayerData> obsData = viewModel.getObsPlayerData();

        // Update Data being displayed
        AppCompatTextView p1Name = view.findViewById(R.id.displayPlayer1NameGame);
        AppCompatTextView p1Health = view.findViewById(R.id.displayPlayer1HealthGame);
        AppCompatTextView p1RH = view.findViewById(R.id.p1RightHandTextGame);
        AppCompatTextView p1LH = view.findViewById(R.id.p1LeftHandTextGame);
        AppCompatTextView p1Monsters = view.findViewById(R.id.displayPlayer1MonstersGame);
        AppCompatTextView p1Effects = view.findViewById(R.id.displayPlayer1EffectsGame);

        AppCompatTextView p2Name = view.findViewById(R.id.displayPlayer2NameGame);
        AppCompatTextView p2Health = view.findViewById(R.id.displayPlayer2HealthGame);
        AppCompatTextView p2RH = view.findViewById(R.id.p2RightHandTextGame);
        AppCompatTextView p2LH = view.findViewById(R.id.p2LeftHandTextGame);
        AppCompatTextView p2Monsters = view.findViewById(R.id.displayPlayer2MonstersGame);
        AppCompatTextView p2Effects = view.findViewById(R.id.displayPlayer2EffectsGame);

        p1Name.setText(obsData.get(0).playerName);
        p1Health.setText(String.valueOf(obsData.get(0).health));
        p1RH.setText(obsData.get(0).rightHand);
        p1LH.setText(obsData.get(0).leftHand);
        p1Monsters.setText(obsData.get(0).monsters);
        p1Effects.setText(obsData.get(0).effects);

        p2Name.setText(obsData.get(1).playerName);
        p2Health.setText(String.valueOf(obsData.get(1).health));
        p2RH.setText(obsData.get(1).rightHand);
        p2LH.setText(obsData.get(1).leftHand);
        p2Monsters.setText(obsData.get(1).monsters);
        p2Effects.setText(obsData.get(1).effects);

        //Button Control
        view.findViewById(R.id.nextGameButton).setOnClickListener(v -> {

            //Switch to Turn Fragment
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new TurnFragment(0), null)
                    .setReorderingAllowed(true)
                    .commit();
        });
        return view;
    }
}