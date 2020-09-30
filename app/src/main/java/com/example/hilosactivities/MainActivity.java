package com.example.hilosactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDesc;
    private TextView tvAsc;
    private TextView tvBoth;
    private EditText etNum;
    private Button butResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAsc=findViewById(R.id.tvAsc);
        tvDesc=findViewById(R.id.tvDesc);
        tvBoth=findViewById(R.id.tvBoth);
        etNum=findViewById(R.id.etNum);
        butResult=findViewById(R.id.butResult);

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

//                MiThreadBoth miThreadBoth = new MiThreadBoth(num);
//                miThreadBoth.start();
            }
        });
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
//
//    private String asc(int num) {
//        String res="";
//        for(int i=1;i<=num;i++){
//            res+=" "+i;
//            SystemClock.sleep(1000);
//
//        }
//        return res;
//    }


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