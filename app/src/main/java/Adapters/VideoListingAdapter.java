package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import Utils.PopUpWindowEdit;
import krish.in.mydraggablepanel.MainActivity;
import krish.in.mydraggablepanel.R;
import nonuithreads.VideoListing;

/**
 * Created by divum on 23/1/17.
 */

public class VideoListingAdapter extends RecyclerView.Adapter<VideoListingAdapter.ViewHolder> {

    Context mContext;
    List<VideoListing> mBannerList;

    public VideoListingAdapter(Context mContext, List<VideoListing> mBannerList) {
        this.mContext = mContext;
        this.mBannerList = mBannerList;
    }

    @Override
    public VideoListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(mContext)
                .inflate(R.layout.banner_listing_item, parent, false);
        ViewHolder holder = new ViewHolder(itemLayoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoListingAdapter.ViewHolder holder, final int position) {
        if (mBannerList.get(position).getImageurl() != null) {
            Glide.with(mContext)
                    .load(mBannerList.get(position).getImageurl()).placeholder(R.drawable.xmen_placeholder)
                    .into(holder.mImage);
        } else {
            holder.mImage.setImageResource(R.drawable.xmen_placeholder);
        }

        holder.mDescription.setText(mBannerList.get(position).getDescription());

        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).onVideoListingClick(mBannerList.get(position));
            }
        });


        final ImageView showMore = holder.mMore;

        holder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpWindowEdit popupWindow = new PopUpWindowEdit(mContext,mBannerList,position);
                popupWindow.show(showMore, 0, -240);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBannerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mMore;
        TextView mDescription;
        ImageView mImage;
        LinearLayout mParentLayout;


        public ViewHolder(View view) {
            super(view);
            mImage = (ImageView) view.findViewById(R.id.image);
            mParentLayout = (LinearLayout) view.findViewById(R.id.parent_layout);
            mDescription = (TextView) view.findViewById(R.id.description);
            mMore = (ImageView) view.findViewById(R.id.more);
        }
    }

}

