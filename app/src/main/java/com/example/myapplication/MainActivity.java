package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SampleViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new ViewModelProvider(this).get(SampleViewModel.class);

        viewModel.getFoo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String foo) {
                getSupportActionBar().setTitle(foo);
            }
        });

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
                viewModel.getFoo().setValue(currentDateTime);
            }
        });
    }
}
