package com.justinplane.spellcaster.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
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

import java.util.concurrent.atomic.AtomicReference;


public class TurnFragment extends Fragment {

    // This constructor passes the turn number so this fragment can decide whose turn it is
    private int turnNum;
    public TurnFragment(int turnNum) {
        this.turnNum = turnNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and get viewModel
        View view = inflater.inflate(R.layout.fragment_turn, container, false);
        PlayerDataViewModel viewModel = new ViewModelProvider(getActivity()).get(PlayerDataViewModel.class);
        ObservableArrayList<PlayerData> obsData = viewModel.getObsPlayerData();

        // Save hands for players
        String rHandData = obsData.get(turnNum).rightHand;
        String lHandData = obsData.get(turnNum).leftHand;
        AtomicReference<String> newRHandData = new AtomicReference<>(rHandData);
        AtomicReference<String> newLHandData = new AtomicReference<>(lHandData);
        AtomicReference<String> currentGesture = new AtomicReference<>("");

        // Update Data being displayed
        AppCompatTextView p1Name = view.findViewById(R.id.displayPlayer1Name);
        AppCompatTextView p1Health = view.findViewById(R.id.displayPlayer1Health);
        AppCompatTextView p1RH = view.findViewById(R.id.p1RightHandText);
        AppCompatTextView p1LH = view.findViewById(R.id.p1LeftHandText);
        AppCompatTextView p1Monsters = view.findViewById(R.id.displayPlayer1Monsters);
        AppCompatTextView p1Effects = view.findViewById(R.id.displayPlayer1Effects);

        AppCompatTextView p2Name = view.findViewById(R.id.displayPlayer2Name);
        AppCompatTextView p2Health = view.findViewById(R.id.displayPlayer2Health);
        AppCompatTextView p2RH = view.findViewById(R.id.p2RightHandText);
        AppCompatTextView p2LH = view.findViewById(R.id.p2LeftHandText);
        AppCompatTextView p2Monsters = view.findViewById(R.id.displayPlayer2Monsters);
        AppCompatTextView p2Effects = view.findViewById(R.id.displayPlayer2Effects);

        if(turnNum < 1) {
            p1Name.setText(obsData.get(0).playerName);
            p1Health.setText(String.valueOf(obsData.get(0).health));
            p1RH.setText(obsData.get(0).rightHand);
            p1LH.setText(obsData.get(0).leftHand);
            p1Monsters.setText(obsData.get(0).monsters);
            p1Effects.setText(obsData.get(0).effects);
        } else {
            p1Name.setText(obsData.get(0).playerName);
            p1Health.setText(String.valueOf(obsData.get(0).health));
            p1RH.setText(obsData.get(0).rightHand.substring(0, obsData.get(0).rightHand.length() - 1));
            p1LH.setText(obsData.get(0).leftHand.substring(0, obsData.get(0).leftHand.length() - 1));
            p1Monsters.setText(obsData.get(0).monsters);
            p1Effects.setText(obsData.get(0).effects);
        }

        p2Name.setText(obsData.get(1).playerName);
        p2Health.setText(String.valueOf(obsData.get(1).health));
        p2RH.setText(obsData.get(1).rightHand);
        p2LH.setText(obsData.get(1).leftHand);
        p2Monsters.setText(obsData.get(1).monsters);
        p2Effects.setText(obsData.get(1).effects);

        // Gesture Button Control
        AppCompatButton fButton = view.findViewById(R.id.fButton);
        AppCompatButton pButton = view.findViewById(R.id.pButton);
        AppCompatButton sButton = view.findViewById(R.id.sButton);
        AppCompatButton wButton = view.findViewById(R.id.wButton);
        AppCompatButton dButton = view.findViewById(R.id.dButton);
        AppCompatButton cButton = view.findViewById(R.id.cButton);

        fButton.setOnClickListener(v -> {
            fButton.setEnabled(false);
            currentGesture.set("F");
            pButton.setEnabled(true);
            sButton.setEnabled(true);
            wButton.setEnabled(true);
            dButton.setEnabled(true);
            cButton.setEnabled(true);
        });
        pButton.setOnClickListener(v -> {
            pButton.setEnabled(false);
            currentGesture.set("P");
            fButton.setEnabled(true);
            sButton.setEnabled(true);
            wButton.setEnabled(true);
            dButton.setEnabled(true);
            cButton.setEnabled(true);
        });
        sButton.setOnClickListener(v -> {
            sButton.setEnabled(false);
            currentGesture.set("S");
            pButton.setEnabled(true);
            fButton.setEnabled(true);
            wButton.setEnabled(true);
            dButton.setEnabled(true);
            cButton.setEnabled(true);
        });
        wButton.setOnClickListener(v -> {
            wButton.setEnabled(false);
            currentGesture.set("W");
            pButton.setEnabled(true);
            sButton.setEnabled(true);
            fButton.setEnabled(true);
            dButton.setEnabled(true);
            cButton.setEnabled(true);
        });
        dButton.setOnClickListener(v -> {
            dButton.setEnabled(false);
            currentGesture.set("D");
            pButton.setEnabled(true);
            sButton.setEnabled(true);
            wButton.setEnabled(true);
            fButton.setEnabled(true);
            cButton.setEnabled(true);
        });
        cButton.setOnClickListener(v -> {
            cButton.setEnabled(false);
            currentGesture.set("C");
            pButton.setEnabled(true);
            sButton.setEnabled(true);
            wButton.setEnabled(true);
            dButton.setEnabled(true);
            fButton.setEnabled(true);
        });

        // Hand Button Control
        AppCompatButton rHButton = view.findViewById(R.id.rightHandButton);
        AppCompatButton lHButton = view.findViewById(R.id.leftHandButton);

        rHButton.setOnClickListener(v -> {
            newRHandData.set(rHandData);
            newRHandData.set(newRHandData.get() + currentGesture.get());
            if(turnNum == 0) {
                p1RH.setText(newRHandData.get());
            }
            if(turnNum == 1) {
                p2RH.setText(newRHandData.get());
            }
        });

        lHButton.setOnClickListener(v -> {
            newLHandData.set(lHandData);
            newLHandData.set(newLHandData.get() + currentGesture.get());
            if(turnNum == 0) {
                p1LH.setText(newLHandData.get());
            }
            if(turnNum == 1) {
                p2LH.setText(newLHandData.get());
            }
        });


        // Next Button Control
        view.findViewById(R.id.nextTurnButton).setOnClickListener(v -> {

            //Switch to New Fragment
            if(newLHandData.get() != lHandData && newRHandData.get() != rHandData) {
                if (turnNum == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new TurnFragment(1), null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, GameFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                viewModel.updatePlayerHands(turnNum, newRHandData.get(), newLHandData.get());
            }
        });



        return view;
    }
}