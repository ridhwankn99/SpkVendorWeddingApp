package com.ridhwankn.spkapp;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.ActivityResultVendorWeddingBinding;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ResultVendorWeddingActivity extends AppCompatActivity {
    private ActivityResultVendorWeddingBinding binding;
    private ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();
    private double price = 0.0;
    private String vendorName = "";
    private String nameGedung = "";
    private int ttlInvitation = 0;
    private String noWa = "";
    private String location = "";
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private static ProgressDialog progressDialog;
    // declaring width and height
    // for our PDF file.
    int pageHeight = 1000;
    int pagewidth = 700;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, scaledbmp, scaledbmpBottom;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        initToolBar();

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.wedding1);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
        scaledbmpBottom = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // checking our permissions.
        if (checkPermission()) {
//            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }
    }

    private void initToolBar(){
        binding.top.tvTitle.setText("Hasil Pendukung Keputusan");
        binding.top.ivBack.setOnClickListener(v->{
            finish();
        });
    }

    private void initData(){
        Intent mData = getIntent();
        price = mData.getDoubleExtra("price",0);
        vendorName = mData.getStringExtra("vendorName");
        nameGedung = mData.getStringExtra("nameGedung");
        ttlInvitation = mData.getIntExtra("ttlInvitation", 0);
        databaseReference.child("vendor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(vendorName)){
                    noWa = snapshot.child(vendorName).child("noWa").getValue(String.class);
                    location = snapshot.child(vendorName).child("location").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        list = mData.getParcelableArrayListExtra("data");
    }

    private void initView(){
        String text = "Sistem pendukung keputusan dengan metode weight product memutuskan vendor terbaik yaitu " + vendorName
                +". Menggunakan gedung "+nameGedung+" dengan harga Rp. "
                + formatCurrency(price)
                + " Total jumlah undangan yaitu " + ttlInvitation + " Tamu.";
        binding.tvText.setText(text);

        binding.btnPdf.setOnClickListener( v-> {
            generatePDF();
        });
    }

    public static String formatCurrency(double amount){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(amount);
        return formattedNumber;
    }

    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledbmp, 56, 40, paint);
        canvas.drawBitmap(scaledbmpBottom, 500, 800, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.black));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
//        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Hasil Sistem Pendukung Keputusan Menggunakan Metode Weighted Product", 90, 320, title);

        canvas.drawText("Nama Vendor", 160, 360, title);
        canvas.drawText(vendorName, 400, 360, title);

        canvas.drawText("venue", 160, 390, title);
        canvas.drawText(nameGedung, 400, 390, title);

        canvas.drawText("Price", 160, 420, title);
        canvas.drawText("Rp. "+formatCurrency(price), 400, 420, title);

        canvas.drawText("Jumlah Undangan", 160, 450, title);
        canvas.drawText(String.valueOf(ttlInvitation)+" Tamu", 400, 450, title);

        canvas.drawText("No Whatsapp", 160, 480, title);
        canvas.drawText(noWa, 400, 480, title);

        canvas.drawText("Lokasi", 160, 510, title);
        canvas.drawText(location, 400, 510, title);
        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.


        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "/SpkWedding.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF Saved Successfully", Toast.LENGTH_SHORT).show();

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(ResultVendorWeddingActivity.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }


    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(ResultVendorWeddingActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        return progressDialog;
    }

    public void dismisDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}