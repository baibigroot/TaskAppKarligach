package com.example.taskappkarligach.ui.board;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskappkarligach.R;
import com.example.taskappkarligach.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private ItemBoardBinding binding;

    private final String [] text = new String[]{
            "Лучшие идеи приходят, в голову неожиданно. Даже если, " ,
                    "вы не планируете писать музыку или книги" , "блокнот позволит не забыть ваши идеи"};

    private final int [] image = new int[]{R.raw.camera,R.raw.protsp,R.raw.walkwalk
    };
    InterfaceListener interfaceListener;

    public void setOnClickListener(InterfaceListener interfaceListener){

        this.interfaceListener = interfaceListener;


    }



    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(ItemBoardBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public  class BoardViewHolder extends RecyclerView.ViewHolder {
        public BoardViewHolder( @NonNull ItemBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(int position) {
            binding.textView.setText(text[position]);
            binding.getIt.setOnClickListener(v -> interfaceListener.OnClick());
           // binding.imgView.setImageResource(image[position]);
             binding.lottie.setAnimation(image[position]);
        }
    }

    public interface InterfaceListener {
        void OnClick();

    }
}