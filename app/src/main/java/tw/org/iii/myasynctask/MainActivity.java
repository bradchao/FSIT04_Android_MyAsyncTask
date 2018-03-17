package tw.org.iii.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

    }

    public void test1(View view) {
        Log.v("brad", "before");
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Brad", "Tony","Mary");
        Log.v("brad", "after");
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... names) {
            Log.v("brad", "doInBackground");

            for (String name : names){
                Log.v("brad", "Hello, " + name);
                //tv.setText(name);

                int rand = (int)(Math.random()*49+1);
                publishProgress(rand, rand*10);

                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return "OK";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.v("brad", "onProgressUpdate");
            tv.setText(values[0] + ":" + values[1]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("brad", "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.v("brad", "onPostExecute:" + result);
            tv.setText(result);
        }



        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
            Log.v("brad", "onCancelled(Void aVoid):" + result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.v("brad", "onCancelled()");
        }
    }

}
