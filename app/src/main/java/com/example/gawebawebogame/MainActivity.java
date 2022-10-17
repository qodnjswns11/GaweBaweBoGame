package com.example.gawebawebogame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimImageView;
    ImageView setHandImageView;

    ImageButton gaweButton;
    ImageButton baweButton;
    ImageButton boButton;
    ImageButton replayButton;

    AnimationDrawable animationDrawable;

    TextToSpeech textToSpeech;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handAnimImageView = findViewById(R.id.hand_anim_image_view);
        setHandImageView = findViewById(R.id.set_hand_image_view);

        gaweButton = findViewById(R.id.gawe_button);
        baweButton = findViewById(R.id.bawe_button);
        boButton= findViewById(R.id.bo_button);
        replayButton = findViewById(R.id.replay_button);

        animationDrawable = (AnimationDrawable) handAnimImageView.getDrawable();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if ( status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.KOREAN);
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);
                }
            }
        });


    }

     public void button_click(View view) {
        switch (view.getId()){
            case R.id.replay_button:
                setHandImageView.setVisibility(View.GONE);
                handAnimImageView.setVisibility(View.VISIBLE);
                animationDrawable.start();
                voicePlay("가위바위보");
                replayButton.setEnabled(false);
                gaweButton.setEnabled(true);
                baweButton.setEnabled(true);
                boButton.setEnabled(true);
                break;

               case R.id.gawe_button:
               case R.id.bawe_button:
               case R.id.bo_button:
                   replayButton.setEnabled(true);
                   gaweButton.setEnabled(false);
                   baweButton.setEnabled(false);
                   boButton.setEnabled(false);
               animationDrawable.stop();
              handAnimImageView.setVisibility(View.GONE);
              setHandImageView.setVisibility(View.VISIBLE);

              int userHand = Integer.parseInt(view.getTag().toString());
              int comHand = setComHand();
              winCheck(userHand,comHand);
              break;

                default:
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();

    }
    public void voicePlay(String voiceText){
        textToSpeech.speak(voiceText,TextToSpeech.QUEUE_FLUSH,null,null);
    }

    public int setComHand(){
        int getcomHand = new Random().nextInt(3)+1;
        switch(getcomHand){
            case 1:
                setHandImageView.setImageResource(R.drawable.com_gawe);
                break;
            case 2:
                setHandImageView.setImageResource(R.drawable.com_bawe);
                break;

            case 3:
                setHandImageView.setImageResource(R.drawable.com_bo);
                break;
        }
        return getcomHand;
    }

    public void winCheck (int userHand,int comHand){
         int result=( 3+ userHand- comHand) % 3;
         switch( result) {
             case 0:
                 voicePlay("비겼어요,다시 시작하세요");
                 break;
             case 1:
                 voicePlay("당신이 이겼어요, 축하링");
                 break;
             case 2:
                 voicePlay("제가 이겼어요. 크크루삥");
                 break;

         }
    }
}
