package com.gabrielaangebrandt.funworld.register_activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabrielaangebrandt.funworld.LauncherActivity.view.Login;
import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.database.DatabaseConfig;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "sdada";
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_username) EditText etUsername;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password1) EditText etPassword1;
    @BindView(R.id.et_password2) EditText etPassword2;
    @BindView(R.id.et_answer) EditText etAnswer;
    @BindView(R.id.spinner_choose_question) Spinner question;
    private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(adapter);
        question.setOnItemSelectedListener(this);
    }

  @OnClick(R.id.btn_register_player)
    void checkLoginData() {
      if (etName.getText().toString() == "" || etName.getText().toString().isEmpty() ||
              etUsername.getText().toString() == "" || etUsername.getText().toString().isEmpty() ||
              etPassword1.getText().toString() == "" || etPassword1.getText().toString().isEmpty() ||
              etPassword2.getText().toString() == "" || etPassword2.getText().toString().isEmpty() ||
              etEmail.getText().toString() == "" || etEmail.getText().toString().isEmpty() ||
              etAnswer.getText().toString() == "" || etAnswer.getText().toString().isEmpty()) {
          Toast.makeText(this, getText(R.string.elementsArentEntered), Toast.LENGTH_LONG).show();
      } else {
          String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
          Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
          Matcher matcher = pattern.matcher(etEmail.getText().toString());
       if(matcher.matches()){
           if (etPassword1.getText().toString().equals(etPassword2.getText().toString())) {

               String name, username, pass, email, answer;

               name = etName.getText().toString();
               username = etUsername.getText().toString();
               pass = etPassword1.getText().toString();
               email = etEmail.getText().toString();
               answer = etAnswer.getText().toString();

               Player player = new Player(name, username, pass, email, q, answer, 0, 0, 0);
               Realm object = DatabaseConfig.getRealmInstance();
               object.beginTransaction();
               object.copyToRealmOrUpdate(player);
               object.commitTransaction();

               Toast.makeText(this, R.string.successfullRegistration, Toast.LENGTH_LONG).show();
               Intent intent = new Intent(this, Login.class);
               startActivity(intent);

           } else {
               etPassword2.setError(getText(R.string.error_passwordsDontMatch));
           }
          }else{
           Toast.makeText(this, getText(R.string.emailIsNotValid), Toast.LENGTH_LONG).show();
       }

      }
  }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        q = question.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, getText(R.string.elementsArentEntered), Toast.LENGTH_LONG).show();
    }
}
