package com.gabrielaangebrandt.funworld.forgotten_password_activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import io.realm.Realm;

public class ForgottenPassword extends AppCompatActivity{
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.et_answer) EditText answer;
    @BindView(R.id.et_username) EditText username;
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
    public void selectQuestion(AdapterView<?> parent, View view, int position, long id){
        selectedItem = parent.getItemAtPosition(position).toString();
    }

    @OnClick(R.id.btn_forgotten_pass)
        public void sendEmailRecoveryPassword(){
        String username1 = username.getText().toString();
        String answer1 = answer.getText().toString();
        Realm realm = Realm.getDefaultInstance();
        Player player = realm.where(Player.class).equalTo("username", username1).findFirst();
        if(player != null) {
            if (player.getQuestion().equals(selectedItem)) {
                if (player.getAnswer().equals(answer1)) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{/*"recipient@example.com"*/"elich_dj@hotmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, R.string.emailSubject);
                    intent.putExtra(Intent.EXTRA_TEXT, "This is your password:");
                    try {
                        startActivity(Intent.createChooser(intent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, getText(R.string.toastEmailCannotBeSent), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.wrongAnswer, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.wrongQuestion, Toast.LENGTH_LONG).show();
            }
        }
    }
}
