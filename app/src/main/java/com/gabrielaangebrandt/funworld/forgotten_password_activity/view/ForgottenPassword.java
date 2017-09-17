package com.gabrielaangebrandt.funworld.forgotten_password_activity.view;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.models.database.DatabaseManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class ForgottenPassword extends AppCompatActivity {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.et_answer)
    EditText answer;
    @BindView(R.id.et_username)
    EditText username;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotten_password_layout);
        ButterKnife.bind(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @OnItemSelected(R.id.spinner)
    public void selectQuestion(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = parent.getItemAtPosition(position).toString();
    }

    @OnClick(R.id.btn_forgotten_pass)
    public void sendEmailRecoveryPassword() {
        Player user = DatabaseManager.checkIfUserExists("username", username.getText().toString());
        if (user != null) {
            String question = DatabaseManager.checkQuestion("username", username.getText().toString());
            if (question.equals(selectedItem)) {
                String userAnswer = DatabaseManager.checkAnswer("username", username.getText().toString());
                if (userAnswer.equals(answer.getText().toString())) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setMessage("Your password is: " + DatabaseManager.getPass("username", username.getText().toString()))
                            .show();
                } else {
                    Toast.makeText(this, R.string.wrongAnswer, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.wrongQuestion, Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, R.string.wrongUsername, Toast.LENGTH_LONG).show();
        }
    }
}
