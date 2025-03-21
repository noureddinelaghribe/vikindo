package com.example.aslsignlanguage.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aslsignlanguage.R;


public class DurationFragment extends Fragment {

    OnClickDurationFragmentListener listener;
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private String []list = {"5","10","20","30"};

    public DurationFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_third_blank,container,false);
        radioGroup = v.findViewById(R.id.radioGroup);
        radioButton1 = v.findViewById(R.id.radioButton1);
        radioButton2 = v.findViewById(R.id.radioButton2);
        radioButton3 = v.findViewById(R.id.radioButton3);
        radioButton4 = v.findViewById(R.id.radioButton4);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String choice= "";
                if (checkedId == radioButton1.getId()){
                    choice=list[0];
                } else if (checkedId == radioButton2.getId()) {
                    choice=list[1];
                }else if (checkedId == radioButton3.getId()) {
                    choice=list[2];
                }else if (checkedId == radioButton4.getId()) {
                    choice=list[3];
                }
                listener.onDurationFragmentClick(choice);
            }
        });

        return v;

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickDurationFragmentListener){
            listener = (OnClickDurationFragmentListener) context;
        }else {
            throw new RuntimeException("must implement OnClickFragmentListener");
        }

    }

    public interface OnClickDurationFragmentListener{
        void onDurationFragmentClick(String name);
    }

}