package ie.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import ie.app.R;
import ie.app.main.DonationApp;

public class Base extends AppCompatActivity
{
    public DonationApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
//        app.dbManager.open();
//        app.donations.setTotalDonated(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        app.donations.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);
        MenuItem reset = menu.findItem(R.id.menuReset);
        if(app.donations.isEmpty())
        {
            report.setEnabled(true);
            reset.setEnabled(false);
        }
        else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
//        if(this instanceof Report){
//            donate.setVisible(false);
//            if(!app.dbManager.getAll().isEmpty())
//            {
//                report.setVisible(true);
//                reset.setEnabled(true);
//            }
//        }
        if(this instanceof MainActivity){
            donate.setVisible(false);
            if(!app.donations.isEmpty())
            {
                report.setVisible(true);
                reset.setEnabled(true);
            }
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(true);
        }
        return true;
    }
    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }
    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, MainActivity.class));
    }
    public void reset(MenuItem item) {
        app.donations.clear();
        app.totalDonated = 0;
        report(item);
        TextView amountTotal = (TextView) findViewById(R.id.totalSoFar);
        amountTotal.setText("$" + app.totalDonated);
    }


}

