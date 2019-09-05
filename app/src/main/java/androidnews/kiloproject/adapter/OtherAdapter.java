package androidnews.kiloproject.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import androidnews.kiloproject.R;
import androidnews.kiloproject.entity.data.ChannelItem;

public class OtherAdapter extends BaseAdapter {
    private Context context;
    public List<ChannelItem> channelList;
    private TextView item_text;
    /**
     * 是否可见
     */
    boolean isVisible = true;
    /**
     * 要删除的position
     */
    public int remove_position = -1;

    public OtherAdapter(Context context, List<ChannelItem> channelList) {
        this.context = context;
        this.channelList = channelList;
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public ChannelItem getItem(int position) {
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.channel_category_item, null);
        ChannelItem channel = getItem(position);
        if (TextUtils.equals(channel.getName(), "fake")) {
            view.setVisibility(View.GONE);
            return view;
        }
        item_text = (TextView) view.findViewById(R.id.text_item);
        item_text.setText(channel.getName());
        if (!isVisible && (position == -1 + channelList.size())) {
            item_text.setText("");
            item_text.setVisibility(View.GONE);//在动画没有结束时，设置不可见
        }
        if (remove_position == position) {
            item_text.setText("");
            item_text.setVisibility(View.GONE);
        }
        return view;
    }

    /**
     * 获取频道列表
     */
    public List<ChannelItem> getChannnelLst() {
        return channelList;
    }

    /**
     * 添加频道列表
     */
    public void addItem(ChannelItem channel) {
        channelList.add(getLastVaildPositon(), channel);
        notifyDataSetChanged();
    }

    public int getLastVaildPositon() {
        int lastVaild = -1;
        for (int i = 0; i < channelList.size(); i++) {
            if (channelList.get(i).getName().equals("fake")) {
                lastVaild = i;
                break;
            }
        }
        if (lastVaild < 0)lastVaild = 0;
        return lastVaild;
    }

    /**
     * 设置删除的position
     */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }

    /**
     * 删除频道列表
     */
    public void remove() {
        if (remove_position == -1) return;
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

    /**
     * 设置频道列表
     */
    public void setListDate(List<ChannelItem> list) {
        channelList = list;
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}