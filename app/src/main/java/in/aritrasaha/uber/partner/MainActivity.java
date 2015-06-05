package in.aritrasaha.uber.partner;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


/**
 * @author aritra
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.sample_text)
    private TextView textView;

    @Inject
    private Login login;

    @Override
    protected void onResume() {
        super.onResume();

        textView.setText("Anuj Saha");

        login.execute("username", "password", textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
