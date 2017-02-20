package pikmy.com.pikmybeta;

import android.os.AsyncTask;
import android.util.Log;

import org.java_websocket.WebSocket;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

/**
 * Created by Bartek on 19.02.2017.
 */

public class LongOperation extends AsyncTask<String, Void, String> {

    private StompClient mStompClient;
    String TAG = "LongOperation";

    @Override
    protected String doInBackground(String... params) {

        mStompClient = Stomp.over(WebSocket.class, "ws://pikmybeta.x3pg3pxqri.eu-central-1.elasticbeanstalk.com/app/hello/websocket");
        mStompClient.connect();

        mStompClient.topic("/topic/greetings").subscribe(topicMessage -> {
            Log.d(TAG, topicMessage.getPayload());
        });

        mStompClient.send("/app/hello", "My first STOMP message!").subscribe();


        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {

                case OPENED:
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA Działam");
                    Log.d(TAG, "Stomp connection opened");
                    break;

                case ERROR:
                    System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn Błąd");
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    break;

                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {

    }
}
