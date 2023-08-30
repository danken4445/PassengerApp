package com.example.passengeractivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class ViewQrCodeActivity extends AppCompatActivity {
    String Pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qr_code);

        // Get the passenger ID from the intent
        Pid = getIntent().getStringExtra("Pid");
        // Generate the QR code for the passenger ID
        Bitmap qrCodeBitmap = generateQRCode(Pid);

        // Load the QR code image into the ImageView
        ImageView qrCodeImageView = findViewById(R.id.qrCodeImageView);
        qrCodeImageView.setImageBitmap(qrCodeBitmap);

        // You can also add functionality here, such as handling clicks on the QR code
        qrCodeImageView.setOnClickListener(v -> {
            // Add code to perform an action when the QR code is clicked
            Toast.makeText(ViewQrCodeActivity.this, "QR Code Clicked!", Toast.LENGTH_SHORT).show();
        });
    }

    private Bitmap generateQRCode(String data) {
        try {
            // Create a QRCodeWriter
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Encode the data as a QR code
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // Create a Bitmap from the BitMatrix
            Bitmap qrCodeBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    qrCodeBitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            return qrCodeBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
