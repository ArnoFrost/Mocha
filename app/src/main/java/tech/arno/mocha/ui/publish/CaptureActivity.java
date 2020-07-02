package tech.arno.mocha.ui.publish;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tech.arno.libnavannotation.ActivityDestination;
import tech.arno.mocha.R;

@ActivityDestination(pageUrl = "main/tabs/publish", asStarter = false)
public class CaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }
}