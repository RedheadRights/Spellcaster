package com.justinplane.spellcaster.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.justinplane.spellcaster.R;
import com.justinplane.spellcaster.models.PlayerData;
import com.justinplane.spellcaster.viewmodels.PlayerDataViewModel;


public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and declare viewmodel
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_menu, container, false);
        PlayerDataViewModel viewModel = new ViewModelProvider(getActivity()).get(PlayerDataViewModel.class);
        ObservableArrayList<PlayerData> obsData = viewModel.getObsPlayerData();

        //Button Control
        view.findViewById(R.id.startButton).setOnClickListener(v -> {

            AppCompatEditText player1 = view.findViewById(R.id.editPlayer1Name);
            AppCompatEditText player2 = view.findViewById(R.id.editPlayer2Name);

            if(!player1.getText().toString().contentEquals("") && !player2.getText().toString().contentEquals("")) {
                viewModel.clean();
                viewModel.createData(player1.getText().toString());
                viewModel.createData(player2.getText().toString());

                //Switch to Game Fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, GameFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ImageView slime = view.findViewById(R.id.slimeView);
        slime.setBackgroundResource(R.drawable.test_animation);

        AnimationDrawable slimeAnimation = (AnimationDrawable) slime.getBackground();

        slime.setOnClickListener(v -> {
            slimeAnimation.stop();
            slimeAnimation.start();
        });

        view.findViewById(R.id.resumeButton).setOnClickListener(v -> {
            if(!obsData.isEmpty()) {
                // Truncate uneven number of moves upon resuming game(TODO: THIS IS A BAD SOLUTION, FIX LATER)
                String p1RHand = obsData.get(0).rightHand;
                String p1LHand = obsData.get(0).leftHand;
                String p2Hand = obsData.get(1).rightHand;
                if (p1RHand.length() > p2Hand.length()) {
                    viewModel.updatePlayerHands(0, p1RHand.substring(0, p1RHand.length() - 1), p1LHand.substring(0, p1LHand.length() - 1));
                }

                //Switch to Game Fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, GameFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}