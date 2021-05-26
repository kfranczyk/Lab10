package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class ZadanieAsynchroniczne1 extends AsyncTask<Void, Void,String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<Button> przycisk;

    public ZadanieAsynchroniczne1(TextView tv, Button _przycisk) {
        mTextView = new WeakReference<>(tv);
        przycisk = new WeakReference<>(_przycisk);
        przycisk.get().setEnabled(false);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random rd = new Random();
        int x = rd.nextInt(11);
        int s= x*200;

        try{
            Thread.sleep(s);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "Zadanie zakończone po " + s +" ms ";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        przycisk.get().setEnabled(true);
    }
}
