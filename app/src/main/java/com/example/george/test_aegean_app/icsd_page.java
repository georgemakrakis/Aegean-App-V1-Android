package com.example.george.test_aegean_app;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class icsd_page extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_icsd_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//energopoiisi tou back button
        //underlining startp page texview
        TextView txt= (TextView)findViewById(R.id.start_page);
        txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //underlining search page texview
        TextView txt2= (TextView)findViewById(R.id.Search);
        txt2.setPaintFlags(txt2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_icsd_page, menu);
        return true;
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException//taken from http://stackoverflow.com/questions/12889608/how-to-open-pdf-file-in-android-from-the-assets-folder?lq=1
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }
    public void eclassClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://eclass.icsd.aegean.gr"));
        startActivity(browserIntent);
    }
    public void icarusClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://icarus-icsd.aegean.gr/"));
        startActivity(browserIntent);
    }
    public void mailClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webmail.aegean.gr/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fwebmail.aegean.gr%2fowa"));
        startActivity(browserIntent);
    }
    public void eudoxusClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://eudoxus.gr/"));
        startActivity(browserIntent);
    }
    public void startPageClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.icsd.aegean.gr/"));
        startActivity(browserIntent);
    }
    public void searchPageClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }
    public void MscClick(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.msc.icsd.aegean.gr"));
        startActivity(browserIntent);
    }
    public void scheduleClick(View v)
    {
        AssetManager assetManager = getAssets();//access to assets folder

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "icsd_Schedule.pdf");//gets directory
        try
        {
            in = assetManager.open("icsd_Schedule.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);//copies file to the output
            in.close();
            in=null;
            out.flush();
            out.close();
            out=null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + getFilesDir() + "/icsd_Schedule.pdf"), "application/pdf");

        startActivity(intent);//starts pdf viewer
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id==android.R.id.home) {
            finish();//na mporei to back button na paei pisw
        }

        return super.onOptionsItemSelected(item);
    }
}
