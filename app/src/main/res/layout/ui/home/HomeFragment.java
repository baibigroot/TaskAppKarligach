package com.geek.taskapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.geek.taskapp.App;
import com.geek.taskapp.R;
import com.geek.taskapp.TaskModel;
import com.geek.taskapp.adapter.ItemClickListener;
import com.geek.taskapp.adapter.TaskAdapter;
import com.geek.taskapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClickListener {
    TaskAdapter adapter;
    private FragmentHomeBinding binding;
    private int position;
    private NavController navController;
    List<TaskModel> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();
        list = new ArrayList<>();
        App.appDataBase.taskModelDao().insert(new TaskModel("Mira",System.currentTimeMillis()));
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       navController = NavHostFragment.findNavController(this);
       binding = FragmentHomeBinding.inflate(inflater, container, false);
       binding.recyclerView.setAdapter(adapter);
       adapter.setOnClickListener(this);
       checkFragmentResult();
       binding.fab.setOnClickListener(v -> {
           navController.navigate(R.id.action_navigation_home_to_formFragment);
       });
       return binding.getRoot();
    }

    private void checkFragmentResult() {
        App.appDataBase.taskModelDao().getAll().observe(getViewLifecycleOwner(),list->{
            this.list = list;
            adapter.addList(list);
        });
    }

    @Override
    public void onItemClick(int position, TaskModel title) {
        this.position = position;
        Bundle bundle = new Bundle();
        bundle.putSerializable("TEXT_KEY", title);
        navController.navigate(R.id.action_navigation_home_to_formFragment, bundle);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort_menu) {
            binding.recyclerView.setAdapter(adapter);
            adapter.addList(App.appDataBase.taskModelDao().getSortedList());
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}