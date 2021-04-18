package com.example.thisday;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddProfileImageActivity extends AppCompatActivity {

    String TAG = "AddProfileImageActivity";

    private ImageView  ivProfileI;
    private  ImageView ivBackgroundI;
    private Button  btnProfileI;
    private Button btnBackgroundI;
    private Button btnAddImage;
    private File photoFile;
    private File file;
    public static final int PICK_GALLERY =1;

    public String photoBack = "back.jpg";
    public String photoFileName = "photo.jpg";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_image);
        getSupportActionBar().hide();
        ivProfileI = findViewById(R.id.ivProfileI);
        ivBackgroundI  = findViewById(R.id.ivBackgroundI);
        btnBackgroundI =findViewById(R.id.btnBackgroundI);
        btnProfileI =findViewById(R.id.btnProfileI);
        btnAddImage =findViewById(R.id.btnAddImage);


        btnProfileI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                launchCamera();

            }
        });

        btnBackgroundI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gall = new Intent();
                file = getPhotoFileUri(photoBack);
                Uri fileProvider = FileProvider.getUriForFile(AddProfileImageActivity.this,"com.mydm.fileprovider", file);
                gall.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
                gall.setType("image/*");
                gall.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gall, "Select Picture"), PICK_GALLERY);

            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePost( photoFile, file);

            }
        });


    }

    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(this,"com.mydm.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        Log.i(TAG, "im here 4");

            Log.i(TAG, "im here 5");
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


    }

    private File getPhotoFileUri(String fileName) {

        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }


    private void savePost( File photoFile, File backFile) {

        ParseUser user =  ParseUser.getCurrentUser();
        ParseFile file1 = new ParseFile(photoFile);

        user.put("profileImg", file1);


        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                Log.e(TAG, "Error while saving1", e);
                }



            }
        });

        /*
        ParseFile file2 = new ParseFile(backFile);
        user.put("profileBackground", file2);

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving2", e);
                }


            }
        });
        */
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i(TAG, "im here 4");
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {

                Log.i(TAG, "im here 5");
                // by this point we have the camera photo on disk
                Bitmap takenImage;
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
                    takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                    ivProfileI.setImageBitmap(takenImage);
                }
                else {
                    Log.i(TAG, "im here 6");
                    Uri imageUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        Log.i(TAG, "im here 7");
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ivBackgroundI.setImageBitmap(bitmap);


                }


            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }

    }



}