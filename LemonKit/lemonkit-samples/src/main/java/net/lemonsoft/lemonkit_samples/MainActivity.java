package net.lemonsoft.lemonkit_samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import net.lemonsoft.lemonkit.annotations.FindView;
import net.lemonsoft.lemonkit.core.parser.LKUIAnnotationParser;

public class MainActivity extends Activity {

    private TextView myTextView;
    @FindView(R.id.myImageView)
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LKUIAnnotationParser.parse(this);// 解析注解
        System.out.println(myTextView);
    }

}