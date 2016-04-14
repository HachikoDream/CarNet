package com.wells.carnet.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Wells on 2016/4/12.
 */
public class AlerDialogUtils {
    public interface DialogBtnClickListener {
        void onClick(boolean isRight);
    }

    public static void showDialog(Context context, String title, String msg, @Nullable String left, String right, final DialogBtnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(right, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onClick(true);
                        }
                        dialog.dismiss();

                    }
                });
        if (left == null) {
            builder.setNegativeButton(left, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onClick(false);
                    }
                    dialog.dismiss();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void showCustomDialog(Context context, View v, String title, @Nullable String left, String right, final DialogBtnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(v, 10, 10, 10, 10)
                .setPositiveButton(right, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onClick(true);
                        }
                        dialog.dismiss();

                    }
                });

        if (left == null) {
            builder.setNegativeButton(left, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onClick(false);
                    }
                    dialog.dismiss();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
