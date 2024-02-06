package com.enesbulut.blackjack;

import static com.enesbulut.blackjack.SgStatus.SIGORTALI;
import static com.enesbulut.blackjack.SgStatus.SIGORTASIZ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private static ImageView[] playerCards;
    private static ImageView[] player1Splits;
    private static ImageView[] player2Splits;
    private static ImageView[] caseCards;
    private static  Button oynaButton;
    private static Button kartButton;
    private static Button pasButton;
    private static Button doubleButton;
    private static Button splitButton;
    private static Button splitPasButton1;
    private static Button splitPasButton2;
    private static Button splitKartButton1;
    private static Button splitKartButton2;
    private static Button downButton;
    private static Button upButton;
    private static  TextView caseToplamText;
    private static TextView playerToplamText;
    private static TextView playerToplamText2;
    private static TextView bahisText;
    private static  TextView bakiyeText;
    public static List<Player> players = new ArrayList<>();
    public static GameDeck gameDeck;
    public static Vault vault;
    public int bakiye;
    public boolean oyun;
    public int bahis;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        beginning();
        //[]]s[i].setImageResource(R.drawable.default_card_image); örnek atama

        for(int i=0;i<7;i++){
            setPlayerCard(i,"");
            setPlayerSplitedCard(0,i,"");
            setPlayerSplitedCard(1,i,"");
            if (i<5){
                setCaseCard(i,"");
            }
        }

        caseToplamText.setVisibility(View.GONE);
        playerToplamText.setVisibility(View.GONE);
        playerToplamText2.setVisibility(View.GONE);
        doubleButton.setVisibility(View.GONE);
        pasButton.setVisibility(View.GONE);
        kartButton.setVisibility(View.GONE);
        splitButton.setVisibility(View.INVISIBLE);
        splitButton.setClickable(false);
        splitPasButton1.setVisibility(View.GONE);
        splitKartButton1.setVisibility(View.GONE);
        splitPasButton2.setVisibility(View.GONE);
        splitKartButton2.setVisibility(View.GONE);


        bakiye = loadBakiye();// degisecek?? kaydetme falan.
        bahis = Integer.parseInt(bahisText.getText().toString());

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bahis = (Integer.parseInt(bahisText.getText().toString()) )-10;
                bahisText.setText(String.valueOf(bahis));
                upButton.setClickable(true);
                upButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yesilRenk));
                if (bahis<=10){
                    downButton.setClickable(false);
                    downButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.solukRenk));
                } else {
                    downButton.setClickable(true);
                    downButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.kirmiziRenk));
                }

            }
        });
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bahis = (Integer.parseInt(bahisText.getText().toString()) )+10;
                bahisText.setText(String.valueOf(bahis));
                downButton.setClickable(true);
                downButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.kirmiziRenk));
                if (bahis >= bakiye) {
                    upButton.setClickable(false);
                    upButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.solukRenk));
                } else {
                    upButton.setClickable(true);
                    upButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yesilRenk));
                }
            }
        });

        String bakiyeString = String.valueOf(bakiye);
        bakiyeText.setText(bakiyeString);




        oynaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bahis = Integer.parseInt(bahisText.getText().toString());
                gameDeck = UtilService.createGameDeck();
                vault = UtilService.createVault();

                    try{
                        vault.getVaultDeck().resetCards();
                    }catch (Exception e){

                    }

                    //offVisibility("oynaButton");
                oynaButton.setVisibility(View.GONE);
                upButton.setVisibility(View.GONE);
                downButton.setVisibility(View.GONE);
                kartButton.setVisibility(View.VISIBLE);
                pasButton.setVisibility(View.VISIBLE);



                //createPlayer();
                Player player = new Player("Oyuncu");
                PlayerDeck playerDeck = new PlayerDeck();

                player.resetPlayerDeck();
                if (bahis > bakiye) {
                    Toast.makeText(MainActivity.getContext(), "Yetersiz Bakiye. Bakiyeye 500 eklendi.", Toast.LENGTH_SHORT).show();
                    bakiye += 500;
                }
                playerDeck.addCard(gameDeck.buyCard());
                playerDeck.addCard(gameDeck.buyCard());
                //playerDeck.setCards(new ArrayList<>(List.of(new Card("3", 3, "sinek3"),
                     //    new Card("3", 3,"karo3"))));
                player.setPlayerDeck(playerDeck);

                bakiye -= bahis;
                saveBakiye(bakiye);
                bakiyeText.setText(String.valueOf(bakiye));

                for (int i = 0; i < 2; i++) {
                    setPlayerCard(i, player.getPlayerDeckByIndex(0).getCard(i).getCardFullName());
                }
                if (player.getPlayerDeckByIndex(0).calculateCardTotal().contains("21")) {
                    player.getPlayerDeckByIndex(0).setStatus(Status.BLACKJACK);
                }
                playerToplamText.setText("Toplam: " + player.getPlayerDeckByIndex(0).calculateCardTotal());
                playerToplamText.setVisibility(View.VISIBLE);

                // Kasa kartlarını çek
                VaultDeck vaultDeck = new VaultDeck();
                vaultDeck.addCard(gameDeck.buyCard());
                vaultDeck.addCard(gameDeck.buyCard());
                //vaultDeck.setCards(new ArrayList<>(List.of(
                //        new Card("A", 1,"kupaas"),new Card("10", 10,"kupa10"))));

                vault.setVaultDeck(vaultDeck);
                setCaseCard(0, vault.getVaultDeck().getCard(0).getCardFullName());
                setCaseCard(1, "back");
                caseToplamText.setText("Toplam: " + vault.getVaultDeck().getCard(0).getValue());
                caseToplamText.setVisibility(View.VISIBLE);


                if (vaultDeck.getCard(0).getName().equals("A")) {
                    vaultDeck.setSgStatus(SgStatus.SIGORTAELI);
                }
                if (vaultDeck.calculateCardTotal().contains("21")) {
                    vaultDeck.setStatus(Status.BLACKJACK);
                }

                //Sorular
                if (vaultDeck.getSgStatus() == SgStatus.SIGORTAELI && bakiye > bahis) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Sigorta Yapmak İstiyor Musunuz?");
                    builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bakiye = bakiye - bahis;
                            saveBakiye(bakiye);
                            bakiyeText.setText(String.valueOf(bakiye));
                            player.getPlayerDeckByIndex(0).setSgStatus(SIGORTALI);
                            if (vaultDeck.getStatus() == Status.BLACKJACK){
                                bakiye += bahis * 3;
                                saveBakiye(bakiye);
                                bakiyeText.setText(String.valueOf(bakiye));
                                setCaseCard(1,vaultDeck.getCard(1).getCardFullName());
                                caseToplamText.setText("Toplam: 21");
                                //yeni oyun
                                makeToast("Kasa Blackjack! Kazandın.");
                                yeniElSorusu();
                            }
                        }
                    });
                    builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            player.getPlayerDeckByIndex(0).setSgStatus(SIGORTASIZ);
                            if (vaultDeck.getStatus() == Status.BLACKJACK){
                                //yeni oyun
                                setCaseCard(1,vaultDeck.getCard(1).getCardFullName());
                                caseToplamText.setText("Toplam: 21");
                                makeToast("Kasa Blackjack! Kaybettin.");
                                yeniElSorusu();
                            }
                        }
                    });
                    builder.show();
                }


                if (vaultDeck.getStatus() == Status.BLACKJACK && vaultDeck.getSgStatus() != SgStatus.SIGORTAELI) {
                    setCaseCard(1,vaultDeck.getCard(1).getCardFullName());
                    if (player.getPlayerDeckByIndex(0).getStatus() == Status.BLACKJACK){
                        bakiye += bahis;
                        saveBakiye(bakiye);
                        bakiyeText.setText(String.valueOf(bakiye));
                        makeToast("Kasa Blackjack! Berabere.");
                        yeniElSorusu();
                    }
                    else{
                        makeToast("Kasa Blackjack! Kaybettin.");
                        yeniElSorusu();}
                }
                if (player.getPlayerDeckByIndex(0).getStatus() == Status.BLACKJACK){
                    oyunSonu(player);
                }
                if (bakiye > bahis) {
                    doubleButton.setVisibility(View.VISIBLE);
                    if (player.getPlayerDeckByIndex(0).getCard(0).getName().equals(player.getPlayerDeckByIndex(0).getCard(1).getName())) {
                        splitButton.setVisibility(View.VISIBLE);
                        splitButton.setClickable(true);
                    }
                }


                pasButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player.getPlayerDeckByIndex(0).setStatus(Status.INACTIVE);
                        oyunSonu(player);
                    }
                });


                kartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int cardIndex = player.getPlayerDeckByIndex(0).getSize();
                        kartActivty(player, 0,cardIndex);
                        if(player.getPlayerDeckByIndex(0).getStatus()== Status.INACTIVE){
                            oyunSonu(player);
                        }
                    }
                });

                doubleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bakiye -= bahis;
                        saveBakiye(bakiye);
                        bakiyeText.setText(String.valueOf(bakiye));
                        player.getPlayerDeckByIndex(0).setStatus(Status.INACTIVE);
                        bahis = (bahis * 2);
                        player.getPlayerDeckByIndex(0).addCard(gameDeck.buyCard());
                        setPlayerCard(2, player.getPlayerDeckByIndex(0).getCard(2).getCardFullName());
                        playerToplamText.setText("Toplam: " + player.getPlayerDeckByIndex(0).calculateCardTotal());
                        oyunSonu(player);
                    }
                });

                splitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bakiye -= bahis;
                        saveBakiye(bakiye);
                        bakiyeText.setText(String.valueOf(bakiye));
                        setPlayerCard(0,"");
                        setPlayerCard(1,"");
                        pasButton.setVisibility(View.GONE);
                        splitButton.setVisibility(View.INVISIBLE);
                        splitButton.setClickable(false);
                        doubleButton.setVisibility(View.GONE);
                        pasButton.setVisibility(View.GONE);
                        kartButton.setVisibility(View.GONE);
                        splitPasButton1.setVisibility(View.VISIBLE);
                        splitKartButton1.setVisibility(View.VISIBLE);
                        //playerToplamText.setVisibility(View.VISIBLE);
                        playerToplamText2.setVisibility(View.VISIBLE);


                        ArrayList<Card> cardList = new ArrayList<>();
                        cardList.add(player.getPlayerDeckByIndex(0).getCard(1));
                        player.getPlayerDeckByIndex(0).setCard(1,gameDeck.buyCard());
                        cardList.add(gameDeck.buyCard());
                        player.setPlayerDeck(new PlayerDeck(player.getPlayerDeckByIndex(0).getCards()));
                        player.setPlayerDeck(new PlayerDeck(cardList));
                        player.getPlayerDeckByIndex(0).setStatus(Status.INACTIVE);

                        setPlayerSplitedCard(0,0,player.getPlayerDeckByIndex(1).getCard(0).getCardFullName());
                        setPlayerSplitedCard(0,1,player.getPlayerDeckByIndex(1).getCard(1).getCardFullName());
                        setPlayerSplitedCard(1,0,player.getPlayerDeckByIndex(2).getCard(0).getCardFullName());
                        setPlayerSplitedCard(1,1,player.getPlayerDeckByIndex(2).getCard(1).getCardFullName());

                        playerToplamText.setText("Toplam: "+ player.getPlayerDeckByIndex(1).calculateCardTotal());
                        playerToplamText2.setText("Toplam: "+ player.getPlayerDeckByIndex(2).calculateCardTotal());

                        splitKartButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int cardIndex = player.getPlayerDeckByIndex(1).getSize();
                                kartActivty(player,1,cardIndex);
                                if(player.getPlayerDeckByIndex(1).getStatus() == Status.INACTIVE){
                                splitKartButton1.setVisibility(View.GONE);
                                splitPasButton1.setVisibility(View.GONE);
                                splitKartButton2.setVisibility(View.VISIBLE);
                                splitPasButton2.setVisibility(View.VISIBLE);}
                            }
                        });
                        splitPasButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                player.getPlayerDeckByIndex(1).setStatus(Status.INACTIVE);
                                splitKartButton1.setVisibility(View.GONE);
                                splitPasButton1.setVisibility(View.GONE);
                                splitKartButton2.setVisibility(View.VISIBLE);
                                splitPasButton2.setVisibility(View.VISIBLE);
                            }
                        });
                        splitPasButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                splitKartButton2.setVisibility(View.GONE);
                                splitPasButton2.setVisibility(View.GONE);
                                player.getPlayerDeckByIndex(2).setStatus(Status.INACTIVE);
                                oyunSonu(player);
                            }
                        });
                        splitKartButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int cardIndex = player.getPlayerDeckByIndex(2).getSize();
                                kartActivty(player,2,cardIndex);
                                if (player.getPlayerDeckByIndex(2).getStatus()== Status.INACTIVE){
                                    splitKartButton2.setVisibility(View.GONE);
                                    splitPasButton2.setVisibility(View.GONE);
                                    oyunSonu(player);
                                }
                            }
                        });


                    }
                });


            }
        });
    }

    public void oyunSonu(Player player){
        if(player.getPlayerDeckByIndex(0).getStatus() == Status.INACTIVE
                || player.getPlayerDeckByIndex(0).getStatus() == Status.BLACKJACK){
            pasButton.setVisibility(View.GONE);
            kartButton.setVisibility(View.GONE);
            splitButton.setVisibility(View.INVISIBLE);
            splitButton.setClickable(false);
            doubleButton.setVisibility(View.GONE);

            setCaseCard(1,vault.getVaultDeck().getCard(1).getCardFullName());

            vault.getVaultDeck().calculateCardTotal();
            if(vault.getVaultDeck().getCardTotal()>=17){
                vault.getVaultDeck().setStatus(Status.INACTIVE);
            }
            int i=2;
            while (vault.getVaultDeck().getStatus() == Status.ACTIVE){
                vault.getVaultDeck().addCard(gameDeck.buyCard());
                setCaseCard(i,vault.getVaultDeck().getCard(i).getCardFullName());
                i++;
                vault.getVaultDeck().calculateCardTotal();
                caseToplamText.setText("Toplam: "+ vault.getVaultDeck().calculateCardTotal());
                if(vault.getVaultDeck().getCardTotal()>=17){
                    vault.getVaultDeck().setStatus(Status.INACTIVE);
                }
            }

            if(player.getPlayerDecks().size()<=1){
                int result = player.result(0);
                if(result==0){
                    makeToast("Kaybettin");
                } else if (result==1) {
                    makeToast("Tebrikler Kazandın.");
                    bakiye+=bahis*2;
                } else if (result==2){
                    makeToast(("Berabere"));
                    bakiye+=bahis;
                }
            }else {
                    int result0 = player.result(1);
                    int result1 = player.result(2);
                if(result0==0 && result1 == 0){
                    makeToast("İki Deste de Kaybetti");
                } else if ((result0==1 && result1== 0) || (result1==1 && result0== 0) ||
                        (result0 == 2 && result1==2) ) {
                    makeToast(" Berabere.");
                    bakiye+=bahis*2;
                } else if ((result0==2 && result1==0) || (result1==2 && result0==0)){
                    makeToast("Bir deste kaybetti.");
                    bakiye+=bahis;
                } else if ((result0==2 && result1 == 1)|| (result1==2 && result0 == 1)){
                    makeToast("Bir deste kazandı");
                    bakiye+=bahis*3;
                } else if ((result0 == 1 && result1 == 1)){
                    makeToast("İki deste de kazandı");
                    bakiye+=bahis*4;
                }
            }


            caseToplamText.setText("Tolam: "+String.valueOf(vault.getVaultDeck().getCardTotal()));
            bakiyeText.setText(String.valueOf(bakiye));
            saveBakiye(bakiye);
            yeniElSorusu();

        }
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static Context getContext(){
        return context;
    }

    public static void setPlayerCard(int index, String card) {
        int resourceId = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
        playerCards[index].setImageResource(resourceId);
        playerCards[index].setVisibility(View.VISIBLE);
    }
    public static void setPlayerSplitedCard(int setIndex,int CardIndex, String card) {
        int resourceId = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
        if (setIndex == 0){
            player1Splits[CardIndex].setImageResource(resourceId);
        }
        if (setIndex == 1){
            player2Splits[CardIndex].setImageResource(resourceId);
        }

    }

    public static void setCaseCard(int index, String card) {
        int resourceId = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
        try {
            caseCards[index].setImageResource(resourceId);
            caseCards[index].setVisibility(View.VISIBLE);
        }catch (Exception e){

        }

    }

    public void yeniElSorusu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Yeni el?")
                .setMessage("Yeni el başlatmak istiyor musunuz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Kullanıcı "Evet" dediğinde yapılacak işlemleri buraya ekleyin
                        // Örneğin, yeni el başlatma kodu

                        playerToplamText.setText("Toplam: 0");
                        playerToplamText.setVisibility(View.GONE);
                        caseToplamText.setText("Toplam: 0");
                        caseToplamText.setVisibility(View.GONE);
                        playerToplamText2.setText("Toplam: 0");
                        playerToplamText2.setVisibility(View.GONE);
                        splitPasButton1.setVisibility(View.GONE);
                        splitKartButton1.setVisibility(View.GONE);
                        splitPasButton2.setVisibility(View.GONE);
                        splitKartButton2.setVisibility(View.GONE);



                        for(int i = 0 ; i< 7 ; i++){
                            setPlayerCard(i, "");
                            setPlayerSplitedCard(0,i, "");
                            setPlayerSplitedCard(1,i, "");
                            if(i<5){
                                setCaseCard(i,"");
                            }
                        }
                        oynaButton.callOnClick();
                        dialog.dismiss(); // Dialog'u kapat
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Kullanıcı "Hayır" dediğinde yapılacak işlemleri buraya ekleyin
                        onNoAction(dialog);
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onNoAction(dialog);
                    }
                })
                .show();
    }


    public static void createPlayer() {
        players.clear();

        for (int i = 1; i <= 1; i++) {
            String name = "Oyuncu";
            players.add(new Player(name));
        }
    }
    // Bu metodu çağırarak SharedPreferences üzerine skoru kaydedebilirsiniz
    private void saveBakiye(double bakiye) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("Bakiye", (float) bakiye); // double değeri kaydetmek için float'a çeviriyoruz
        editor.apply();
    }

    // Bu metodu çağırarak SharedPreferences'den skoru geri yükleyebilirsiniz
    private int loadBakiye() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // İkinci parametrede varsayılan değeri belirleyebilirsiniz, burada 500 olarak belirledik

        return (int) sharedPreferences.getFloat("Bakiye", 500);

    }
    private void onNoAction(DialogInterface dialog) {
        for(int i = 0 ; i< 7 ; i++){
            setPlayerCard(i,"");
            setPlayerSplitedCard(0,i,"");
            setPlayerSplitedCard(1,i,"");
            if(i<5){
                setCaseCard(i,"");
            }
        }
        caseToplamText.setVisibility(View.GONE);
        playerToplamText.setVisibility(View.GONE);
        playerToplamText2.setVisibility(View.GONE);
        doubleButton.setVisibility(View.GONE);
        pasButton.setVisibility(View.GONE);
        kartButton.setVisibility(View.GONE);
        splitButton.setVisibility(View.INVISIBLE);
        splitButton.setClickable(false);
        oynaButton.setVisibility(View.VISIBLE);
        upButton.setVisibility(View.VISIBLE);
        downButton.setVisibility(View.VISIBLE);

        dialog.dismiss(); // Dialog'u kapat
    }
    private void beginning(){
        oynaButton = findViewById(R.id.playButton);
        kartButton = findViewById(R.id.karCekButton);
        pasButton = findViewById(R.id.pasButton);
        doubleButton = findViewById(R.id.doubleButton);
        splitButton = findViewById(R.id.splitButton);
        splitPasButton1 = findViewById(R.id.splitPasButton1);
        splitKartButton1 = findViewById(R.id.splitKartButton1);
        splitPasButton2 = findViewById(R.id.splitPasButton2);
        splitKartButton2 = findViewById(R.id.splitKartButton2);
        downButton = findViewById(R.id.downButton);
        upButton = findViewById(R.id.upButton);
        upButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yesilRenk));
        downButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.kirmiziRenk));
        //Texts
        bahisText = findViewById(R.id.bahisText);
        caseToplamText = findViewById(R.id.caseToplamText);
        playerToplamText = findViewById(R.id.playerToplamText);
        playerToplamText2 = findViewById(R.id.playerToplamText2);
        bakiyeText = findViewById(R.id.bakiyeText);
        //Images
        playerCards = new ImageView[7];
        playerCards[0] = findViewById(R.id.playerFirstCard);
        playerCards[1] = findViewById(R.id.playerSecondCard);
        playerCards[2] = findViewById(R.id.playerThirdCard);
        playerCards[3] = findViewById(R.id.playerFourthCard);
        playerCards[4] = findViewById(R.id.playerFifthCard);
        playerCards[5] = findViewById(R.id.playerSixthCard);
        playerCards[6] = findViewById(R.id.playerSeventhCard);
        caseCards = new ImageView[5];
        caseCards[0] = findViewById(R.id.caseFirstCard);
        caseCards[1] = findViewById(R.id.caseSecondCard);
        caseCards[2] = findViewById(R.id.caseThirdCard);
        caseCards[3] = findViewById(R.id.caseFourthCard);
        caseCards[4] = findViewById(R.id.caseFifthCard);
        player1Splits = new ImageView[7];
        player1Splits[0] = findViewById(R.id.split1First);
        player1Splits[1] = findViewById(R.id.split1Second);
        player1Splits[2] = findViewById(R.id.split1Third);
        player1Splits[3] = findViewById(R.id.split1Fourth);
        player1Splits[4] = findViewById(R.id.split1Fifth);
        player1Splits[5] = findViewById(R.id.split1Sixth);
        player1Splits[6] = findViewById(R.id.split1Seventh);
        player2Splits = new ImageView[7];
        player2Splits[0] = findViewById(R.id.split2First);
        player2Splits[1] = findViewById(R.id.split2Second);
        player2Splits[2] = findViewById(R.id.split2Third);
        player2Splits[3] = findViewById(R.id.split2Fourth);
        player2Splits[4] = findViewById(R.id.split2Fifth);
        player2Splits[5] = findViewById(R.id.split2Sixth);
        player2Splits[6] = findViewById(R.id.split2Seventh);

    }

    private void kartActivty(Player player,int deckIndex, int cardIndex){
        player.getPlayerDeckByIndex(deckIndex).addCard(gameDeck.buyCard());
        doubleButton.setVisibility(View.GONE);
        splitButton.setVisibility(View.INVISIBLE);
        splitButton.setClickable(false);

                if(deckIndex==0){
                setPlayerCard(cardIndex, player.getPlayerDeckByIndex(deckIndex).getCard(cardIndex).getCardFullName());
                    playerToplamText.setText("Toplam: " + player.getPlayerDeckByIndex(deckIndex).calculateCardTotal());
                }
                else {
                    setPlayerSplitedCard(deckIndex-1,cardIndex,player.getPlayerDeckByIndex(deckIndex).getCard(cardIndex).getCardFullName());
                    playerToplamText.setText("Toplam: " + player.getPlayerDeckByIndex(1).calculateCardTotal());
                    playerToplamText2.setText("Toplam: " + player.getPlayerDeckByIndex(2).calculateCardTotal());
                }

                player.getPlayerDeckByIndex(deckIndex).calculateCardTotal();
        if (player.getPlayerDeckByIndex(deckIndex).getCardTotal() > 21) {
            if(player.getPlayerDeckByIndex(deckIndex).calculateCardTotal().contains("ya da") &&
                    player.getPlayerDeckByIndex(deckIndex).getCardTotal() > 31) {
                player.getPlayerDeckByIndex(deckIndex).setStatus(Status.INACTIVE);
            } else {
                player.getPlayerDeckByIndex(deckIndex).setStatus(Status.INACTIVE);
            }
        }

    }

}