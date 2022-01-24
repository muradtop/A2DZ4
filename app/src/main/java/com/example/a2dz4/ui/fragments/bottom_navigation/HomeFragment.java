package com.example.a2dz4.ui.fragments.bottom_navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a2dz4.R;
import com.example.a2dz4.adapter.AdapterNotes;
import com.example.a2dz4.databinding.FragmentHomeBinding;
import com.example.a2dz4.interfaces.OnItemClickListener;
import com.example.to_dolistproject.data.NoteModel;

import com.example.a2dz4.App;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickListener {

    AdapterNotes adapterNotes;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        initAdapter();


    }

    private void initAdapter() {
        App.getApp().getDb().noteDao().getAllNotes().observe(getViewLifecycleOwner(), taskList -> {
            adapterNotes = new AdapterNotes((List<NoteModel>) taskList, this);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerview.setAdapter(adapterNotes);

        });
    }


    private void initListeners() {
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTaskFragment createTaskFragment = new CreateTaskFragment();
                createTaskFragment.show(requireActivity().getSupportFragmentManager(), "");
            }
        });


    }

    @Override
    public void onItemPress(NoteModel model) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.getApp().getDb().noteDao().delete(model);
                        Toast.makeText(getActivity(), "You have successfully deleted this task!", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(getResources().getDrawable(R.drawable.warning))
                .show();

    }

}