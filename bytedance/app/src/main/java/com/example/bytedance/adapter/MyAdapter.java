package com.example.bytedance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bytedance.Info.JsonLoad;
import com.example.bytedance.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<JsonLoad> mListString;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    final int TYPE_5 = 4;

    public MyAdapter(Context context,List<JsonLoad> listString) {
        // TODO Auto-generated constructor stub
        mContext = context;
        mListString = listString;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mListString.size();
    }

    //每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        int p = position % 5;
        if (p == 0)
            return TYPE_1;
        else if (p==1)
            return TYPE_2;
        else if (p==2)
            return TYPE_3;
        else if (p==3)
            return TYPE_4;
        else
            return TYPE_5;
    }

    //返回三个不同的布局
    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mListString.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //创建正确的contentview复用
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        viewHolder2 holder3 = null;
        viewHolder2 holder4 = null;
        viewHolder3 holder5 = null;
        int type = getItemViewType(position);

        //无convertView，需要new出各个控件
        if (convertView == null) {
            //按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.listitem1, parent, false);
                    holder1 = new viewHolder1();
                    holder1.textView_title = (TextView) convertView.findViewById(R.id.title2);
                    holder1.textView_author = (TextView) convertView.findViewById(R.id.author2);
                    holder1.textView_time = (TextView) convertView.findViewById(R.id.time2);
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.listitem2, parent, false);
                    holder2 = new viewHolder2();
                    holder2.textView_title = (TextView) convertView.findViewById(R.id.title1);
                    holder2.textView_author = (TextView) convertView.findViewById(R.id.author1);
                    holder2.textView_time = (TextView) convertView.findViewById(R.id.time1);
                    holder2.imageView = (ImageView) convertView.findViewById(R.id.image1);
                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.listitem3, parent, false);
                    holder3 = new viewHolder2();
                    holder3.textView_title = (TextView) convertView.findViewById(R.id.title3);
                    holder3.textView_author = (TextView) convertView.findViewById(R.id.author3);
                    holder3.textView_time = (TextView) convertView.findViewById(R.id.time3);
                    holder3.imageView = (ImageView) convertView.findViewById(R.id.image3);
                    convertView.setTag(holder3);
                    break;
                case TYPE_4:
                    convertView = inflater.inflate(R.layout.listitem4, parent, false);
                    holder4 = new viewHolder2();
                    holder4.textView_title = (TextView) convertView.findViewById(R.id.title4);
                    holder4.textView_author = (TextView) convertView.findViewById(R.id.author4);
                    holder4.textView_time = (TextView) convertView.findViewById(R.id.time4);
                    holder4.imageView = (ImageView) convertView.findViewById(R.id.image4);
                    convertView.setTag(holder4);
                    break;
                case TYPE_5:
                    convertView = inflater.inflate(R.layout.listitem5, parent, false);
                    holder5 = new viewHolder3();
                    holder5.textView_title = (TextView) convertView.findViewById(R.id.title5);
                    holder5.textView_author = (TextView) convertView.findViewById(R.id.author5);
                    holder5.textView_time = (TextView) convertView.findViewById(R.id.time5);
                    holder5.imageView1 = (ImageView) convertView.findViewById(R.id.image51);
                    holder5.imageView2 = (ImageView) convertView.findViewById(R.id.image52);
                    holder5.imageView3 = (ImageView) convertView.findViewById(R.id.image53);
                    holder5.imageView4 = (ImageView) convertView.findViewById(R.id.image54);
                    convertView.setTag(holder5);
                    break;
            }
        } else {
            //有convertView，按样式，取得不用的布局
            switch (type) {
                case TYPE_1:
                    holder1 = (viewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (viewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (viewHolder2) convertView.getTag();
                    break;
                case TYPE_4:
                    holder4 = (viewHolder2) convertView.getTag();
                    break;
                case TYPE_5:
                    holder5 = (viewHolder3) convertView.getTag();
                    break;
            }
        }

            //设置资源
            switch (type) {
                case TYPE_1:
                    //设置文本内容以及图片等
                    holder1.textView_title.setText(mListString.get(TYPE_1).getTitle());
                    holder1.textView_author.setText(mListString.get(TYPE_1).getAuthor());
                    holder1.textView_time.setText(mListString.get(TYPE_1).getPublishTime());
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    holder2.textView_title.setText(mListString.get(TYPE_2).getTitle());
                    holder2.textView_author.setText(mListString.get(TYPE_2).getAuthor());
                    holder2.textView_time.setText(mListString.get(TYPE_2).getPublishTime());
                    // Picasso.get().load(mListString.get(TYPE_2).getCover()).into(holder2.imageView);
                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    holder3.textView_title.setText(mListString.get(TYPE_3).getTitle());
                    holder3.textView_author.setText(mListString.get(TYPE_3).getAuthor());
                    holder3.textView_time.setText(mListString.get(TYPE_3).getPublishTime());
//                    Picasso.get().load(mListString.get(TYPE_3).getCover()).into(holder3.imageView);
                    convertView.setTag(holder3);
                    break;
                case TYPE_4:
                    holder4.textView_title.setText(mListString.get(TYPE_4).getTitle());
                    holder4.textView_author.setText(mListString.get(TYPE_4).getAuthor());
                    holder4.textView_time.setText(mListString.get(TYPE_4).getPublishTime());
//                    Picasso.get().load(mListString.get(TYPE_4).getCover()).into(holder4.imageView);
                    convertView.setTag(holder4);
                    break;
                case TYPE_5:
                    holder5.textView_title.setText(mListString.get(TYPE_5).getTitle());
                    holder5.textView_author.setText(mListString.get(TYPE_5).getAuthor());
                    holder5.textView_time.setText(mListString.get(TYPE_5).getPublishTime());
//                    Picasso.get().load(mListString.get(TYPE_5).getCover()).into(holder5.imageView1);
//                    Picasso.get().load(mListString.get(TYPE_5).getCover()).into(holder5.imageView2);
//                    Picasso.get().load(mListString.get(TYPE_5).getCover()).into(holder5.imageView3);
//                    Picasso.get().load(mListString.get(TYPE_5).getCover()).into(holder5.imageView4);
                    convertView.setTag(holder5);
                    break;
            }

        return convertView;
    }
    //各个布局的控件资源
    class viewHolder1 {
        TextView textView_title;
        TextView textView_author;
        TextView textView_time;
    }

    class viewHolder2 {
        TextView textView;
        TextView textView_title;
        TextView textView_author;
        TextView textView_time;
        ImageView imageView;
    }
    class viewHolder3 {
        TextView textView;
        TextView textView_title;
        TextView textView_author;
        TextView textView_time;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
    }
}
