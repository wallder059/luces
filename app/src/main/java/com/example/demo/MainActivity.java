package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private View rojoArriba,amarilloArriba,verdeArriba;
    private View rojoAbajo,amarilloAbajo,verdeAbajo;
    private Button btnIniciar;
    private boolean corriendo=false;
    private int paso=0;
    private static final long TIEMPO=3000L; //3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rojoArriba=findViewById(R.id.a_red);
        amarilloArriba=findViewById(R.id.a_yellow);
        verdeArriba=findViewById(R.id.a_green);
        rojoAbajo=findViewById(R.id.b_red);
        amarilloAbajo=findViewById(R.id.b_yellow);
        verdeAbajo=findViewById(R.id.b_green);
        btnIniciar=findViewById(R.id.btnStart);

        //2) oncreate com oincia
        ponerGris();
        rojoArriba.setBackgroundResource(R.drawable.circulorojo);
        verdeAbajo.setBackgroundResource(R.drawable.circuloverde);

        //esto hace que al  hacer click se active el hilo
        btnIniciar.setOnClickListener(v->{
            if(corriendo) return;
            corriendo=true;
            btnIniciar.setEnabled(false);
            paso=0;
            Thread hilo=new Thread(()->{
                while(corriendo){
                    int pasoActual=paso;
                    //cambiar luces en UI
                    runOnUiThread(()->cambiarLuces(pasoActual));
                    paso=(paso+1)%3;
                    try{Thread.sleep(TIEMPO);
                    }catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            hilo.start();
        });
    }

    //esto use para poner colores es la como lo que es logica del semaforo
    private void cambiarLuces(int k){
        ponerGris();
        if(k==0){
            rojoArriba.setBackgroundResource(R.drawable.circulorojo);
            verdeAbajo.setBackgroundResource(R.drawable.circuloverde);
        }else if(k==1){
            amarilloArriba.setBackgroundResource(R.drawable.circuloamarillo);
            amarilloAbajo.setBackgroundResource(R.drawable.circuloamarillo);
        }else{
            verdeArriba.setBackgroundResource(R.drawable.circuloverde);
            rojoAbajo.setBackgroundResource(R.drawable.circulorojo);
        }
    }

    //esto esta para llamar para ponerlo en gris
    private void ponerGris(){
        rojoArriba.setBackgroundResource(R.drawable.circulogris);
        amarilloArriba.setBackgroundResource(R.drawable.circulogris);
        verdeArriba.setBackgroundResource(R.drawable.circulogris);
        rojoAbajo.setBackgroundResource(R.drawable.circulogris);
        amarilloAbajo.setBackgroundResource(R.drawable.circulogris);
        verdeAbajo.setBackgroundResource(R.drawable.circulogris);
    }
}
