package hu.ait.sugarormdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hu.ait.sugarormdemo.model.Note;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvData = (TextView) findViewById(R.id.tvData);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note noteDummy = new Note("Note"+System.currentTimeMillis(),"DESC");
                noteDummy.save();

                tvData.setText("");
                queryNotes(tvData);
            }
        });

        queryNotes(tvData);
    }

    private void queryNotes(TextView tvData) {
        List<Note> notes = Note.listAll(Note.class);
        for (Note note : notes) {
            tvData.append(note.getTitle()+" "+note.getDescription()+"\n");
        }
    }
}

