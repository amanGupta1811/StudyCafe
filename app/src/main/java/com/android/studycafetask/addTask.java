package com.android.studycafetask;

import static com.google.firebase.Timestamp.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class addTask extends AppCompatActivity {

    EditText taskTitle, taskDetail;
    ImageButton saveTaskBtn;
    TextView completionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        saveTaskBtn = findViewById(R.id.btn_Save);
        taskTitle = findViewById(R.id.task_title);
        taskDetail = findViewById(R.id.task_details);
        completionDate = findViewById(R.id.completonDate);

        completionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        addTask.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                completionDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        saveTaskBtn.setOnClickListener((v)-> saveTask());
    }

    void saveTask(){
        String title = taskTitle.getText().toString();
        String detail = taskDetail.getText().toString();
        String date = completionDate.getText().toString();
        if(title==null || title.isEmpty()){
            taskTitle.setError("Title is required");
        }
        else {
            Task task = new Task();
            task.setTitleT(title);
            task.setDetailT(detail);
            task.setCompletionDateT(date);
            task.setTimestamp(Timestamp.now());

            saveTaskToFirebase(task);
        }
    }

    void saveTaskToFirebase(Task task){
        DocumentReference documentReference;
        documentReference = utility.getCollectionReferenceForTask().document();


        documentReference.set(task).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if(task.isSuccessful()){

                    Intent addData = new Intent(addTask.this,MainActivity.class);
                    startActivity(addData);
                    finish();
                }
                else{
                    utility.showToast(addTask.this,"failed...");
                }
            }
        });

    }
}