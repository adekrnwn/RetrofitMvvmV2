package com.example.retrofitmvvmv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroViewHolder> {
    private Context mCtx;
    private List<Hero> heroesList;

    public HeroesAdapter(Context mCtx, List<Hero> heroList) {
        this.mCtx = mCtx;
        this.heroesList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_hero_list, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroesList.get(position);

        Glide.with(mCtx)
                .load(hero.getImageurl())
                .into(holder.imageView);

        holder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroesList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView textView;

        public HeroViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hero_image);
            textView = itemView.findViewById(R.id.hero_name);
        }
    }
}
