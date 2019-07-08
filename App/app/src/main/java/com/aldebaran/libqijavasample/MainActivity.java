package com.aldebaran.libqijavasample;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;

import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.aldebaran.qi.AnyObject;

import com.aldebaran.qi.Session;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    Session session;
    Context context;
    AnyObject say;
    AnyObject move;
    AnyObject autoLife;
    AnyObject manipulateSay;
    //to lazy to find a nice place..

    EditText robotIp;
    Button hello;
    Button instrument;
    Button konitchiwa;
    Button musicQuiz;

    Button warmUp;
    Button stretch;
    Button strength;
    Button coolDown;
    Button pmr;

    Button bingo;
    Button memoryGame;
    Button bunny;

    Button stopp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // init views
        robotIp = (EditText) findViewById(R.id.robot_ip);
        robotIp.setText(getPreferences(MODE_PRIVATE).getString("robotIP", "192.168.0.10"));

        hello= (Button) findViewById(R.id.hello);
        instrument= (Button) findViewById(R.id.instrument);
        konitchiwa= (Button) findViewById(R.id.konitchiwaSong);
        musicQuiz= (Button) findViewById(R.id.musik);

        warmUp= (Button) findViewById(R.id.warmUp);
        stretch= (Button) findViewById(R.id.stretch);
        strength= (Button) findViewById(R.id.strength);
        coolDown= (Button) findViewById(R.id.coolDown);
        pmr= (Button) findViewById(R.id.pmr);

        bingo= (Button) findViewById(R.id.bingo);
        memoryGame= (Button) findViewById(R.id.memory);
        bunny  = (Button) findViewById(R.id.bunny);

        stopp= (Button) findViewById(R.id.stopp);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        session.close();
    }

    public void onClickConnect(View view) {

            String ip = robotIp.getText().toString();
            try {
                if (!TextUtils.isEmpty(ip)) {
                    String endpoint = ip + ":9559";




                    //connect Session
                    getPreferences(MODE_PRIVATE).edit().putString("robotIP",robotIp.getText().toString()).apply();
                    Session session = new Session();
                    session.connect(endpoint).get();

                    manipulateSay = session.service("ALTextToSpeech").get();
                    autoLife = session.service("ALAutonomousLife").get();
                    say = session.service("ALAnimatedSpeech").get();
                    move = session.service("ALMotion").get();

                    hello.setEnabled(true);
                    instrument.setEnabled(true);
                    konitchiwa.setEnabled(true);
                    musicQuiz.setEnabled(true);

                    warmUp.setEnabled(true);
                    stretch.setEnabled(true);
                    strength.setEnabled(true);
                    coolDown.setEnabled(true);
                    pmr.setEnabled(true);

                    bingo.setEnabled(true);
                    memoryGame.setEnabled(true);
                    bunny.setEnabled(true);


                    stopp.setEnabled(true);


                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
    }

    public void startHello(View view){

        try {

            move.call(void.class, "setAngles", "HipPitch", -0.6, 0.3).get();
            say.call(void.class, "say", "Konitschiwa").get();
            move.call(void.class, "setAngles", "HipPitch", 0, 0.2).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void startInstrumentQuiz(View view){

        try {
            autoLife.call(void.class, "switchFocus", "instrumentquiz-5d6906/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startKonitchiwa(View view){

        try {
            autoLife.call(void.class, "switchFocus", "konitschiwasong-96fcef/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startMusicQuiz(View view){

        try {
            autoLife.call(void.class, "switchFocus", "musikquiz-94bc1b/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startWarmUp(View view){

        try {
            autoLife.call(void.class, "switchFocus", "aufwarmen-ca858a/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startStretching(View view){

        try {
            autoLife.call(void.class, "switchFocus", "dehnen-467e0f/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startStrength(View view){

        try {
            autoLife.call(void.class, "switchFocus", "kraft-4c4059/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startCoolDown(View view){

        try {
            autoLife.call(void.class, "switchFocus", "entspannung-09154f/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startPMR(View view){

        try {
            autoLife.call(void.class, "switchFocus", "muskelentspannung-1d4940/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startBingo(View view){

        try {
            autoLife.call(void.class, "switchFocus", "bingo-350a3a/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startMemory(View view){

        try {
            autoLife.call(void.class, "switchFocus", "memory-e22a5b/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startBunny(View view){

        try {
            autoLife.call(void.class, "switchFocus", "hasenfangen/behavior_1").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }




    public void stoppBehavior(View view){

        try {
            autoLife.call(void.class, "stopFocus").get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
