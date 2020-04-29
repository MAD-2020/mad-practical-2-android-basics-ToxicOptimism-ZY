package sg.edu.np.WhackAMole;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declare Variables
    private static final String TAG = "Whack-A-Mole"; //final means locked at run time

    private TextView resultTextView;
    private Button holeButton1;
    private Button holeButton2;
    private Button holeButton3;
    private List<Button> holeButtonList = new ArrayList<>();
    private Integer randomLocation;

    //Pre-Instantiation object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        holeButton1 = (Button)findViewById(R.id.holeButton1);
        holeButton2 = (Button)findViewById(R.id.holeButton2);
        holeButton3 = (Button)findViewById(R.id.holeButton3);
        holeButtonList.add(holeButton1);
        holeButtonList.add(holeButton2);
        holeButtonList.add(holeButton3);
        Log.v(TAG, "Finished Pre-initialisation."); //verbose filter for easy reading
    }

    //Loaded and usable
    @Override
    protected void onStart() {
        super.onStart();

        setNewMole();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonPressed = (Button) v;
                Log.v(TAG,"Reached");
                switch (holeButtonList.indexOf(buttonPressed)) {
                    case 0:
                        Log.v(TAG,"Button Left Clicked!");
                        break;
                    case 1:
                        Log.v(TAG,"Button Middle Clicked!");
                        break;
                    case 2:
                        Log.v(TAG,"Button Right Clicked!");
                        break;
                    default:
                        Log.v(TAG,"Unknown Button pressed. Found within ButtonList");
                }

                Integer score = Integer.parseInt(resultTextView.getText().toString());
                switch (buttonPressed.getText().toString()) {
                    case "0":
                        Log.v(TAG,"Missed, score deducted!");
                        score -= 1;
                        resultTextView.setText(score.toString());
                        break;
                    case "*":
                        Log.v(TAG,"Hit, score added!");
                        score += 1;
                        resultTextView.setText(score.toString());
                        break;
                    default:
                        Log.v(TAG,"Unknown button pressed, no case for it's text set.");
                }
                resetMole();
                setNewMole();
            }
        };
        holeButton1.setOnClickListener(listener);
        holeButton2.setOnClickListener(listener);
        holeButton3.setOnClickListener(listener);

        Log.v(TAG,"Starting Gui.");
    }

    public void setNewMole()
    {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        holeButtonList.get(randomLocation).setText("*");
    }

    public void resetMole()
    {
        holeButtonList.get(randomLocation).setText("0");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"Resuming.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"Pausing.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"Destroying.");
    }
}

