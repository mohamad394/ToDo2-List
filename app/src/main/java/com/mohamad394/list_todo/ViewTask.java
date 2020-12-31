package com.mohamad394.list_todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mohamad394.list_todo.classes.TaskClass;

import java.util.Date;

public class ViewTask extends AppCompatActivity {
    EditText nName,nContext;
    TextView nDate;
    private static DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        String dateText= TaskClass.longToDate(getIntent().getLongExtra("task date",0));
        nName=findViewById(R.id.meet_joseph );
        nContext=findViewById(R.id.have_to_mee);
        nDate=findViewById(R.id.date_of_task);
        nName.setText(getIntent().getStringExtra("task name"));
        nContext.setText(getIntent().getStringExtra("task context"));
        nDate.setText(dateText);
    }

    public void backtotask(View view) {
        Intent intent=new Intent(ViewTask.this,Daily.class);
        startActivity(intent);
        finish();
    }

    public void deleteTask(View view) {


        AlertDialog alertDialog = new AlertDialog.Builder(ViewTask.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Delete Task")
//set message
                .setMessage("Are you sure the Task  will be deleted? ")
//set positive button
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCategory();

                        Toast.makeText(getApplicationContext(),"The List  has been deleted",Toast.LENGTH_LONG).show();
                    }
                })
//set negative button
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }
    private void deleteCategory() {
        TaskClass note=new TaskClass(Lists.currentNotebookId,Daily.notes.get(getIntent().getIntExtra("task position",0)).idOfTask);
        String userId = FirebaseAuth.getInstance().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("User").child(userId ).child("Task").child(note.idOfTask);
        mDatabase.removeValue();

        Intent intent=new Intent(ViewTask.this,Daily.class);
        startActivity(intent);
        finish();

    }

    public void editeTask(View view) {
        TaskClass note=new TaskClass(Lists.currentNotebookId,Daily.notes.get(getIntent().getIntExtra("task position",0)).idOfTask,nName.getText().toString(),nContext.getText().toString(),new Date().getTime());
        Daily.writeNote(note);
        Toast.makeText(this,"Task is edite successfully" , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ViewTask.this,Daily.class));
        finish();
    }
}