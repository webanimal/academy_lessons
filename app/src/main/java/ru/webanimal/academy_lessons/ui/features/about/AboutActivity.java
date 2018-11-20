package ru.webanimal.academy_lessons.ui.features.about;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import ru.webanimal.academy_lessons.R;

public class AboutActivity extends AppCompatActivity {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.sendButton:
                    sendMessage();
                    break;

                case R.id.twitterButton:
                    showToast(R.string.activity_main_show_twitter);
                    break;

                case R.id.fbButton:
                    showToast(R.string.activity_main_show_fb);
                    break;

                case R.id.instButton:
                    showToast(R.string.activity_main_show_inst);
                    break;
            }
        }
    };


    //==============================================================================================
    // Activity callbacks
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(getString(R.string.activity_main_title));
    }

    @Override
    protected void onResume() {
        super.onResume();

        setListeners(true);
    }

    @Override
    protected void onPause() {
        setListeners(false);

        super.onPause();
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void sendMessage() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(getString(R.string.send_mail_intent_type));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.send_mail_default_email)});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.send_mail_default_subject));
        i.putExtra(Intent.EXTRA_TEXT, getMessage());

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(i, getString(R.string.send_mail_chooser_title)));

        } else {
            showToast(R.string.alert_send_message_application_not_found);
        }
    }

    private String getMessage() {
        String message = ((AppCompatEditText) findViewById(R.id.inputText)).getText().toString();
        return TextUtils.isEmpty(message) ? "" : message;
    }

    private void showToast(int res) {
        Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show();
    }

    private void setListeners(boolean shouldSetListener) {
        findViewById(R.id.sendButton).setOnClickListener(shouldSetListener ? clickListener : null);
        findViewById(R.id.twitterButton).setOnClickListener(shouldSetListener ? clickListener : null);
        findViewById(R.id.fbButton).setOnClickListener(shouldSetListener ? clickListener : null);
        findViewById(R.id.instButton).setOnClickListener(shouldSetListener ? clickListener : null);
    }
}