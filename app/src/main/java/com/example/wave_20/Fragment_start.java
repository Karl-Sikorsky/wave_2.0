package com.example.wave_20;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

public class Fragment_start extends Fragment {




    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, null);

        final ImageView button_start = (ImageView) v.findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_start.setImageBitmap(BitmapFactory.decodeResource(inflater.getContext().getResources(), R.drawable.start_pressed));
                    EventBus.getDefault().post(new DataEvent(" "));


            }
        });

        return v;
    }
}
