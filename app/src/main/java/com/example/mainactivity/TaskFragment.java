package com.example.mainactivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mainactivity.R;
import com.example.mainactivity.Task;

import java.nio.Buffer;
import java.util.UUID;

public class TaskFragment extends Fragment {

    private static UUID ARG_TASK_ID = null;
    private Task task;
    private EditText nameField;
    private Button dateButton;
    private CheckBox doneCheckBox;


    public TaskFragment()
    {
        task = new Task();
        ARG_TASK_ID = task.getId();
    }
    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = new Task();
        ARG_TASK_ID = task.getId();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Pobieranie i zwracanie widoku z pliku fragment_task.xml.
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        // Pobieranie referencji do widoków w fragmencie.
        nameField = view.findViewById(R.id.task_name);
        dateButton = view.findViewById(R.id.task_date);
        doneCheckBox = view.findViewById(R.id.task_done);

        updateUI();

        // Dodanie obsługi zmiany tekstu w polu nameField.
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Przed zmianą tekstu.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Po zmianie tekstu. Aktualizuj pole name w obiekcie Task.
                task.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Po zakończeniu zmiany tekstu.
            }

        });

        dateButton.setText(task.getDate().toString());
        dateButton.setEnabled(false);

        doneCheckBox.setChecked(task.isDone());
        doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setDone(isChecked));
        return inflater.inflate(R.layout.fragment_task, container, false);

    }

    private void updateUI() {
        nameField.setText(task.getName());
        dateButton.setText(task.getDate().toString());
        doneCheckBox.setChecked(task.isDone());
        dateButton.setEnabled(false); // Można dodać obsługę wybierania daty w tym miejscu.
    }

    public static TaskFragment newInstance(UUID taskId) {
        Bundle bundle = new Bundle();
        //bundle.putSerializable(ARG_TASK_ID, taskId.toString()); // Zamień UUID na String
        bundle.putSerializable(ARG_TASK_ID, taskId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }


}
