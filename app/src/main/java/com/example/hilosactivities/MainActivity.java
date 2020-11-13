package com.example.hilosactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView tvDesc;
    private TextView tvAsc;
    private TextView tvBoth;
    private EditText etNum;
    private Button butResult;
    private ImageView imgUrlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAsc=findViewById(R.id.tvAsc);
        tvDesc=findViewById(R.id.tvDesc);
        tvBoth=findViewById(R.id.tvBoth);
        etNum=findViewById(R.id.etNum);
        butResult=findViewById(R.id.butResult);
        imgUrlId=findViewById(R.id.imgUrlId);

        MiThreadPhoto miThreadPhoto = new MiThreadPhoto();
        miThreadPhoto.start();



        butResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvAsc.setText(R.string.tvAsc);
                tvDesc.setText(R.string.tvDesc);
                tvBoth.setText(R.string.tvBoth);

                int num=Integer.parseInt(etNum.getText().toString());

                MiThreadAsc miThreadAsc = new MiThreadAsc(num);
                miThreadAsc.start();

                MiThreadDesc miThreadDesc = new MiThreadDesc(num);
                miThreadDesc.start();

            }
        });
    }
    public class MiThreadPhoto extends Thread {

        public MiThreadPhoto() {
        }

        @Override
        public void run() {
//            result = asc(num);

            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Picasso.get().load("https://archive.org/details/doki_20201113").into(imgUrlId);
                    Picasso.Builder builder = new Picasso.Builder(getBaseContext());
                    builder.listener(new Picasso.Listener()
                    {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                            Log.d("miFiltro","Uri: "+uri+"<--/ failed");
                            Log.d("miFiltro","Exception: "+exception+"<--/ Exception");

                        }


                    });
                    builder.build().load("https://archive.org/download/doki_20201113/WhatsApp%20Image%202018-02-16%20at%2013.59.55.jpeg").into(imgUrlId);
                    Log.d("miFiltro","Uri: "+imgUrlId.getImageAlpha()+"<--/ failed");

                }
            });
        }
    }
    public class MiThreadAsc extends Thread {
        private int num;
        private String result;

        public MiThreadAsc(int num) {
            this.num = num;
        }

        @Override
        public void run() {
//            result = asc(num);

            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(int i=1;i<=num;i++){

                        SystemClock.sleep(100);
                        tvAsc.append(i + " ");
                        tvBoth.append(i + " ");
                    }

                }
            });
        }
    }



    public class MiThreadDesc extends Thread {
        private int num;
        private String result;

        public MiThreadDesc(int num) {
            this.num = num;
        }

        @Override
        public void run() {
//            result = desc(num);

            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(int i=num;1<=i;i--) {
                        tvDesc.append(i + " ");
                        tvBoth.append(i + " ");

                    }
                }
            });
        }
    }

//
//    private String desc(int num) {
//        String res="";
//        for(int i=num;1<=i;i--){
//            res+=" "+i;
//            SystemClock.sleep(1500);
//        }
//        return res;
//    }

//    public class MiThreadBoth extends Thread {
//        private int num;
//        private String resultAsc;
//        private String resultDesc;
//        private int [] result;
//
//        public MiThreadBoth(int num) {
//            this.num = num;
//        }
//
//        @Override
//        public void run() {
////            resultAsc=asc(num);
////            resultDesc=desc(num);
////            result=Integer.parseInt(resultAsc);
//
//            //necesario para poder usar los elementos visuales (view) y modificarlos
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    tvBoth.append(result + "\n");
//                }
//            });
//        }
//    }
}