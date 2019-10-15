package com.android.farmstudio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModel;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class visualization extends AppCompatActivity {
Button btn;
ImageView img;
TextView tt;
    public Bitmap selectedImage;
    public int RESULT_LOAD_IMG=999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);
        btn = findViewById(R.id.button6);
        tt=findViewById(R.id.textView5);
        img=findViewById(R.id.imageView3);
        FirebaseLocalModel localModel = new FirebaseLocalModel.Builder("my_local_model")
                .setAssetFilePath("manifest.json")
                .build();
        FirebaseModelManager.getInstance().registerLocalModel(localModel);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengllery();
            }
        });


    }

    private void opengllery() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                img.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(visualization.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(visualization.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
        try {
            imagebitmap(selectedImage);
        } catch (FirebaseMLException e) {
            e.printStackTrace();
        }
    }



    public  void  imagebitmap(Bitmap bitmap) throws FirebaseMLException {


        FirebaseVisionOnDeviceAutoMLImageLabelerOptions labelerOptions =
                new FirebaseVisionOnDeviceAutoMLImageLabelerOptions.Builder()
                        .setLocalModelName("model")
                        .build();
        FirebaseVisionImageLabeler labeler =
                FirebaseVision.getInstance().getOnDeviceAutoMLImageLabeler(labelerOptions);
        img.setImageBitmap(bitmap);
        labeler.processImage(FirebaseVisionImage.fromBitmap(bitmap))
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionImageLabel> labels) {
                        for (FirebaseVisionImageLabel label: labels) {
                            String text = label.getText();
                            float confidence = label.getConfidence();
                            String ptt = tt.getText().toString();
                            tt.setText("\nSoil: "+text+"\nConfidence : "+confidence);
                        }

                        Toast.makeText(visualization.this,"Succ",Toast.LENGTH_SHORT).show();



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(visualization.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });



    }
}
