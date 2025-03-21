package com.example.aslsignlanguage.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.aslsignlanguage.services.MyService;
import com.example.aslsignlanguage.R;
import com.example.aslsignlanguage.workers.WorkerStartServesBackground;
import com.example.aslsignlanguage.workers.DailyResetWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ProfileActivity extends AppCompatActivity /*implements TimePickerDialog.OnTimeSetListener*/{

    ImageView home,quiz,reels,aiChat,subsecribe;
    Button subsecribe2;
    TextView changePassword,popupLearning,certificate,restore,chatSupport,terms,privetPolicy;

    private final int[] arrStart = new int[2];
    private final int[] arrEnd = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        home = findViewById(R.id.imageButton1);
        quiz = findViewById(R.id.imageButton2);
        reels = findViewById(R.id.imageButton3);
        aiChat = findViewById(R.id.imageButton4);
        subsecribe = findViewById(R.id.imageButton5);
        subsecribe2 = findViewById(R.id.button7);
        changePassword = findViewById(R.id.textView39);
        popupLearning = findViewById(R.id.textView40);
        certificate = findViewById(R.id.textView42);
        restore = findViewById(R.id.textView43);
        chatSupport = findViewById(R.id.textView45);
        terms = findViewById(R.id.textView46);
        privetPolicy = findViewById(R.id.textView47);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ReelsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        aiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatBotActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        subsecribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        subsecribe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });


        popupLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                poupLearningDialog();

            }
        });


        certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });


        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });


        chatSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });

        privetPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePasswordDialog();

            }
        });



    }



    public void changePasswordDialog() {
        LayoutInflater inflater = LayoutInflater.from(ProfileActivity.this);
        View dialogView = inflater.inflate(R.layout.change_password_dialog, null);

        EditText password = dialogView.findViewById(R.id.editText1);
        EditText newPassword = dialogView.findViewById(R.id.editText2);
        EditText confirmPassword = dialogView.findViewById(R.id.editText3);
        ImageView close = dialogView.findViewById(R.id.imageView_close);
        Button save = dialogView.findViewById(R.id.button_save_and_print);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ProfileActivity.this, "save", Toast.LENGTH_SHORT).show();

            }
        });

        // Show the dialog
        dialog.setCancelable(false);
        dialog.show();
    }

    @SuppressLint({"UseSwitchCompatOrMaterialCode", "MissingInflatedId", "SetTextI18n"})
    public void poupLearningDialog() {
        LayoutInflater inflater = LayoutInflater.from(ProfileActivity.this);
        View dialogView = inflater.inflate(R.layout.popup_learning_dialog, null);

        SharedPreferences sharedPreferencesNotification = getSharedPreferences("notification", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("PoupLearningDialog", Context.MODE_PRIVATE);

        Map<String,?> allEntries = sharedPreferences.getAll();

        if (allEntries.isEmpty()) {
            // SharedPreferences is empty

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("showPopup",false);
            editor.putBoolean("oneTimesShowPopup",true);
            editor.putInt("numberTimesShowPopup",1);
            editor.putInt("countTimesShowPopup",0);
            editor.putInt("numberTimesShowPopupSecreenOn",3);
            editor.putInt("duration",0);
            editor.putBoolean("specificTimesShowPopup",true);
            editor.putLong("fromTimeToShowPopup",0);
            editor.putLong("toTimeToShowPopup",0);
            editor.putInt("startHour",7);
            editor.putInt("endHour",23);
            editor.putInt("startMinute",0);
            editor.putInt("endMinute",0);
            editor.putInt("lastTimeShowPopup",1439);
            editor.apply();

        }

        final int[] countShow = {sharedPreferences.getInt("numberTimesShowPopup",1)};
        final int[] countShowSecreenOn = {sharedPreferences.getInt("numberTimesShowPopupSecreenOn",3)};

        Toast.makeText(this, "last time : "+sharedPreferences.getInt("lastTimeShowPopup",0), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+sharedPreferences.getInt("countTimesShowPopup",0)+" "+sharedPreferences.getInt("numberTimesShowPopup",1), Toast.LENGTH_SHORT).show();

        Switch showPopup = dialogView.findViewById(R.id.switch1);
        CheckBox multipleTime = dialogView.findViewById(R.id.checkBox1);
        CheckBox oneTime = dialogView.findViewById(R.id.checkBox2);
        CheckBox specificTime = dialogView.findViewById(R.id.checkBox3);
        CheckBox allways = dialogView.findViewById(R.id.checkBox4);
        LinearLayout timesShow = dialogView.findViewById(R.id.LinearlayoutTimesShow);
        LinearLayout dateShow = dialogView.findViewById(R.id.linearDate);
        LinearLayout linearDateFrom = dialogView.findViewById(R.id.linearDateFrom);
        LinearLayout linearDateTo = dialogView.findViewById(R.id.linearDateTo);
        TextView countShowTextView = dialogView.findViewById(R.id.textView13);
        TextView fromDateTextView = dialogView.findViewById(R.id.textView16);
        TextView toDateTextView = dialogView.findViewById(R.id.textView18);
        ImageView close = dialogView.findViewById(R.id.imageView_close);
        ImageView add = dialogView.findViewById(R.id.ImageView2);
        ImageView mins = dialogView.findViewById(R.id.ImageView);
        Spinner spinner = dialogView.findViewById(R.id.spinner);
        ImageView addSecreenOn = dialogView.findViewById(R.id.ImageView4);
        ImageView minsSecreenOn = dialogView.findViewById(R.id.ImageView3);
        TextView countShowSecreenOnTextView = dialogView.findViewById(R.id.textView21);
        Button save = dialogView.findViewById(R.id.button_save);

        fromDateTextView.setText(
                (String.valueOf(sharedPreferences.getInt("startHour", 0)).length() > 1 ? (sharedPreferences.getInt("startHour", 0)) : "0"+(sharedPreferences.getInt("startHour", 0)))
                        +" : "+
                        (String.valueOf(sharedPreferences.getInt("startMinute", 0)).length() > 1 ? sharedPreferences.getInt("startMinute", 0) : "0"+sharedPreferences.getInt("startMinute", 0))
        );

        toDateTextView.setText(
                (String.valueOf(sharedPreferences.getInt("endHour", 0)).length() > 1 ? (sharedPreferences.getInt("endHour", 0)) : "0"+(sharedPreferences.getInt("endHour", 0)))
                        +" : "+
                        (String.valueOf(sharedPreferences.getInt("endMinute", 0)).length() > 1 ? sharedPreferences.getInt("endMinute", 0) : "0"+sharedPreferences.getInt("endMinute", 0))
        );

        arrStart[0] = sharedPreferences.getInt("startHour", 0);
        arrStart[1] = sharedPreferences.getInt("startMinute", 0);
        arrEnd[0] = sharedPreferences.getInt("endHour", 0);
        arrEnd[1] = sharedPreferences.getInt("endMinute", 0);

        countShowTextView.setText(String.valueOf(countShow[0]));
        countShowSecreenOnTextView.setText(String.valueOf(countShowSecreenOn[0]));

        if (sharedPreferences.getBoolean("showPopup",false)){
            showPopup.setChecked(true);
        }else {
            showPopup.setChecked(false);
        }

        if (sharedPreferences.getBoolean("oneTimesShowPopup",true)){
            timesShow.setVisibility(View.GONE);
            oneTime.setChecked(true);
            multipleTime.setChecked(false);
        }else {
            timesShow.setVisibility(View.VISIBLE);
            oneTime.setChecked(false);
            multipleTime.setChecked(true);
        }

        if (sharedPreferences.getBoolean("specificTimesShowPopup",false)){
            dateShow.setVisibility(View.VISIBLE);
            specificTime.setChecked(true);
            allways.setChecked(false);
        }else {
            dateShow.setVisibility(View.GONE);
            specificTime.setChecked(false);
            allways.setChecked(true);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinnerAdapter.add("  Second  ");
        spinnerAdapter.add("  Minute  ");
        spinnerAdapter.notifyDataSetChanged();

        // تعيين العنصر "Second" كافتراضي
        int selection = sharedPreferences.getInt("duration", 0);
        spinner.setSelection(selection);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        multipleTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                oneTime.setChecked(!isChecked);
                if (isChecked){
                    timesShow.setVisibility(View.VISIBLE);
                }

            }
        });

        oneTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                multipleTime.setChecked(!isChecked);
                if (isChecked){
                    timesShow.setVisibility(View.GONE);
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countShow[0]++;
                countShowTextView.setText(String.valueOf(countShow[0]));

            }
        });

        mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countShow[0]>1){
                    countShow[0]--;
                    countShowTextView.setText(String.valueOf(countShow[0]));
                }

            }
        });

        addSecreenOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countShowSecreenOn[0]++;
                countShowSecreenOnTextView.setText(String.valueOf(countShowSecreenOn[0]));

            }
        });

        minsSecreenOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countShowSecreenOn[0]>1){
                    countShowSecreenOn[0]--;
                    countShowSecreenOnTextView.setText(String.valueOf(countShowSecreenOn[0]));
                }

            }
        });

        specificTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                allways.setChecked(!isChecked);
                if (isChecked){
                    dateShow.setVisibility(View.VISIBLE);
                }

            }
        });

        allways.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                specificTime.setChecked(!isChecked);
                if (isChecked){
                    dateShow.setVisibility(View.GONE);
                }

            }
        });

        linearDateFrom.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                TimePickerDialog dialog = new TimePickerDialog(
                        ProfileActivity.this,
                        (view, hour, minute) -> {
                            arrStart[0] = hour;
                            arrStart[1] = minute;
                            fromDateTextView.setText(String.format("%02d:%02d", hour, minute));
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                dialog.show();

            }
        });

        linearDateTo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                 TimePickerDialog dialog = new TimePickerDialog(
                        ProfileActivity.this,
                        (view, hour, minute) -> {
                            arrEnd[0] = hour;
                            arrEnd[1] = minute;
                            toDateTextView.setText(String.format("%02d:%02d", hour, minute));
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                dialog.show();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("showPopup",showPopup.isChecked());
                editor.putBoolean("oneTimesShowPopup",oneTime.isChecked());
                editor.putInt("numberTimesShowPopup",countShow[0]);
                editor.putInt("numberTimesShowPopupSecreenOn",countShowSecreenOn[0]);
                editor.putInt("duration",spinner.getSelectedItemPosition());
                editor.putBoolean("specificTimesShowPopup",specificTime.isChecked());
                editor.putInt("startHour",arrStart[0]);
                editor.putInt("startMinute",arrStart[1]);
                editor.putInt("endHour",arrEnd[0]);
                editor.putInt("endMinute",arrEnd[1]);
                editor.apply();

                SharedPreferences.Editor editorNotification = sharedPreferencesNotification.edit();
                editorNotification.putBoolean("showNotification",!showPopup.isChecked());
                editorNotification.apply();


                if (showPopup.isChecked()){
                    Intent service = new Intent(ProfileActivity.this, MyService.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        getApplicationContext().startForegroundService(service);
                    }

                    //runPeriodicWork();
                    scheduleInitialReset();

                }else {
                    Intent service = new Intent(ProfileActivity.this, MyService.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        getApplicationContext().stopService(service);
                    }
                }

                Toast.makeText(ProfileActivity.this, "save", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        // Show the dialog
        dialog.setCancelable(false);
        dialog.show();

    }


//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        // Format the selected time as HH:mm
//        String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
//        Toast.makeText(this, "Selected time: " + formattedTime, Toast.LENGTH_SHORT).show();
//    }


    // run servece in background if its stopped
//    public void runPeriodicWork() {
//        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(WorkerStartServesBackground.class, 15, TimeUnit.MINUTES).build();
//        WorkManager.getInstance(getApplicationContext())
//                .enqueue(periodicWork);
//    }




    // to reverce the popup count to 0 at mednight
    private void scheduleInitialReset() {
        Calendar midnight = Calendar.getInstance();

        if (midnight.get(Calendar.HOUR_OF_DAY) >= 0) {
            midnight.add(Calendar.DAY_OF_YEAR, 1);
        }

        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);

        long delayInMillis = midnight.getTimeInMillis() - System.currentTimeMillis();

        OneTimeWorkRequest resetWork = new OneTimeWorkRequest.Builder(DailyResetWorker.class)
                .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(this)
                .enqueueUniqueWork(
                        "reset_popup_count",
                        ExistingWorkPolicy.REPLACE,
                        resetWork
                );
    }


}