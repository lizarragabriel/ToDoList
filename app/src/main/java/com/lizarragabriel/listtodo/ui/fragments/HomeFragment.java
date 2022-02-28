package com.lizarragabriel.listtodo.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lizarragabriel.listtodo.R;
import com.lizarragabriel.listtodo.databinding.FragmentHomeBinding;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.sharedpref.SharedPref;
import com.lizarragabriel.listtodo.ui.adapter.TaskAdapter;
import com.lizarragabriel.listtodo.viewmodel.MainViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController mNavController;
    private MainViewModel mMainViewModel;
    private SharedPref mShared;
    AlertDialog.Builder dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShared = new SharedPref(getContext());
        int session = mShared.mGetUserSSession("user");
        if(session == 0) {
            NavController mNavController = Navigation.findNavController(view);
            mNavController.navigate(R.id.action_homeFragment_to_loginFragment);
        }
        mNavController = Navigation.findNavController(view);
        dialog = new AlertDialog.Builder(getContext());
        mToolbar();
        binding.mAddTask.setOnClickListener(mAdd -> {
            NavController mNavController = Navigation.findNavController(mAdd);
            mNavController.navigate(R.id.action_homeFragment_to_addFragment);
        });
        TaskAdapter adapter = new TaskAdapter();
        binding.mRecyclerView.setAdapter(adapter);

        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mMainViewModel.mRecyclerView(session);
        mMainViewModel.mGetTasks().observe(getViewLifecycleOwner(), mList -> {
            adapter.setmList(mList);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMainViewModel.mDeleteNote(adapter.mGetTask(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.mRecyclerView);
    }

    private void mToolbar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.mToolbar);
        binding.mToolbar.setNavigationOnClickListener(mNav -> {
            requireActivity().onBackPressed();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mSignOut){
            mShared.mSetUserSession("user", 0);
            mNavController.navigate(R.id.action_homeFragment_to_loginFragment);
        }
        if(item.getItemId() == R.id.mDeleteAll) {
            mShowDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mShowDialog() {
        dialog
                .setTitle("Delete")
                .setMessage("¿Delete all tasks?")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mMainViewModel.mDeleteAllNotes();
                    }
                });
        dialog.show();
    }
}