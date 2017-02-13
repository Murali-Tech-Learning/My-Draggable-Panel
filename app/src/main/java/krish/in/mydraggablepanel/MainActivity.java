package krish.in.mydraggablepanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Adapters.VideoListingAdapter;
import Utils.DraggableListener;
import Utils.DraggablePanel;
import Utils.MoviePosterFragment;
import nonuithreads.VideoListing;

import static Utils.Constants.YOUTUBE_API_KEY;

public class MainActivity extends AppCompatActivity {

    private DraggablePanel draggablePanel;
    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        settingList();
    }

    private void settingList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(true);

        List<VideoListing> mBannerList = new ArrayList<VideoListing>();
        VideoListing e1 = new VideoListing();
        e1.setDescription("Sample Video");
        e1.setImageurl("http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/xmenDOFP.wobbly.1.jpg");
        e1.setUrl("https://www.youtube.com/watch?v=fhWaJi1Hsfo");
        mBannerList.add(0, e1);
        mBannerList.add(1, e1);
        mBannerList.add(2, e1);
        mBannerList.add(3, e1);
        mBannerList.add(4, e1);
        mBannerList.add(5, e1);
        mBannerList.add(6, e1);
        mBannerList.add(7, e1);
        mBannerList.add(8, e1);
        mBannerList.add(9, e1);
        mBannerList.add(10, e1);
        mBannerList.add(11, e1);

        VideoListingAdapter mAdapter = new VideoListingAdapter(this, mBannerList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initialize() {
        draggablePanel = (DraggablePanel) findViewById(R.id.draggable_panel);
        mRecyclerView = (RecyclerView) findViewById(R.id.listing_list_view);
        mRecyclerView.setNestedScrollingEnabled(false);

    }


    private void initializeYoutubeFragment(final String url) {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;

                    youtubePlayer.cueVideo(extractYTId(url));
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult error) {
            }
        });
    }

    public static String extractYTId(String ytUrl) {
        String vId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);

        if (matcher.find()) {
            vId = matcher.group();
        }
        return vId;
    }


    private void initializeDraggablePanel(String imageurl, String description) {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment.setPoster(imageurl);
        moviePosterFragment.setPosterTitle(description);
        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();
    }

    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                playVideo();
            }

            @Override
            public void onMinimized() {
                //Empty
            }

            @Override
            public void onClosedToLeft() {
                pauseVideo();
            }

            @Override
            public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    private void pauseVideo() {
        if (youtubePlayer.isPlaying()) {
            youtubePlayer.pause();
        }
    }

    private void playVideo() {
        if (!youtubePlayer.isPlaying()) {
            youtubePlayer.play();
        }
    }


    public void onVideoListingClick(VideoListing videoListing) {

        draggablePanel.removeAllViews();
        videoListing.getImageurl();
        videoListing.getDescription();
        videoListing.getUrl();


        initializeYoutubeFragment(videoListing.getUrl());
        initializeDraggablePanel(videoListing.getImageurl(), videoListing.getDescription());
        hookDraggablePanelListeners();

    }
}
