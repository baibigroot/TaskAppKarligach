package com.example.taskappkarligach.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taskappkarligach.Prefs;
import com.example.taskappkarligach.R;
import com.example.taskappkarligach.databinding.FragmentBoardBinding;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private NavController navController;
    private BoardAdapter boardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardAdapter = new BoardAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        ViewPager2 viewPager2 = binding.pager;
        viewPager2.setAdapter(boardAdapter);
        binding.dots.setViewPager2(viewPager2);
        boardAdapter.setOnClickListener(new BoardAdapter.InterfaceListener() {
            @Override
            public void OnClick() {
                close();
            }
        });

        return binding.getRoot();
    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
    }






}

