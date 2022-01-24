package com.example.a2dz4.ui.fragments.bottom_navigation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a2dz4.App;

import com.example.a2dz4.R;
import com.example.a2dz4.databinding.FragmentCreateTaskBinding;
import com.example.to_dolistproject.data.NoteModel;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;


public class CreateTaskFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    private FragmentCreateTaskBinding binding;

    private int startYear;
    private int startMonth;
    private int startDay;
    private String date;
    private String frequency;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        binding.btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDatabase();
                dismiss();
            }
        });
        binding.tvDateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        binding.tvFrequencyChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooseFrequencyDialog();

            }
        });
    }

    private void sendToDatabase() {
        String text = binding.etTask.getText().toString();
        NoteModel noteModel = new NoteModel(text, date, frequency);
        App.getApp().getDb().noteDao().insert(noteModel);

    }


    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                this, startYear, startMonth, startDay);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
        date = day + "/" + month + 1 + "/" + year;
        binding.tvDateChoose.setText(date);

    }

    public void showChooseFrequencyDialog() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        Dialog alertDialog = new Dialog(requireContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.repeat_dialog);
        alertDialog.show();
        RadioButton never = alertDialog.findViewById(R.id.radioBtn_never);
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String never = "Never";
                binding.tvFrequencyChoose.setText(never);
                frequency = never;
                alertDialog.dismiss();
            }
        });
        RadioButton daily = alertDialog.findViewById(R.id.radioBtn_daily);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyDay = "Every day";
                binding.tvFrequencyChoose.setText(everyDay);
                frequency = everyDay;
                alertDialog.dismiss();
            }
        });
        RadioButton weekly = alertDialog.findViewById(R.id.radioBtn_weekly);
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyWeek = "Every week";
                binding.tvFrequencyChoose.setText(everyWeek);
                frequency = everyWeek;
                alertDialog.dismiss();
            }
        });
        RadioButton monthly = alertDialog.findViewById(R.id.radioBtn_monthly);
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyMonth = "Every month";
                binding.tvFrequencyChoose.setText(everyMonth);
                frequency = everyMonth;
                alertDialog.dismiss();
            }
        });
        RadioButton yearly = alertDialog.findViewById(R.id.radioBtn_yearly);
        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyYear = "Every year";
                binding.tvFrequencyChoose.setText(everyYear);
                frequency = everyYear;
                alertDialog.dismiss();
            }
        });


    }


}
