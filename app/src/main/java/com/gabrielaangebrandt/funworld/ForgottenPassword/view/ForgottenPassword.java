package com.gabrielaangebrandt.funworld.ForgottenPassword.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgottenPassword extends AppCompatActivity{
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.et_forgotten_pass) EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotten_password_layout);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @OnClick(R.id.btn_forgotten_pass)
        public void sendEmailRecoveryPassword(){
        //uvjet da je pitanje i odgovor isti kao u bazi, DOPUNITI!!!!!!!!!!

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        //intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{/*"recipient@example.com"*/"elich_dj@hotmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT   , "This is your password:");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, getText(R.string.toastEmailCannotBeSent), Toast.LENGTH_SHORT).show();
        }
    }

}
