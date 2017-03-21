package com.example.wave_20;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ПОДАРУНКОВИЙ on 12.03.2017.
 */
public class Fragment_special extends Fragment {
    EditText editSpecial;
    TextView description_special;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_special, null);
        Typeface keys = Typeface.createFromAsset(inflater.getContext().getAssets(),   getString(R.string.digit_keyboard_font));
        editSpecial = (EditText)v.findViewById(R.id.editSpecial);
        editSpecial.setTypeface(keys);

        description_special = (TextView)v.findViewById(R.id.textView5);
        description_special.setTypeface(keys);

        final ImageView button_next = (ImageView) v.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_next.setImageBitmap(BitmapFactory.decodeResource(inflater.getContext().getResources(), R.drawable.next_pressed));
                EventBus.getDefault().post(new DataEvent("\nСпеціальні побажання: "+editSpecial.getText()));
            }
        });

        return v;
    }
}

