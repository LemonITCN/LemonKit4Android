package net.lemonsoft.lemonkit_samples;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import net.lemonsoft.lemonkit.annotations.FindView;
import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKActivity;

@SetContentView(R.layout.activity_main)
public class MainActivity extends LKActivity {

    private TextView myTextView;
    @FindView(R.id.myImageView)
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}