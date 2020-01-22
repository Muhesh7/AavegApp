package com.example.aaveg2020.login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.api.AavegApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseHostel extends Fragment {
    TextView agate, bloodstone, opal, cobalt, azurite;
    Button hostelSelect;
    String chosenHostel;
    CardView a, b, c, o, az;
    LinearLayout background;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    CardView hostelBanner;
    ImageView agateBack,azuriteBack,bloodstoneBack,opalBack,cobaltBack;


    public ChooseHostel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choose_hostel, container, false);
        agate = v.findViewById(R.id.agatetext);
        bloodstone = v.findViewById(R.id.bloodstonetext);
        opal = v.findViewById(R.id.opaltext);
        cobalt = v.findViewById(R.id.cobalttext);
        azurite = v.findViewById(R.id.azuritetext);
        hostelSelect = v.findViewById(R.id.chooseButton);
        background = v.findViewById(R.id.hostelback);
        a = v.findViewById(R.id.agate);
        b = v.findViewById(R.id.bloodstone);
        o = v.findViewById(R.id.opal);
        c = v.findViewById(R.id.cobalt);
        az = v.findViewById(R.id.azurite);
        agateBack = v.findViewById(R.id.agatecard);
        bloodstoneBack = v.findViewById(R.id.bloodstonecard);
        opalBack = v.findViewById(R.id.opalcard);
        cobaltBack = v.findViewById(R.id.cobaltcard);
        azuriteBack = v.findViewById(R.id.azuritecard);
        hostelBanner=v.findViewById(R.id.hostelbanner);
        hostelBanner.setBackgroundResource(R.drawable.cardbanner);
        hostelSelect = v.findViewById(R.id.chooseButton);
        chosenHostel = "";

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackgroundImage();
                chosenHostel = agate.getText().toString();
                agateBack.setImageResource(R.drawable.agatecard);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackgroundImage();

                chosenHostel = bloodstone.getText().toString();
                bloodstoneBack.setBackgroundResource(R.drawable.bloodstonecard);

            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackgroundImage();

                chosenHostel = opal.getText().toString();
                opalBack.setBackgroundResource(R.drawable.opalcard);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackgroundImage();

                chosenHostel = cobalt.getText().toString();
                cobaltBack.setBackgroundResource(R.drawable.cobaltcard);
            }
        });
        az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackgroundImage();

                chosenHostel = azurite.getText().toString();
               azuriteBack.setBackgroundResource(R.drawable.azuritecard);
            }
        });
        hostelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chosenHostel.isEmpty()) {
                    setHostel(chosenHostel);

                } else {
                    Toast.makeText(getActivity(), "Please choose a hostel", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    private void setHostel(final String chosenHostel) {
        Toast.makeText(getActivity(), chosenHostel, Toast.LENGTH_LONG).show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aaveg.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AavegApi api = retrofit.create(AavegApi.class);

        Call<LoginBody> call = api.setHostel(chosenHostel, UserUtils.APIToken);

        call.enqueue(new Callback<LoginBody>() {

            @Override
            public void onResponse(Call<LoginBody> call, Response<LoginBody> response) {
                pref= getActivity().getSharedPreferences("Aaveg2020", MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("hostel",chosenHostel);
                editor.apply();
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<LoginBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void clearBackgroundImage(){
        int x=0;
        switch(chosenHostel){
            case "Agate": agateBack.setImageResource(x);        Toast.makeText(getActivity(),"lol",Toast.LENGTH_SHORT).show();
                break;
            case "Azurite": azuriteBack.setImageResource(x);  Toast.makeText(getActivity(),"lol",Toast.LENGTH_SHORT).show();break;
            case "Bloodstone":bloodstoneBack.setImageResource(x); Toast.makeText(getActivity(),"lol",Toast.LENGTH_SHORT).show();break;
            case "Cobalt":cobaltBack.setImageResource(x); Toast.makeText(getActivity(),"lol",Toast.LENGTH_SHORT).show();break;
            case "Opal":opalBack.setImageResource(x); Toast.makeText(getActivity(),"lol",Toast.LENGTH_SHORT).show();break;

        }
    }

}
