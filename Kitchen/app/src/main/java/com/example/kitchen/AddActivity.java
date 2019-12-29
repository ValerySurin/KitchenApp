package com.example.kitchen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    AddData addData;
    private List<Food> food = new ArrayList<>();
    private int PICK_IMAGE_REQUEST = 1;

    private List<Integer> StepNumberList = new ArrayList<>();
    private List<String> StepContentList =  new ArrayList();
    private Bitmap bitmap;
    private List<Integer> ProductName  = new ArrayList<>();
    private List<String> ProductType  = new ArrayList<>();
    private List<Integer> ProductCount = new ArrayList<>();

    TextView Status;
    ImageView imageView;
    TextView AddOrDelete;
    ImageView ImStatus;
    RecyclerView FoodListInAddActivity;
    EditText productSearch;
    EditText productType;
    EditText productCount;
    TextView EnterFood;
    TextView productTypeA;
    TextView productCountA;
    TextView EnterFoodType;
    TextView EnterFoodCount;
    TextView AddToRecipe;
    TextView AddStep;
    TextView AddToServer;
    LinearLayout hiddenElement;
    ChipGroup AddChipGroup;
    TextView tmp;
    RecyclerView StepList;
    EditText EnterStepNumber;
    EditText EnterStepContent;
    EditText EnterTime;
    LinearLayout AddStepLayout;
    View progressBar;
    ScrollView scrollView;
    TextView tmp1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        food.add(new Food("Свинина", R.color.meat, 1));
        food.add(new Food("Красный перец", R.color.spice, 2));
        food.add(new Food("Соевый соус", R.color.sause, 3));
        food.add(new Food("Соль", R.color.spice, 4));
        food.add(new Food("Яйцо куриное", R.color.meat, 5));

         ImageView AddPhotoImage = findViewById(R.id.AddPhotoImage);
         productSearch = findViewById(R.id.EditSearch);
         Status = findViewById(R.id.AddPhotoStatus);
         imageView = findViewById(R.id.PhotoInAddActivity);
         AddOrDelete = findViewById(R.id.AddOrDelete);
         ImStatus = findViewById(R.id.AddPhotoImage);
         FoodListInAddActivity = findViewById(R.id.FoodListInAddActivity);
         EnterFood = findViewById(R.id.EnterFood);
         productType = findViewById(R.id.EditSize);
         productCount = findViewById(R.id.EditCount);
         hiddenElement = findViewById(R.id.hiddenElement);
         productTypeA = findViewById(R.id.EditSizeA);
         productCountA = findViewById(R.id.EditCountA);
         EnterFoodType = findViewById(R.id.EnterFoodType);
         EnterFoodCount = findViewById(R.id.EnterFoodCount);
         AddToRecipe = findViewById(R.id.AddToRecipe);
         AddChipGroup = findViewById(R.id.AddFoodGroup);
         tmp = hiddenElement.findViewById(R.id.tmp);
         StepList = findViewById(R.id.AddStepList);
         AddStep = findViewById(R.id.AddStep);
         AddStepLayout = findViewById(R.id.AddStepLayout);
            AddToServer = findViewById(R.id.AddToServer);
        EnterStepNumber = AddStepLayout.findViewById(R.id.EnterStepNumber1);
        EnterStepContent = AddStepLayout.findViewById(R.id.EnterStepContent1);
        progressBar = findViewById(R.id.llProgressBar);
        scrollView = findViewById(R.id.ScrollViewInAdd);
        EnterTime = findViewById(R.id.EnterTime);
        tmp1 = findViewById(R.id.tmp1);




        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        FoodListInAddActivity.setLayoutManager(layoutManager);
        FoodListInAddActivity.setHasFixedSize(true);
        final AdapterForFoodListInAddActivity FoodAdapter[] = {new AdapterForFoodListInAddActivity(food, EnterFood, hiddenElement, EnterFoodType, EnterFoodCount)};
        FoodListInAddActivity.setAdapter(FoodAdapter[0]);


        productSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String userInput = charSequence.toString();
                List<Food> NewFood = new ArrayList<>();

                for(Food Food : food  ){
                    if (Pattern.compile(Pattern.quote(userInput), Pattern.CASE_INSENSITIVE).matcher(Food.getName()).find()){
                        NewFood.add(Food);
                    }
                }

                FoodAdapter[0] = new AdapterForFoodListInAddActivity(NewFood, EnterFood, hiddenElement, EnterFoodType, EnterFoodCount);
                FoodListInAddActivity.setAdapter(FoodAdapter[0]);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        productTypeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EnterFoodType.setText(productType.getText().toString());
                EnterFoodType.setTextColor(EnterFood.getCurrentTextColor());
                EnterFoodType.setVisibility(View.VISIBLE);
            }
        });

        productCountA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterFoodCount.setText(productCount.getText().toString());
                EnterFoodCount.setVisibility(View.VISIBLE);
            }
        });

        AddToRecipe.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Chip chip = new Chip(AddChipGroup.getContext());
                chip.setText("" + EnterFood.getText()+" " + EnterFoodCount.getText() + " " + EnterFoodType.getText());
                chip.setChipBackgroundColorResource(Integer.parseInt(tmp.getText().toString()));
                chip.setCloseIconVisible(true);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setCloseIconVisible(false);
                chip.setTextColor(R.color.ChipText);
                AddChipGroup.addView(chip);

                ProductName.add(Integer.parseInt(tmp1.getText().toString()));
                ProductCount.add(Integer.parseInt(EnterFoodCount.getText().toString()));
                ProductType.add(EnterFoodType.getText().toString());

            }
        });



        AddPhotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Status.getVisibility() == View.GONE){
                    imageView.setImageDrawable(null);
                    Status.setVisibility(View.VISIBLE);
                    AddOrDelete.setText("Добавить фото");
                    ImStatus.setImageDrawable(getDrawable(R.drawable.ic_add_black_24dp));
                    bitmap = null;
                }
                else {
                    chooseImage();
                }
            }
        });




        AddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p = Integer.parseInt(EnterStepNumber.getText().toString());
                StepNumberList.add(p);
                StepContentList.add(EnterStepContent.getText().toString());

                RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext());
                StepList.setLayoutManager(layoutManager2);
                AdapterForAddStep adapterForAddStep ;
                StepList.setHasFixedSize(true);
                adapterForAddStep = new AdapterForAddStep(StepNumberList, StepContentList);
                StepList.setAdapter(adapterForAddStep);
            }
        });



        AddToServer.setOnClickListener(new View.OnClickListener() {
            String k;
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);

               progressBar.setVisibility(View.VISIBLE);







                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
                byte[] byteArray = outputStream.toByteArray();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                Random r = new Random();
                int i1 = r.nextInt(500 - 0) + 65;
                final StorageReference storageRef = storage.getReferenceFromUrl("gs://kitchenapp-75c69.appspot.com/").child("android"+ i1 );

                UploadTask uploadTask = storageRef.putBytes(byteArray);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                k = (uri.toString());
                                Log.e("Tuts+", "uri: " + uri.toString());
                                int et = Integer.parseInt(EnterTime.getText().toString());


                                AddData addData = new AddData(1, k, ProductName, ProductType, ProductCount, StepContentList, StepNumberList, et );
                                int gjhg = 0;




                                NetworkService.getInstance()
                                        .getJSONApi()
                                        .AddRecipe(addData)
                                        .enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                                                t.printStackTrace();
                                            }
                                        });
                                progressBar.setVisibility(View.GONE);
                                finish();




                                //Handle whatever you're going to do with the URL here
                            }
                        });
                    }
                });

//                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        String k = (uri.toString());
//
//                        //Handle whatever you're going to do with the URL here
//                    }
//                });

            }


        });



    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                Status.setVisibility(View.GONE);
                AddOrDelete.setText("Удалить фото");
                ImStatus.setImageDrawable(getDrawable(R.drawable.ic_cancel_black_24dp));

                imageView.setImageBitmap(bitmap);







            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
