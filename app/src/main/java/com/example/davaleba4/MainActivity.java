package com.example.davaleba4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView myZodiacList;
    Button logout;
    ImageButton downArrow;

    public void setDownArrow(View view) {
        if(logout.getVisibility() == View.INVISIBLE){
            logout.setVisibility(View.VISIBLE);
        }else {
            logout.setVisibility(View.INVISIBLE);
        }
    }



    String[] zodiacNames = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius",
    "Capricorn", "Aquarius", "Pisces"};
    String[] zodiacMonths = {"March 21 - April 19", "April 20 - May 20", "May 21 - June 21", "June 22 - July 22",
    "July 23 - August 22", "August 23 - September 22", "September 23 - October 23", "October 24 - November 21",
    "November 22 - December 21", "December 22 - January 19", "January 20 - February 18", "February 19 - March 20"};
    int[] images = {R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer, R.drawable.leo, R.drawable.virgo,
    R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        logout = findViewById(R.id.logout);
        downArrow = findViewById(R.id.down_arrow);

        myZodiacList = findViewById(R.id.myZodiacList);

        MyAdapter myAdapter = new MyAdapter();
        myZodiacList.setAdapter(myAdapter);


        myZodiacList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
            ImageView zodiacImage = (ImageView) view.findViewById(R.id.zodiacImage);
            TextView zodiacName = view.findViewById(R.id.zodiacName);
            TextView zodiacDescription = view.findViewById(R.id.zodiacDescription);

            zodiacImage.setImageResource(images[position]);
            zodiacName.setText(zodiacNames[position]);
            zodiacDescription.setText(zodiacMonths[position]);

            return view;
        }
    }
}