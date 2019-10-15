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
String text;
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
        FirebaseLocalModel localModel = new FirebaseLocalModel.Builder("model")
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
            Toast.makeText(visualization.this, "Something went wrong", Toast.LENGTH_LONG).show();

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
                             text = label.getText();
                            float confidence = label.getConfidence();
                            String ptt = tt.getText().toString();
//                            tt.setText("\nSoil: "+text);

                        }


                        Toast.makeText(visualization.this,"Success ",Toast.LENGTH_SHORT).show();
                        if (text!="Desert")
                        {
                            text="Red Soil\n\nThe red color of soil is due to the presence of iron oxide. It appears yellow when it contains less iron or more water. Red soil contains a mixture of clay and sand, antis not fertile. However, the soil can be fertile by adding manures and fertilizers.";
                        }
                        else  if (text=="Desert")
                        {
                            text="Black Soil\n\nBlack soil is also known as black lava soil. This soil black in color. It is formed from lava rocks and is rich in clay.";
                        }
                        else {text= "Sandy soil \n\nSandy soil contains more than 60% sand and clay. It contains very little clay and silt, so it is porous. The size of soil particles in sandy soil is from 0.2mm to 2.0mm. The water building capacity of a sandy soil is very poor. Hence, there is a lot of air present in this type of soil.\n";}
                    tt.setText(text);
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
