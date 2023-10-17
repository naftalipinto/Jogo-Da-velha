package com.example.jogodavelha;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int qtd;
    private int jogador;
    private int matriz[][]= new int[3][3];
    private Button b[]= new Button[9];
    private String winner;
    private String player1;
    private String player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qtd=0;
        jogador=0;
        b[0]=findViewById(R.id.bt);
        b[1]=findViewById(R.id.bt1);
        b[2]=findViewById(R.id.bt2);
        b[3]=findViewById(R.id.bt3);
        b[4]=findViewById(R.id.bt4);
        b[5]=findViewById(R.id.bt5);
        b[6]=findViewById(R.id.bt10);
        b[7]=findViewById(R.id.bt9);
        b[8]=findViewById(R.id.bt8);

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[0], 0,0);

            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[1], 0,1);

            }
        });

        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[2], 0,2);

            }
        });

        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[3], 1,0);

            }
        });

        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[4], 1,1);
            }
        });

        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[5], 1,2);

            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[6], 2,0);

            }
        });

        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(b[7], 2,1);

            }
        });

        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play(b[8], 2,2);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemnovo:
                clear();
                final EditText edit2= new EditText(this);
                AlertDialog.Builder secplayer= new AlertDialog.Builder(this);
                secplayer.setMessage("Digite o nome do jogador 2: ");
                secplayer.setTitle("jogador 2");
                secplayer.setView(edit2);
                secplayer.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        player2= edit2.getText().toString();
                    }
                });

                secplayer.create();
                secplayer.show();

                final EditText edit1= new EditText(this);
                AlertDialog.Builder fplayer= new AlertDialog.Builder(this);
                fplayer.setMessage("Digite o nome do jogador 1: ");
                fplayer.setTitle("jogador 1");
                fplayer.setView(edit1);
                fplayer.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        player1= edit1.getText().toString();
                    }
                });

                fplayer.create();
                fplayer.show();

                //Toast.makeText(getApplicationContext(), "Comece a jogar", Toast.LENGTH_LONG).show();
        break;}
        return super.onOptionsItemSelected(item);
    }

    public  void play(Button b, int x, int y){

        b.setEnabled(true);

        if(jogador==1){
            matriz[x][y]=1;
            b.setText("X");
            jogador=2;
            winner=player1;
            playcheck(1);
        }
        else{

            matriz[x][y]=1;
            b.setText("O");
            jogador=1;
            winner=player2;
            playcheck(2);
        }
        qtd++;

    }

    public boolean win( int x ){


        for(int a=0;a<matriz.length;a++){
            if(matriz[a][0]==x && matriz[a][1]==x && matriz[a][2]==x){
                return true;
            }

            if(matriz[0][a]==x && matriz[1][a]==x && matriz[2][a]==x){
                return true;
            }
        }

        if(matriz[0][0]==x && matriz[1][1]==x && matriz[2][2]==x){
            return true;
        }

        if(matriz[0][2]==x && matriz[1][1]==x && matriz[2][0]==x){
            return true;
        }
        return false;
    }

    public  void playcheck(int x){
        if(win(x)==true){
            AlertDialog.Builder venceu= new AlertDialog.Builder( this);
            venceu.setTitle("Vitoria");
            venceu.setMessage(" O Jogador " +winner+" venceu");
            venceu.setIcon(android.R.drawable.star_on);
            venceu.setPositiveButton("ok",null);
            venceu.create();
            venceu.show();
            end();


        }


    }

    public void end(){
        for(int a=0;a<9;a++){
            b[a].setEnabled(false);
        }


    }

    public void clear(){
        for(int a=0;a<9;a++){
            b[a].setEnabled(true);
            b[a].setText("");
        }

        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++) {
                matriz[a][b] = 0;
            }
        }

        jogador=1;
        player1="";
        player2="";
        winner="";

    }


}