package com.example.aslsignlanguage.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.aslsignlanguage.R;

import java.util.ArrayList;
import java.util.List;

import com.example.aslsignlanguage.Controlar.ImageMerger;

public class ChatBotActivity extends AppCompatActivity {

    ImageButton a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    Button blank,send,delete;
    ImageView messege;
    ImageView home,quiz,reels,subsecribe,profile;

    List<Integer> imageList = new ArrayList<>();

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_bot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        f = findViewById(R.id.f);
        g = findViewById(R.id.g);
        h = findViewById(R.id.h);
        i = findViewById(R.id.i);
        j = findViewById(R.id.j);
        k = findViewById(R.id.k);
        l = findViewById(R.id.l);
        m = findViewById(R.id.m);
        n = findViewById(R.id.n);
        o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        q = findViewById(R.id.q);
        r = findViewById(R.id.r);
        s = findViewById(R.id.s);
        t = findViewById(R.id.t);
        u = findViewById(R.id.u);
        v = findViewById(R.id.v);
        w = findViewById(R.id.w);
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);

        blank = findViewById(R.id.blank);
        messege = findViewById(R.id.imageViewMessege);
        send = findViewById(R.id.Send);
        delete = findViewById(R.id.Delet);

        home = findViewById(R.id.imageButton1);
        quiz = findViewById(R.id.imageButton2);
        reels = findViewById(R.id.imageButton3);
        subsecribe = findViewById(R.id.imageButton5);
        profile = findViewById(R.id.imageButton6);


        send.setOnClickListener(v -> {
            //send
        });

        delete.setOnClickListener(v -> {

            if (imageList.size()>1){
                imageList.remove(imageList.size()-1);
                int d = imageList.get(imageList.size()-1);
                //imageList.remove(imageList.size()-1);
                Glide.with(this)
                        .load(d)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }else {
                imageList.clear();
                messege.setImageResource(R.drawable.blank);
            }

        });

        blank.setOnClickListener(v -> {

            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.blank));
            }else {
                Glide.with(this)
                        .load(R.drawable.blank)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.blank);

        });



        a.setOnClickListener(v -> {

            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.a)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.a);

        });


        b.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.b)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.b);
        });


        c.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.c)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.c);
        });


        d.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.d)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.d);
        });


        e.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.e)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.e);
        });


        f.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.f)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.f);
        });


        g.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.g)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.g);
        });


        h.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.h)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.h);
        });


        i.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.i)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.i);
        });


        j.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.j)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.j);
        });


        k.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.k)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.k);
        });


        l.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.l)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.l);
        });


        m.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.m)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.m);
        });


        n.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.n)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.n);
        });


        o.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.o)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.o);
        });


        p.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.p)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.p);
        });


        q.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.q)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.q);
        });


        r.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.r)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.r);
        });



        s.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.s)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.s);
        });


        t.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.t)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.t);
        });


        u.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.u)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.u);
        });


        v.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.v)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.v);
        });


        w.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.w)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.w);
        });


        x.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.x)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.x);
        });


        y.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.y)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.y);
        });


        z.setOnClickListener(v -> {
            if (imageList.isEmpty()){
                messege.setImageBitmap(ImageMerger.resizeImage(this,R.drawable.a));
            }else {
                Glide.with(this)
                        .load(R.drawable.z)
                        .transform(new ImageMerger(this, imageList))
                        .into(messege);
            }
            imageList.add(R.drawable.z);
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBotActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBotActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBotActivity.this, ReelsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        subsecribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBotActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBotActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


    }
}