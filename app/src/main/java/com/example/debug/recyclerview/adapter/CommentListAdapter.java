package com.example.debug.recyclerview.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.debug.recyclerview.Commentfun;
import com.example.debug.recyclerview.CustomTagHander;
import com.example.debug.recyclerview.R;
import com.example.debug.recyclerview.bean.Comment;

import java.util.List;

public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> CommentList;
    private CustomTagHander customTagHander;
    private ListView listView;
    public CommentListAdapter(Context context, List<Comment> CommentList,ListView listView) {
        this.context = context;
        this.CommentList = CommentList;
        this.listView=listView;
    }

    @Override
    public int getCount() {
        return CommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.content = convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String contentstr;
        customTagHander =new CustomTagHander(context, new CustomTagHander.OnCommentClickListener() {
            @Override
            public void onCommentatorClick(View view, String comment) {
                Toast.makeText(context,CommentList.get(position).getUserName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceiverClick(View view, String receiver) {
                Toast.makeText(context,CommentList.get(position).getReceiver(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onContentClick(View view, String comment, String receiver) {
               Commentfun.inputComment(context,listView,view,new Commentfun.InputCommentListener(){
                   @Override
                   public void onCommitComment() {
                       CommentListAdapter.this.notifyDataSetChanged();
                   }
               });
            }
        });
        if (CommentList.get(position).getReceiver() == "") {
            contentstr = String.format("<html><%s>%s</%s>: <%s>%s</%s></html>", CustomTagHander
                            .TAG_COMMENTATOR,
                    CommentList.get(position).getUserName(), CustomTagHander.TAG_COMMENTATOR,
                    CustomTagHander.TAG_CONTENT, CommentList.get(position).getContent(),
                    CustomTagHander.TAG_CONTENT);

        } else {
            contentstr = String.format("<html><%s>%s</%s>回复<%s>%s</%s>:<%s>%s</%s></html>", CustomTagHander
                    .TAG_COMMENTATOR, CommentList.get(position).getUserName(), CustomTagHander
                    .TAG_COMMENTATOR, CustomTagHander.TAG_RECEIVER, CommentList.get(position)
                    .getReceiver(), CustomTagHander.TAG_RECEIVER, CustomTagHander.TAG_CONTENT,
                    CommentList.get(position).getContent(), CustomTagHander.TAG_CONTENT);
            Log.e("tag","hzh"+contentstr);
        }
        viewHolder.content.setText(Html.fromHtml(contentstr,null,customTagHander));
                viewHolder.content.setClickable(true);
        viewHolder.content.setMovementMethod(LinkMovementMethod.getInstance());
        //viewHolder.content.setTag(CustomTagHander.KEY_COMMENTATOR, CommentList.get(position).getUserName());
        //viewHolder.content.setTag(CustomTagHander.KEY_RECEIVER,CommentList.get(position).getReceiver());
        //viewHolder.content.setTag(KEY_COMMENT_SOURCE_COMMENT_LIST, mCommentList);
        return convertView;
    }

    private class ViewHolder {
        TextView content;
    }
}
