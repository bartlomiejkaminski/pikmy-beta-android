package pikmy.com.pikmybeta;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static pikmy.com.pikmybeta.R.id.container;

/**
 * Created by Bartek on 17.02.2017.
 */

public class Add extends Fragment{

    Button btn_add_channel, btn_add_pik;

    public Add() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);

        btn_add_channel = (Button) view.findViewById(R.id.btn_add_channel);
        btn_add_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AddChannel newFragment = new AddChannel();
//                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.activity_second, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

                Intent IntentChannel = new Intent(getActivity(), AddChannelActivity.class);
                startActivity(IntentChannel);
            }
        });

        btn_add_pik = (Button) view.findViewById(R.id.btn_add_pik);
        btn_add_pik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), AddPikActivity.class);
                startActivity(myIntent);
            }
        });
        return view;
    }
}
