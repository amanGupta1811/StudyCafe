package com.android.studycafetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    adapter taskAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        addDataRecyclerView();

    }

    void addDataRecyclerView() {
        Query query = utility.getCollectionReferenceForTask().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Task> options = new FirestoreRecyclerOptions.Builder<Task>().setQuery(query, Task.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new adapter(options, this);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        taskAdapter.startListening();
    }

    protected void onResume() {
        super.onResume();
        taskAdapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(MainActivity.this, addTask.class));
                finish();
                break;
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, signIn.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}