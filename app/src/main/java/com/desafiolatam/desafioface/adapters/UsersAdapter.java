package com.desafiolatam.desafioface.adapters;

import android.content.Context;
import android.hardware.Camera;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiolatam.desafioface.R;
import com.desafiolatam.desafioface.data.UserData;
import com.desafiolatam.desafioface.models.User;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by QA on 24-11-2016.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<User> userList = new UserData().all();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);
        Context context = holder.photo.getContext();
        Picasso.with(context).load(user.getPhoto_url()).into(holder.photo);
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView name;
        ImageButton twitter,github,mail,share;
        LikeButton favorite;
        //Same that above, create a subclass which you will use to access your views
        //But this time, the inflating is above and the finding is here

        ViewHolder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.developerIv);
            name = (TextView) view.findViewById(R.id.developerName);
            twitter = (ImageButton) view.findViewById(R.id.twitterBtn);
            github = (ImageButton) view.findViewById(R.id.githubBtn);
            mail = (ImageButton) view.findViewById(R.id.mailBtn);
            share = (ImageButton) view.findViewById(R.id.shareBtn);
        }
    }
}
