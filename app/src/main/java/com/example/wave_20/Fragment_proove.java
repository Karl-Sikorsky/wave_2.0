package com.example.wave_20;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ПОДАРУНКОВИЙ on 12.03.2017.
 */
public class Fragment_proove extends Fragment {
    EventBus myEventBus;
    TextView prooveText;
    

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_proove, null);
        Typeface keys = Typeface.createFromAsset(inflater.getContext().getAssets(),   getString(R.string.digit_keyboard_font));

        myEventBus = EventBus.getDefault();
        EventBus.getDefault().register(this);

        prooveText = (TextView) v.findViewById(R.id.textViewProove) ;
        prooveText.setTypeface(keys);
        EventBus.getDefault().post(new DataEvent("send me total"));
         final ImageView button_next = (ImageView) v.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_next.setImageBitmap(BitmapFactory.decodeResource(inflater.getContext().getResources(), R.drawable.next_pressed));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                DatabaseReference dataRef = myRef.child("new what");

                myRef.setValue(prooveText.getText().toString(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Log.d("dbTAG","onComplete");
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());
                            prooveText.setText("Замовлення не прийнято. \n Перевірте з'єднання з інтернетом ");
                        } else {
                            System.out.println("Data saved successfully.");
                            prooveText.setText("Замовлення прийнято. Очікуйте ");
                        }
                    }
                });
                prooveText.setText("Замовлення не прийнято. \n Перевірте з'єднання з інтернетом ");


                button_next.setVisibility(View.GONE);
            }
        });


        return v;
    }
    @Subscribe
    public void onGlobalEvent(TotalDataEvent event){
        Log.d("eventbus",event.message + " received");
        String res = event.message;
        res = res.substring(1, res.length() - 1);
        prooveText.setText(prooveText.getText()+" "+res);

    }
    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

