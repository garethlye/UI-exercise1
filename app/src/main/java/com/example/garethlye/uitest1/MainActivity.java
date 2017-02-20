package com.example.garethlye.uitest1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Data.FetchData;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView_countries)
    ListView mListView;
    private String[][] countryList;

    private List<CountryData> mCountryDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchData fetchData = new FetchData(this);
        countryList = fetchData.getCountryList();
        for(int i = 0; i < 3 ; i++){
            for (int j = 1; j < 8 ; j++){
                if(countryList[i][j] != null)
                mCountryDataList.add(new CountryData(countryList[i][0],countryList[i][j] , false));
                Log.w("Country List","Country : " + countryList[i][j]);
            }
        }
        /**mCountryDataList.add(new CountryData("Indonesia", "LOL", true));
        mCountryDataList.add(new CountryData("Malaysia", "Johor", false));
        mCountryDataList.add(new CountryData("1234", "Ipoh", true));
        /**mCountryDataList.add(new CountryData("Malaysia", "Melaka",false));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", true));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", false));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", true));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", false));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", true));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", false));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", true));
        mCountryDataList.add(new CountryData("Malaysia", "Melaka", true));**/
        ButterKnife.bind(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.listview_layout, mCountryDataList);
        mListView.setAdapter(arrayAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l) {

                ImageView checkboxIcon = (ImageView)view.findViewById(R.id.checkbox_image);

                if(mCountryDataList.get(i).getStatus()){
                    mCountryDataList.get(i).setCheckboxImage(R.drawable.ic_checkbox);
                    checkboxIcon.setImageResource(mCountryDataList.get(i).getCheckboxImage());
                    mCountryDataList.get(i).setStatus(false);
                }
                else {
                    mCountryDataList.get(i).setCheckboxImage(R.drawable.ic_checkbox_selected);
                    checkboxIcon.setImageResource(mCountryDataList.get(i).getCheckboxImage());
                    mCountryDataList.get(i).setStatus(true);

                }
            }
        });

    }

    public class ArrayAdapter extends android.widget.ArrayAdapter {

        private List<CountryData> mCountryDataList;
        private int resource;
        private LayoutInflater mInflater;

        private ArrayAdapter(final Context context, final int resource, final List<CountryData> objects) {
            super(context, resource, objects);
            mCountryDataList = objects;
            this.resource = resource;
            mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }



        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.listview_layout, null);
            if(convertView == null){
                Log.w("lol","it was null :(");
                convertView = mInflater.inflate(resource, null);
            }

            ImageView checkboxIcon;
            TextView countryName = (TextView)convertView.findViewById(R.id.countryName);
            TextView city = (TextView)convertView.findViewById(R.id.cityName);

            checkboxIcon = (ImageView)convertView.findViewById(R.id.checkbox_image);
            checkboxIcon.setImageResource(mCountryDataList.get(position).getCheckboxImage());

            city.setText(mCountryDataList.get(position).getCityName());
            Log.w("indeed", "Value of position is :" +position);

            countryName.setText(mCountryDataList.get(position).getCountryName());
            if(position > 0 && mCountryDataList.get(position).getCountryName().equals("Malaysia"))
                countryName.setVisibility(View.GONE);
            else if(position > 8 && mCountryDataList.get(position).getCountryName().equals("Indonesia"))
                countryName.setVisibility(View.GONE);
            Log.w("hi", "-"+mCountryDataList.get(position).getCountryName()+"-");
            //if(position == 10)
            //    if(mCountryDataList.get(position -1).getCountryName() == "Singapore")
            //        countryName.setVisibility(View.INVISIBLE);

            return convertView;
        }

    }

}
