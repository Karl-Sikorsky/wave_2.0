package com.example.wave_20;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;



import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ПОДАРУНКОВИЙ on 12.03.2017.
 */
public class Fragment_proove extends Fragment {
    EventBus myEventBus;
    TextView prooveText;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_proove, null);

        myEventBus = EventBus.getDefault();
        EventBus.getDefault().register(this);

        prooveText = (TextView) v.findViewById(R.id.textViewProove) ;
        EventBus.getDefault().post(new DataEvent("send me total"));
        ImageButton button_next = (ImageButton) v.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
/*
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");*/
            }
        });


        return v;
    }
    @Subscribe
    public void onGlobalEvent(TotalDataEvent event){
        Log.d("eventbus",event.message + " received");
        prooveText.setText(prooveText.getText()+" "+event.message);

    }
    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

