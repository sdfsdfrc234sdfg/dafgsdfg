package com.lala.love.ui.main_scr.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lala.love.databinding.ArticlesRvItemBinding;
import com.lala.love.entities.ArticlesItem;

import java.util.List;

public class ArticlesRvAdapter extends RecyclerView.Adapter<ArticlesRvAdapter.ArticleHolder> {

    List<ArticlesItem> items;
    private ArticlesRvAdapter.Interactor interactor;

    public ArticlesRvAdapter(List<ArticlesItem> items, ArticlesRvAdapter.Interactor interactor) {
        this.items = items;
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticlesRvItemBinding binding = ArticlesRvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArticleHolder(binding, interactor);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ArticleHolder extends RecyclerView.ViewHolder {

        private ArticlesRvItemBinding binding;
        private ArticlesRvAdapter.Interactor interactor;

        public ArticleHolder(@NonNull ArticlesRvItemBinding binding, ArticlesRvAdapter.Interactor interactor) {
            super(binding.getRoot());
            this.binding = binding;
            this.interactor = interactor;
        }

        public void bind(ArticlesItem item) {

            binding.getRoot().setOnClickListener(container-> {
                interactor.interact(getAdapterPosition());
            });

            binding.articleImg.setImageResource(item.getImage());
            binding.titleText.setText(item.getTitle());
            binding.text.setText(item.getText1());
        }
    }

    public interface Interactor {
        void interact(int id);
    }
}
