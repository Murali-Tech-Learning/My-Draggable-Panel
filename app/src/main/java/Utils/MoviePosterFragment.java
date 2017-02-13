package Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Adapters.VideoListingAdapter;
import krish.in.mydraggablepanel.R;
import nonuithreads.VideoListing;

/**
 * Created by divum on 23/1/17.
 */

public class MoviePosterFragment extends Fragment implements View.OnClickListener {

    private String videoPosterThumbnail;
    private String posterTitle;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TextView mDescription;

    /**
     * Override method used to initialize the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);
        initialize(view);
        settingRelatedList();
        Glide.with(getActivity())
                .load(videoPosterThumbnail)
                .placeholder(R.drawable.xmen_placeholder)
                .into(mImageView);
        mDescription.setText(posterTitle +" (Curently Playing)");
        return view;
    }

    private void settingRelatedList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(true);

        List<VideoListing> mBannerList = new ArrayList<VideoListing>();
        VideoListing e1 = new VideoListing();
        e1.setDescription("Related Video");
        e1.setImageurl("http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past-wolverine-poster.jpg");
        e1.setUrl("https://www.youtube.com/watch?v=fhWaJi1Hsfo");
        mBannerList.add(0,e1);
        mBannerList.add(1,e1);
        mBannerList.add(2,e1);
        mBannerList.add(3,e1);
        mBannerList.add(4,e1);
        mBannerList.add(5,e1);
        mBannerList.add(6,e1);
        mBannerList.add(7,e1);
        mBannerList.add(8,e1);
        mBannerList.add(9,e1);
        mBannerList.add(10,e1);
        mBannerList.add(11,e1);


        VideoListingAdapter mAdapter = new VideoListingAdapter(getContext(), mBannerList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initialize(View view) {
        mImageView = (ImageView) view.findViewById(R.id.iv_thumbnail);
        mDescription = (TextView) view.findViewById(R.id.description);
        mImageView.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listing_list_view);
        mRecyclerView.setNestedScrollingEnabled(false);

    }

    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }

    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.iv_thumbnail:
                Toast.makeText(getActivity(), posterTitle, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}