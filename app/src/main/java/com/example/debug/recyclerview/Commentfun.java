package com.example.debug.recyclerview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Commentfun {
    public static void inputComment(final Context context, final ListView listView, final View btnComment, final InputCommentListener listener){
        final int[] coord =new int[2];
        if(listView!=null){
            btnComment.getLocationOnScreen(coord);
        }
        showInputComment(context, new CommentDialogListener() {
            @Override
            public void onClickPublish(Dialog dialog, EditText input, TextView btn) {
                final String content=input.getText().toString();
                if(content.trim().equals("")){
                    Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                btn.setClickable(false);
                dialog.dismiss();
                Toast.makeText(context, "评论成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShow(int[] inputViewCoordinatesOnScreen) {
                if(listView!=null){
                    int span=btnComment.getId() ==R.id.pl? 0 :btnComment.getHeight();
                    listView.smoothScrollBy(coord[1]+span-inputViewCoordinatesOnScreen[1],1000);
                }
            }

            @Override
            public void onDismiss() {

            }
        });
    }
    private static Dialog showInputComment(Context context,final CommentDialogListener listener){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.view_input_comment);
        dialog.findViewById(R.id.input_comment_dialog_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(listener !=null){
                    listener.onDismiss();
                }
            }
        });
        final EditText input = (EditText) dialog.findViewById(R.id.input_comment);
        final TextView btn =(TextView) dialog.findViewById(R.id.btn_publish_comment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener !=null){
                    listener.onClickPublish(dialog,input,btn);
                }
            }
        });
        dialog.setCancelable(true);
        dialog.show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(listener !=null){
                    int[] coord =new int[2];
                    dialog.findViewById(R.id.input_comment_container).getLocationOnScreen(coord);
                    listener.onShow(coord);
                }
            }
        },300);
        return dialog;
    }
    public interface CommentDialogListener{
        void onClickPublish(Dialog dialog, EditText input, TextView btn);
        void onShow(int[] inputViewCoordinatesOnScreen);
        void onDismiss();
    }
    public static class InputCommentListener{
        public void onCommitComment(){

        }
    }
}
