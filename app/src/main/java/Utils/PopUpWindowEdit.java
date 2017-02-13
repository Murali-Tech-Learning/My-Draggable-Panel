package Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import krish.in.mydraggablepanel.R;
import nonuithreads.VideoListing;

/**
 * Created by divum on 24/1/17.
 */

public class PopUpWindowEdit extends android.widget.PopupWindow {

    private Context ctx;
    private int mPosition;


    public PopUpWindowEdit(final Context context, final List<VideoListing> mBannerList, int position) {
        super(context);
        ctx = context;
        mPosition = position;

        View popupView = LayoutInflater.from(context).inflate(R.layout.custom_xml_spinner_layout, null);

        TextView mShare = (TextView) popupView.findViewById(R.id.standard_spinner_format);

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mBannerList.get(mPosition).getUrl());
                sendIntent.setType("text/plain");
                ctx.startActivity(sendIntent);
            }
        });


        setContentView(popupView);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());


    }

    public void show(View anchor, int x, int y) {
        showAsDropDown(anchor, 50, -70);
    }


}
