package ru.webanimal.academy_lessons.ui.features.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.webanimal.academy_lessons.R;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        getSupportFragmentManager().beginTransaction().replace(R.id.tutorial_frame, TutorialFragment.create()).commit();
    }
}
