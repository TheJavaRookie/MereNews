package androidnews.kiloproject.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidnews.kiloproject.R;
import androidnews.kiloproject.entity.net.VideoListData;
import androidnews.kiloproject.util.GlideUtils;
import androidnews.kiloproject.widget.MyJzvdStd;
import cn.jzvd.Jzvd;
import de.hdodenhof.circleimageview.CircleImageView;

public class VideoRvAdapter extends BaseQuickAdapter<VideoListData, BaseViewHolder> {
    Context mContext;
    RequestOptions options;
//    ReceiverGroup receiverGroup;

    public VideoRvAdapter(Context context, List data) {
        super(R.layout.list_item_card_video, data);
        this.mContext = context;
        options = new RequestOptions();
        options.centerCrop()
                .error(R.drawable.ic_error);
//        receiverGroup = new ReceiverGroup();
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListData item) {
        helper.setText(R.id.item_card_text, item.getTitle().replace("&quot", "\""));
        helper.setText(R.id.item_card_times, item.getPlayCount() + "次播放");
        if (item.isReaded())
            helper.setTextColor(R.id.item_card_text,
                    mContext.getResources().getColor(R.color.main_text_color_read));
        else
            helper.setTextColor(R.id.item_card_text,
                    mContext.getResources().getColor(R.color.main_text_color_dark));
        if (!TextUtils.isEmpty(item.getTopicImg()) && GlideUtils.isValidContextForGlide(mContext))
                Glide.with(mContext).load(item.getTopicImg())
                        .apply(options)
                        .into((CircleImageView) helper.getView(R.id.item_card_img));

        MyJzvdStd videoView = helper.getView(R.id.item_card_vid);
        videoView.setUp(item.getMp4_url(), item.getSectiontitle(), Jzvd.SCREEN_WINDOW_LIST);
        if (!TextUtils.isEmpty(item.getCover()) && GlideUtils.isValidContextForGlide(mContext))
                Glide.with(mContext)
                        .load(item.getCover())
                        .apply(options)
                        .into(videoView.thumbImageView);
    }
}
