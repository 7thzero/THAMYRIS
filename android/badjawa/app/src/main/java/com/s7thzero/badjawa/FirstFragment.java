package com.s7thzero.badjawa;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.zip.CRC32;

public class FirstFragment extends Fragment {

    private EditText ssidBox;
    private EditText passwordBox;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ssidBox = (EditText)view.findViewById(R.id.ssidBx);
        passwordBox = (EditText)view.findViewById(R.id.passwordBx);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            private String smartCodeStr;
            private String pass;
            private String ssid;
            private String strInput;
            private int provingNumberRandom;
            private AudioTrack trackplayer_v3s;

            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                String ssidStr = ssidBox.getText().toString();
                String passStr = passwordBox.getText().toString();
                sendPairingDetails(ssidStr, passStr);
            }

            void sendPairingDetails(String ssidStr, String passwordStr){

                this.ssid = ssidStr;
                this.pass = passwordStr;

                int length = (this.ssid.length() + this.pass.length() + 6 + 3) * 4;
                int smartCodeInt = 24688;
                this.smartCodeStr = String.valueOf(smartCodeInt);
                this.provingNumberRandom = smartCodeInt;
                codeFreqTable = new CodeFreqTable();
                myList_v3s = new ArrayList<>();

                strInput = "\u0001" + getUserinfo();
                char crcVal = getCrcVal(this.strInput);     // Requires: '\u0001'+getUserinfo() which returns with a trailing '1'

                trackplayer_v3s =  new AudioTrack(AudioManager.STREAM_MUSIC, AudioConst.SAMPLE_FREQ, AudioFormat.CHANNEL_OUT_FRONT_LEFT, AudioFormat.ENCODING_PCM_16BIT, AudioTrack.getMinBufferSize(22050, 4, 2), 1);
                trackplayer_v3s.play();

                //
                // Play code block 1
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);

                for (int i = 0; i < this.strInput.length(); i++) {
                    play1Char(this.trackplayer_v3s, this.strInput.charAt(i));
                }

                play1Char(this.trackplayer_v3s, crcVal);

                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);


                //
                // Play code block 2
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);

                char s2 = '\u0012';
                char sc0 = (char) (smartCodeInt %16);
                String sc0Str = Short.toString((short)sc0);
                char sc1 = (char) (smartCodeInt/16 %16);
                String sc1Str = Short.toString((short)sc1);
                String sc12 = sc1Str+sc0Str;
                short scShort = Short.parseShort(sc12, 16);
                char scChar = (char)scShort;

                char sc2 = (char) (smartCodeInt/16/16 %16);
                String sc2Str = Short.toString((short)sc2);
                char sc3 = (char) (smartCodeInt/16/16/16 %16);
                String sc3Str = Short.toString((short)sc3);
                String sc23 = sc3Str+sc2Str;
                short sc2Short = Short.parseShort(sc23, 16);
                char sc2Char = (char)sc2Short;
                String combined = ""+s2+scChar+sc2Char;
                char crc = getCrcVal(combined);
                crc = (char)(crc & 0xFF);

                play1Char(this.trackplayer_v3s, s2);
                play1Char(this.trackplayer_v3s, scChar);
                play1Char(this.trackplayer_v3s, sc2Char);
                play1Char(this.trackplayer_v3s, crc);

                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);


                //
                // Play code block 3
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_BEGIN, AudioConst.mDuration);

                char s3 = '\u0002';
                char crcSsid = getCrcVal(this.ssid);
                crcSsid = (char)(crcSsid & 0xFF);
                char crcMangledSsid = getCrcValMangled(this.ssid);

                String combinedSection3 = "" + s3 + crcSsid + crcMangledSsid + scChar + sc2Char + this.pass;
                char crcSection3Complete = getCrcVal(combinedSection3);
                crcSection3Complete = (char)(crcSection3Complete & 0xFF);

                play1Char(this.trackplayer_v3s, s3);
                play1Char(this.trackplayer_v3s, crcSsid);
                play1Char(this.trackplayer_v3s, crcMangledSsid);
                play1Char(this.trackplayer_v3s, scChar);                    //
                play1Char(this.trackplayer_v3s, sc2Char);                   // Same characters as from Section 2

                for (int i = 0; i < this.pass.length(); i++) {              //
                    play1Char(this.trackplayer_v3s, this.pass.charAt(i));   // Play the password bytes
                }                                                           //

                play1Char(this.trackplayer_v3s, crcSection3Complete);       // Round it out with the complete section 3 crc

                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);
                generateAndPlayTone(AudioConst.FREQ_END, AudioConst.mDuration);
            }

            private String getUserinfo() {
                String str = this.ssid + "\u0001" + this.pass + "\u0001" + this.smartCodeStr+ AudioConst.MSG_DB_NOTIFY_REACHED;
                return str;
            }

            // Note to self: Java short is a 16 bit signed value that can track with 16bit PCM
            private List<Short> prep1Atom(int freqToPlay, int sampleFreq, int flyYBits, int durationMs) {
                Vector<Short> list = new Vector<Short>();
                int i5 = (durationMs * sampleFreq) / 1000;
                double d = (double) freqToPlay;
                double d2 = (double) sampleFreq;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = (d / d2) * 2.0d * 3.141592653589793d;
                double d4 = 0.0d;
                for (int i6 = 0; i6 < i5; i6++) {
                    double sin = Math.sin(d4);
                    double d5 = (double) flyYBits;
                    Double.isNaN(d5);
                    d4 += d3;
                    list.add(Short.valueOf((short) ((int) (sin * d5))));
                }
                return list;
            }

            private List<Short> myList_v3s;
            private CodeFreqTable codeFreqTable;
            private int lastPlayRate = -1;
            private void play1Char(AudioTrack audioTrack, char c) {
                int i;
                short s = (short) (c & 15);
                short s2 = (short) (((short) (c & 240)) >> 4);
                int i2 = 0;
                while (i2 < 2) {
                    if (i2 == 0) {
                        i = this.codeFreqTable.getFreqFromNum(s);                                      // First time through while loop
                    } else {
                        i = i2 == 1 ? this.codeFreqTable.getFreqFromNum(s2) : 0;                       // Second time through while loop  (each character is represented by 2 frequencies, each frequency is 1 HEX digit)
                    }
                    if (this.lastPlayRate == i) {
                        System.out.println("Freq Gap: " + AudioConst.FREQ_GAP);
                        generateAndPlayTone(AudioConst.FREQ_GAP, AudioConst.mDuration);
                    }
                    System.out.println(c + " freq: " + i);
                    generateAndPlayTone(i, AudioConst.mDuration);
                    this.lastPlayRate = i;
                    i2++;
                }
            }

            //
            // This takes the last byte of the CRC32 and returns it as a char
            private char getCrcVal(String str) {
                CRC32 crc32 = new CRC32();
                byte[] bArr = new byte[str.length()];
                for (byte b = 0; b < bArr.length; b = (byte) (b + 1)) {
                    bArr[b] = (byte) str.charAt(b);
                }
                crc32.update(bArr);
                byte value = (byte) ((int) (crc32.getValue() & 255));
                return (char) value;
            }

            //
            // This takes the CRC32 of the input string, shifts the output one byte to the 'right', then masks/returns that output
            private char getCrcValMangled(String str) {
                CRC32 crc32 = new CRC32();
                byte[] bArr = new byte[str.length()];
                for (byte b = 0; b < bArr.length; b = (byte) (b + 1)) {
                    bArr[b] = (byte) str.charAt(b);
                }
                crc32.update(bArr);

                long crcCalced = crc32.getValue();
                crcCalced = crcCalced >> 8;
                byte crcMangled = (byte)((int)crcCalced & 255);
                return (char) (crcMangled & 255);
            }

            private int getRandom(int i, int i2) {
                return (new Random().nextInt(i2) % ((i2 - i) + 1)) + i;
            }

            private void generateAndPlayTone(double freqHz, int durationMs)
            {
                List<Short> samplesRaw = prep1Atom(((int) freqHz), AudioConst.SAMPLE_FREQ, AudioConst.FLY_Y_BITS, AudioConst.mDuration);
                int samplesSize = samplesRaw.size();
                short[] samples = new short[samplesSize];
                for(int i = 0; i < samplesSize; i++){
                    samples[i] = samplesRaw.get(i);
                }

                trackplayer_v3s.write(samples, 0, samplesSize);
            }
        });
    }
}