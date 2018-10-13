package com.nerdroco.admin.implicitintentlab;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText uriEditText;
    private EditText locEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find edit text views
        uriEditText = findViewById(R.id.uriEditText);
        locEditText = findViewById(R.id.locEditText);
        shareEditText = findViewById(R.id.shareEditText);
    }

    public void uriClick(View view) {
        // Get the url Text
        String urlText = uriEditText.getText().toString();

        // Parse the uri and create intent
        Uri webpage = Uri.parse(urlText);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // check if there is an app able to fulfill intent and proceed
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void locClick(View view) {
        // Get location text
        String loc = locEditText.getText().toString();

        // parse text and create intent
        Uri addressUri = Uri.parse("geo:0,0?q" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find activity to handle intent and start activity
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareClick(View view) {
        // Get share text
        String shareText =shareEditText.getText().toString();
        String mimeType = "text/plain";

        // Allows us to give user option of picking app to use
        ShareCompat.IntentBuilder
                .from(this)  // Where activity launched
                .setType(mimeType)  // MIME type of item being shared
                .setChooserTitle(R.string.chooseTitle)  // Title that appears on system app chooser
                .setText(shareText) // actual text to be shared
                .startChooser();  // Show the system app chooser and send the Intent
    }
}
