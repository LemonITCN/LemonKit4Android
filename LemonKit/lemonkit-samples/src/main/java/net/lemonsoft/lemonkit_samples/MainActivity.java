package net.lemonsoft.lemonkit_samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import net.lemonsoft.lemonkit.annotations.FindView;

public class MainActivity extends Activity {

    @FindView
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}