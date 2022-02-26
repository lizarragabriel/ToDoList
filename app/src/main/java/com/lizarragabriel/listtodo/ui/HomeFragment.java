package com.lizarragabriel.listtodo.ui;

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
    private List<TaskEntity> lista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        System.out.println("SOY EL " + session);
        mNavController = Navigation.findNavController(view);
        dialog = new AlertDialog.Builder(getContext());


        setHasOptionsMenu(true);
        mToolbar();


        binding.mAddTask.setOnClickListener(mAdd -> {
            NavController mNavController = Navigation.findNavController(mAdd);
            mNavController.navigate(R.id.action_homeFragment_to_addFragment);
        });



        TaskAdapter adapter = new TaskAdapter();
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        //mMainViewModel.init(getContext());
        mMainViewModel.mRecyclerView(session);
        mMainViewModel.mGetTasks().observe(getViewLifecycleOwner(), mList -> {
            binding.mRecyclerView.setAdapter(adapter);
            adapter.setmList(mList);

            lista = mList;
            if(mList.size() > 0) {
                for(TaskEntity task : mList) {
                    System.out.println(task.toString());
                }
            } else {
                System.out.println("es 0");
            }
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
                Toast.makeText(getContext(), "Task deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.mRecyclerView);
    }

    private void mToolbar() {
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
                .setTitle("Eliminar")
                .setMessage("¿Eliminara todas las notas?")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mMainViewModel.mDeleteAllNotes();
                    }
                });

        dialog.show();
    }
}