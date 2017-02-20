package pikmy.com.pikmybeta;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import ua.naiksoftware.stomp.client.StompClient;

public class AddPikActivity extends AppCompatActivity implements OnItemSelectedListener {

    private StompClient mStompClient;
    public static final String TAG = "StompClient";
    Button btn_add_pik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pik);

        btn_add_pik = (Button) findViewById(R.id.btn_add_pik);
        btn_add_pik.setOnClickListener(e -> new LongOperation().execute(""));

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.list_channels);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        ArrayList<String> list_channels = new ArrayList<>();
        list_channels.add("StartUpWeekendOlsztyn");
        list_channels.add("InfoShare");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_channels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
