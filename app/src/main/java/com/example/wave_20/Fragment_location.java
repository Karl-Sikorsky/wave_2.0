package com.example.wave_20;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ПОДАРУНКОВИЙ on 12.03.2017.
 */
public class Fragment_location extends Fragment {

    EditText nameEdit;
    EditText telephonEdit;
    EditText cityEdit;
    EditText adressEdit;
    RadioButton rB;

    int hour;
    int day;
    int month;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, null);
        ImageButton button_next = (ImageButton) v.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new DataEvent("\nТелефон: "+telephonEdit.getText()+"\nАдреса: "+adressEdit.getText()+"\nБажаний час: "
                        +String.valueOf(hour)+":00;"+String.valueOf(day)+"."+String.valueOf(month)));
            }
        });


        nameEdit = (EditText)v.findViewById(R.id.editName);
        telephonEdit = (EditText) v.findViewById(R.id.editPhone);
        cityEdit = (EditText) v.findViewById(R.id.editCity);
        adressEdit = (EditText) v.findViewById(R.id.editAdres);

        rB = (RadioButton)v.findViewById(R.id.radioButton2);
        rB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                Log.d("dialog","checked rB");
                PickerDialog pd = PickerDialog.newInstance(5,12, 8);
                new PickerDialog();

                pd.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        hour = selectedYear;
                        day = selectedDay;
                        month = selectedMonth;
                        Toast.makeText(getActivity(),"hour : " + selectedYear + " Month :" + selectedMonth + " Day:" + selectedDay,
                                Toast.LENGTH_LONG ).show();


                    }
                });
                pd.show(getFragmentManager(), "MonthYearPickerDialog");}
            }
        });
















        return v;
    }
}

