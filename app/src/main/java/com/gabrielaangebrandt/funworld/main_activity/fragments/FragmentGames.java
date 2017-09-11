package com.gabrielaangebrandt.funworld.main_activity.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.memory_activity.view.MemoryActivity;
import com.gabrielaangebrandt.funworld.picado_activity.view.PicadoActivity;
import com.gabrielaangebrandt.funworld.tilt_activity.view.TiltActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gabriela on 16.5.2017..
 */

public class FragmentGames extends Fragment {
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.rl_memory)
    void openADMemory() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle(R.string.gameInstructions).setMessage(R.string.picadoInstructions);
        final AlertDialog alert = dialog.create();
        alert.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                    Intent intent = new Intent(getContext(), MemoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }

    @OnClick(R.id.rl_picado)
    void openADPicado() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle(R.string.gameInstructions).setMessage(R.string.picadoInstructions);
        final AlertDialog alert = dialog.create();
        alert.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                    Intent intent = new Intent(getContext(), PicadoActivity.class);
                    startActivity(intent);
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }

    @OnClick(R.id.rl_rightFlag)
    void openADTilt() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle(R.string.gameInstructions).setMessage(R.string.tiltInstructins);
        final AlertDialog alert = dialog.create();
        alert.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                    Intent intent = new Intent(getContext(), TiltActivity.class);
                    startActivity(intent);
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
