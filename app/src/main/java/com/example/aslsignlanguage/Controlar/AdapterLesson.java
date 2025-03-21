package com.example.aslsignlanguage.Controlar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aslsignlanguage.R;

import java.util.ArrayList;
import java.util.List;

import com.example.aslsignlanguage.Model.Lesson;

public class AdapterLesson extends RecyclerView.Adapter<AdapterLesson.ViweHolder> {

//    List<Lesson> lessonList = new ArrayList<>();

    List<Object> lessonList = new ArrayList<>();
    OnVideoPlayLessonListener listener;

    //    Context context;

//    public AdapterLesson(Context context) {
//        this.context = context;
//    }

    public interface OnVideoPlayLessonListener {
        void playLesson(int position);
    }

    public static final int VIEW_TYPE_1 = 0;
    public static final int VIEW_TYPE_2 = 1;

    @SuppressLint("NotifyDataSetChanged")
    public void addLesson(List<Object> lessonList ,OnVideoPlayLessonListener listener ) {
        this.lessonList = lessonList != null ? lessonList : new ArrayList<>();;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearData() {
        if (lessonList != null) {
            lessonList.clear(); // Clear the list
            notifyDataSetChanged(); // Notify the adapter
        }
    }


    @Override
    public int getItemViewType(int position) {

        Object item = lessonList.get(position);
        if (item instanceof Lesson) {

            return VIEW_TYPE_1;
        } else if (item instanceof String) {

            return VIEW_TYPE_2;
        } else {
            //Log.e("AdapterReport", "Unknown type at position " + position + ": " + item.getClass().getSimpleName());
            throw new IllegalArgumentException("Unknown type at position " + position);
        }

    }



    @NonNull
    @Override
    public AdapterLesson.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_1) {
            View view = inflater.inflate(R.layout.lesson, parent, false);
            return new ViweHolder(view);
        } else {
            View view = inflater.inflate(R.layout.unit, parent, false);
            return new ViweHolder(view);
        }

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterLesson.ViweHolder holder, int position) {

        int pos = holder.getBindingAdapterPosition();

        if (lessonList.get(position) instanceof Lesson) {

            Lesson lesson = (Lesson) lessonList.get(position);
            holder.numberLesson.setText("Lesson "+lesson.getId());
            holder.titalLesson.setText(lesson.getTitleLesson());
            holder.linearLayout.setBackgroundResource(R.color.c3);

            holder.play.setVisibility(View.GONE);

            if (lesson.getId() == 3 && lesson.getUnit() == 1){
                holder.linearLayout.setBackgroundResource(R.color.c2);
            }

            if (lesson.getId() < 3 && lesson.getUnit() == 1){
                holder.img.setImageResource(R.drawable.checked);
            }else{
                holder.img.setImageResource(R.drawable.star);
                if (lesson.getId() == 3){
                    holder.play.setVisibility(View.VISIBLE);
                }
            }

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.playLesson(pos);  // Notify the activity
                    }
                }
            });


        }else {

            holder.unit.setText(lessonList.get(pos).toString());

        }




    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{
        ImageView img,play;
        TextView numberLesson,titalLesson,unit;
        LinearLayout linearLayout;

        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView6);
            play = itemView.findViewById(R.id.imageView14);
            numberLesson = itemView.findViewById(R.id.textView17);
            titalLesson = itemView.findViewById(R.id.textView18);
            titalLesson = itemView.findViewById(R.id.textView18);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            unit = itemView.findViewById(R.id.textViewUnit17);

        }
    }



}
