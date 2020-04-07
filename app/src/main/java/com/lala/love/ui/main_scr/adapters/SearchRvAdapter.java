package com.lala.love.ui.main_scr.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lala.love.databinding.SearchRvItemBinding;
import com.lala.love.entities.SearchItem;

import java.util.List;

public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.SearchHolder> {

    private List<SearchItem> items;
    private SearchRvAdapter.Interactor interactor;

    public SearchRvAdapter(List<SearchItem> items, Interactor interactor) {
        this.items = items;
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchRvItemBinding binding = SearchRvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchRvAdapter.SearchHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.bind(items.get(position), interactor);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class SearchHolder extends RecyclerView.ViewHolder {

        private SearchRvItemBinding binding;

        public SearchHolder(@NonNull SearchRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SearchItem item, SearchRvAdapter.Interactor interactor) {

            binding.getRoot().setOnClickListener(container -> {
                interactor.interact(getAdapterPosition());
            });

            binding.iconImg.setImageResource(item.getImg());
            binding.photoText.setText(item.getTitle());
            binding.name.setText(item.getName() +", "+item.getAge() + " - "+ item.getZodiacSign());
            binding.place.setText(item.getCity());
            binding.date.setText(item.getRegDate());
        }
    }

    public interface Interactor {
        void interact(int id);
    }
}
