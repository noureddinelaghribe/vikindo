package com.example.aslsignlanguage.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aslsignlanguage.R;
import com.example.aslsignlanguage.fragments.ReasonFragment;
import com.example.aslsignlanguage.Controlar.ViewPagerAdapter;
import com.example.aslsignlanguage.fragments.DurationFragment;
import com.example.aslsignlanguage.fragments.FirstFragment;
import com.example.aslsignlanguage.fragments.FourthFragment;

import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends AppCompatActivity implements
        ReasonFragment.OnClickReasonFragmentListener, DurationFragment.OnClickDurationFragmentListener{

    ViewPager2 viewPager2;
    List<Fragment> fragmentList = new ArrayList<>();
    ViewPagerAdapter adapter;
    Button buttonLeft,buttonRight;
    ImageView imageView1,imageView2,imageView3,imageView4;

    String reason = null , duration = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slide);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2 = findViewById(R.id.viewPager2);
        buttonLeft = findViewById(R.id.button);
        buttonRight = findViewById(R.id.button2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        fragmentList.add(new FirstFragment());
        fragmentList.add(new ReasonFragment());
        fragmentList.add(new DurationFragment());
        fragmentList.add(new FourthFragment());

        adapter = new ViewPagerAdapter(this,fragmentList);
        viewPager2.setAdapter(adapter);

        viewPager2.setUserInputEnabled(false);

        view();

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager2.getCurrentItem();
                if (currentItem>0){
                    viewPager2.setCurrentItem(currentItem-1);
                }
                view();
            }
        });


        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager2.getCurrentItem();

                if (viewPager2.getCurrentItem()==1 && buttonRight.hasOnClickListeners()){
                    if (reason == null){
                        Toast.makeText(SlideActivity.this, "Please select a reason ", Toast.LENGTH_SHORT).show();
                    }else {
                        if (currentItem<fragmentList.size()){
                            viewPager2.setCurrentItem(currentItem+1);
                        }
                    }
                }else if (viewPager2.getCurrentItem()==2 && buttonRight.hasOnClickListeners()){
                    if (duration == null){
                        Toast.makeText(SlideActivity.this, "Please select a duration ", Toast.LENGTH_SHORT).show();
                        buttonRight.setClickable(false);
                    }else {
                        if (currentItem<fragmentList.size()){
                            viewPager2.setCurrentItem(currentItem+1);
                        }
                    }
                }else {
                    if (currentItem<fragmentList.size()){
                        viewPager2.setCurrentItem(currentItem+1);
                    }
                }


                if (buttonRight.getText().equals("DONE")){
                    Intent intent = new Intent(SlideActivity.this, RegisterActivity.class);
                    intent.putExtra("reason",reason);
                    intent.putExtra("duration",duration);
                    startActivity(intent);
                }

                view();
            }
        });



    }


    public void view(){

        if (viewPager2.getCurrentItem()==0){
            buttonLeft.setVisibility(View.INVISIBLE);
        }else {
            buttonLeft.setVisibility(View.VISIBLE);
        }

        if (viewPager2.getCurrentItem()==fragmentList.size()-1){
            buttonRight.setText("DONE");
        }else {
            buttonRight.setText("NEXT");
        }

        switch (viewPager2.getCurrentItem()){
            case 0:
                imageView1.setBackgroundResource(R.drawable.dot_select);
                imageView2.setBackgroundResource(R.drawable.dot_unselecte);
                imageView3.setBackgroundResource(R.drawable.dot_unselecte);
                imageView4.setBackgroundResource(R.drawable.dot_unselecte);
                break;
            case 1:
                imageView1.setBackgroundResource(R.drawable.dot_unselecte);
                imageView2.setBackgroundResource(R.drawable.dot_select);
                imageView3.setBackgroundResource(R.drawable.dot_unselecte);
                imageView4.setBackgroundResource(R.drawable.dot_unselecte);
                break;
            case 2:
                imageView1.setBackgroundResource(R.drawable.dot_unselecte);
                imageView2.setBackgroundResource(R.drawable.dot_unselecte);
                imageView3.setBackgroundResource(R.drawable.dot_select);
                imageView4.setBackgroundResource(R.drawable.dot_unselecte);
                break;
            case 3:
                imageView1.setBackgroundResource(R.drawable.dot_unselecte);
                imageView2.setBackgroundResource(R.drawable.dot_unselecte);
                imageView3.setBackgroundResource(R.drawable.dot_unselecte);
                imageView4.setBackgroundResource(R.drawable.dot_select);
                break;
        }

    }


    @Override
    public void onReasonFragmentClick(String name) {
        reason=name;
    }

    @Override
    public void onDurationFragmentClick(String name) {
        duration=name;
    }
}