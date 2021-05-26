package pollub.ism.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pollub.ism.lab10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding okno = null;
    private ZadanieAsynchroniczne2 zadAsyn2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okno = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(okno.getRoot());

        okno.pasekPostepu.setProgress(0);
        okno.pasekPostepu.setMax(100);
        okno.przycisk3.setEnabled(true);
        okno.przycisk4.setEnabled(false);
    }

    public void przycisk1(View view) {
        new ZadanieAsynchroniczne1(okno.poleTekstowe1, okno.przycisk1).execute();
        okno.poleTekstowe1.setText(R.string.working);
    }

    public void przycisk2(View view) {
        new ZadanieAsynchroniczne1(okno.poleTekstowe2,okno.przycisk2).execute();
        okno.poleTekstowe2.setText(R.string.working);
    }

    public void przycisk3(View view) {
        okno.przycisk3.setEnabled(false);
        int powtorzenia = Integer.parseInt(okno.poleEdycyjne1.getText().toString());
        int pauza = Integer.parseInt(okno.poleEdycyjne2.getText().toString());
        zadAsyn2 = new ZadanieAsynchroniczne2( okno.przycisk3, okno.przycisk4, okno.pasekPostepu);
        zadAsyn2.execute(powtorzenia,pauza);
        okno.przycisk4.setEnabled(true);
    }

    public void przycisk4(View view) {
        zadAsyn2.cancel(true);
    }
}