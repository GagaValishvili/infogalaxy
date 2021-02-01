package com.example.davaleba4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ListView myZodiacList;
    Button logout;
    ImageButton downArrow, close;
    TextView zodiacText;
    FirebaseAuth myFirebaseAuth;
    FirebaseFirestore myFireStore;
    DocumentReference myReference;




    public void logOut(View view) {
        myFirebaseAuth.signOut();
        Intent sign_out = new Intent(MainActivity.this, Login.class);
        startActivity(sign_out);

    }

    public void closeTextView(View view) {
        close.setVisibility(View.INVISIBLE);
        zodiacText.setVisibility(View.INVISIBLE);
        myZodiacList.setVisibility(View.VISIBLE);
    }




    String[] zodiacNames = {"ვერძი", "კურო", "ტყუპები", "კირჩხიბი", "ლომი", "ქალწული", "სასწორი", "მორიელი", "მშვილდოსანი",
    "თხისრქა", "მერწყული", "თევზები"};
    String[] zodiacMonths = {"მარტი 21 - აპრილი 19", "აპრილი 20 - მაისი 20", "მაისი 21 - ივნისი 21", "ივნისი 22 - ივლისი 22",
    "ივლისი 23 - აგვისტო 22", "აგვისტო 23 - სექტემბერი 22", "სექტემბერი 23 - ოქტომბერი 23", "ოქტომბერი 24 - ნოემბერი 21",
    "ნოემბერი 22 - დეკემბერი 21", "დეკემბერი 22 - იანვარი 19", "იანვარი 20 - თებერვალი 18", "თებერვალი 19 - მარტი 20"};
    int[] images = {R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer, R.drawable.leo, R.drawable.virgo,
    R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        myFirebaseAuth = FirebaseAuth.getInstance();
        myFireStore = FirebaseFirestore.getInstance();
        myReference = myFireStore.collection("zodiac").document("9gx1txOHUW25yPhgH2Af");

        close = findViewById(R.id.closeBtn);
        zodiacText = findViewById(R.id.aboutZodiacText);
        logout = findViewById(R.id.logout);
        downArrow = findViewById(R.id.down_arrow);

        myZodiacList = findViewById(R.id.myZodiacList);

        MyAdapter myAdapter = new MyAdapter();
        myZodiacList.setAdapter(myAdapter);


        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logout.getVisibility() == View.INVISIBLE){
                    logout.setVisibility(View.VISIBLE);
                }else {
                    logout.setVisibility(View.INVISIBLE);
                }
            }
        });

        myReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot dSnap = task.getResult();
                    String aries = dSnap.getString("Aries");
                    String taurus = dSnap.getString("Taurus");
                    String gemini = dSnap.getString("Gemini");
                    String cancer = dSnap.getString("Cancer");
                    String leo = dSnap.getString("Leo");
                    String virgo = dSnap.getString("Virgo");
                    String libra = dSnap.getString("Libra");
                    String scorpio = dSnap.getString("Scorpio");
                    String sagittarius = dSnap.getString("Sagittarius");
                    String capricorn = dSnap.getString("Capricorn");
                    String aquarius = dSnap.getString("Aquaris");
                    String pisces = dSnap.getString("Pisces");

                    myZodiacList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            close.setVisibility(View.VISIBLE);
                            zodiacText.setVisibility(View.VISIBLE);
                            myZodiacList.setVisibility(View.INVISIBLE);

                            if(position == 0){
                                zodiacText.setText(aries);
                            }else if(position == 1){
                                zodiacText.setText(taurus);
                            }else if(position == 2){
                                zodiacText.setText(gemini);
                            }else if(position == 3){
                                zodiacText.setText(cancer);
                            }else if(position == 4){
                                zodiacText.setText(leo);
                            }else if(position == 5){
                                zodiacText.setText(virgo);
                            }else if(position == 6){
                                zodiacText.setText(libra);
                            }else if(position == 7){
                                zodiacText.setText(scorpio);
                            }else if(position == 8){
                                zodiacText.setText(sagittarius);
                            }else if(position == 9){
                                zodiacText.setText(capricorn);
                            }else if(position == 10){
                                zodiacText.setText(aquarius);
                            }else if(position == 11){
                                zodiacText.setText(pisces);
                            }
                        }
                    });
                }
            }
        });
    }







    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_view_look,null);
            ImageView zodiacImage = view.findViewById(R.id.zodiacImage);
            TextView zodiacName = view.findViewById(R.id.zodiacName);
            TextView zodiacDescription = view.findViewById(R.id.zodiacDescription);

            zodiacImage.setImageResource(images[position]);
            zodiacName.setText(zodiacNames[position]);
            zodiacDescription.setText(zodiacMonths[position]);

            return view;
        }
    }
}