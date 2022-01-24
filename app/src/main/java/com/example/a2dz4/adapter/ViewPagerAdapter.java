package com.example.a2dz4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a2dz4.databinding.FragmentBoardBinding;
import com.example.to_dolistproject.interfaces.OnPagerClickListener;
import com.example.to_dolistproject.model.ViewPagerModel;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder> {
    ArrayList<ViewPagerModel> listPager = new ArrayList<>();
    OnPagerClickListener onPagerClickListener;

    public ViewPagerAdapter(ArrayList<ViewPagerModel> listPager, OnPagerClickListener onPagerClickListener) {
        this.listPager = listPager;
        this.onPagerClickListener = onPagerClickListener;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerHolder(FragmentBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewPagerHolder holder, int position) {
        holder.onBind(listPager.get(position));
    }

    @Override
    public int getItemCount() {
        return listPager.size();
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        private final FragmentBoardBinding binding;

        public ViewPagerHolder(@NonNull FragmentBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(ViewPagerModel model) {
            binding.tvTitle.setText(model.getTitle());
            binding.tvDescription.setText(model.getDescription());
            binding.imImage.setImageResource(model.getImage());
            binding.skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPagerClickListener.onItemClick();

                }
            });
        }
    }
}
