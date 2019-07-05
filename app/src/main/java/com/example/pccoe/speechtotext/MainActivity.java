package com.example.pccoe.speechtotext;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView voiceInput,speakBtn;
    public static final int REQ_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceInput=findViewById(R.id.tv);
        speakBtn=findViewById(R.id.btnspeak);

        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //calling for inbuilt speech reconginzer
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //to invoke language support and language form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //give local support to speech Recognizer
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //its my speech recognizer
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak slowly slowly");

                startActivityForResult(intent,REQ_CODE);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQ_CODE:
                    if(resultCode==RESULT_OK && null!=data)
                    {
                        ArrayList <String> stringArrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        voiceInput.setText(stringArrayList.get(0));
                    }
                break;

        }
    }
}
