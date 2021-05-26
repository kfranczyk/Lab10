package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class ZadanieAsynchroniczne2 extends AsyncTask<Integer,Integer,Void> {
    WeakReference<Button> przyciskStartRef, przyciskCancelRef;
    WeakReference<ProgressBar> pasekPostepuRef;
    int liczbaPowtorzen,czasPauzy,postep;

    public ZadanieAsynchroniczne2(Button _przyciskStart, Button _przyciskCancel, ProgressBar _pasekPostepu) {
        przyciskStartRef = new WeakReference<>(_przyciskStart);
        przyciskCancelRef = new WeakReference<>(_przyciskCancel);
        pasekPostepuRef = new WeakReference<>(_pasekPostepu);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        liczbaPowtorzen = integers[0];
        czasPauzy = integers[1];

        for(int i=0;i<liczbaPowtorzen;i++){
            if(isCancelled())
                break;

            try{
                Thread.sleep(czasPauzy);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            postep = ((i+1)*100) /liczbaPowtorzen;
            publishProgress(postep);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        przyciskStartRef.get().setEnabled(true);
        przyciskCancelRef.get().setEnabled(false);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pasekPostepuRef.get().setProgress(values[0]);
    }

    @Override
    protected void onCancelled() {
        przyciskStartRef.get().setEnabled(true);
        przyciskCancelRef.get().setEnabled(false);
    }
}
