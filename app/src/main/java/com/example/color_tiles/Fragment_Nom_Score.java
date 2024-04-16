package com.example.color_tiles;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.color_tiles.databinding.FragmentNomScoreBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Nom_Score#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Nom_Score extends Fragment {

    private FragmentNomScoreBinding binding;
    private static final String ARG_POSITION = "position";
    private static final String ARG_SCORE = "score";
    private int position;
    private int score;

    public Fragment_Nom_Score() {
        // Required empty public constructor
    }

    public static Fragment_Nom_Score newInstance(int position, int score) {
        Fragment_Nom_Score fragment = new Fragment_Nom_Score();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putInt(ARG_SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            score = getArguments().getInt(ARG_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNomScoreBinding.inflate(inflater, container, false);
        binding.textViewScore.setText(position + " - " + score);
        return binding.getRoot();
    }
}
