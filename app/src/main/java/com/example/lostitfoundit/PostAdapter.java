package com.example.lostitfoundit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private Context mContext;
    private int mResource;
    private List<Post> mPosts;

    public PostAdapter(Context context, int resource, List<Post> posts) {
        super(context, resource, posts);
        mContext = context;
        mResource = resource;
        mPosts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        // Get the Post object for the current position
        Post post = mPosts.get(position);

        // Bind data to the views in the layout
        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        itemNameTextView.setText(post.itemName);

        TextView itemDescTextView = convertView.findViewById(R.id.itemDescTextView);
        itemDescTextView.setText(post.itemDescription);

        TextView reportDateTextView = convertView.findViewById(R.id.reportDateTextView);
        reportDateTextView.setText(post.reportedDate);

        return convertView;
    }
}
