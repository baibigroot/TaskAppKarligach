package com.geek.taskapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.geek.taskapp.App;
import com.geek.taskapp.R;
import com.geek.taskapp.TaskModel;
import com.geek.taskapp.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private NavController navController;
    private TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       navController = NavHostFragment.findNavController(this) ;

       binding = FragmentFormBinding.inflate(inflater, container, false);
       checkData();

       binding.addButton.setOnClickListener(v -> {
           save();
       });
       return binding.getRoot();
    }


    private void checkData() {
        if (getArguments() != null) {
            this.model = (TaskModel) getArguments().getSerializable("TEXT_KEY");
        binding.editText.setText(model.getTitle());
        }
    }

    private void save() {
        String text = binding.editText.getText().toString();

        if (model!=null){
            model.setTitle(text);
            App.appDataBase.taskModelDao().update(model);
        }else {
            model = new TaskModel(text,System.currentTimeMillis());
            App.appDataBase.taskModelDao().insert(model);
        }

        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
    }
}