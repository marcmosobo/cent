package com.example.valarmorghulis.firebaseauth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;

import com.bumptech.glide.Glide;
import com.example.valarmorghulis.firebaseauth.Model.AccessToken;
import com.example.valarmorghulis.firebaseauth.Services.DarajaApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.valarmorghulis.firebaseauth.Model.STKPush;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.example.valarmorghulis.firebaseauth.Constants.BUSINESS_SHORT_CODE;
import static com.example.valarmorghulis.firebaseauth.Constants.CALLBACKURL;
import static com.example.valarmorghulis.firebaseauth.Constants.PARTYB;
import static com.example.valarmorghulis.firebaseauth.Constants.PASSKEY;
import static com.example.valarmorghulis.firebaseauth.Constants.TRANSACTION_TYPE;

public class BuyFragment extends Fragment {

    ImageView pImage;
    private TextView name;
    private TextView price;
    private TextView seller;
    private TextView location;
    private TextView openHours;
    private TextView closeHours;
    private TextView deliveryLocation;
    private TextView sellDate;
    private TextView Desc_tag;
    private TextView Desc_text;
    private  TextView display_quantity;
    private TextView buyer_pickup_time;
    private TextView delivery_locations;
    private  TextView total_price;
    private TextView order_details;
    private  TextView choose_method;
    private LinearLayout quantity;
    private EditText mEditTextPickUpTime;
    private EditText bPhone;
    private RadioGroup radioGroup;
    private  RadioGroup radioGroup2;
    private RadioButton location1;
    private RadioButton location2;
    private RadioButton location3;
    private RadioButton location4;
    private RadioButton location5;
    private Button button_make_offer;
    private Button button_message;
    private Button button_add_to_cart;
    private Button button_delete;
    private Button button_increase;
    private Button button_decrease;
    private Button mButtonTimePicker;
    boolean mItemClicked = false;
    private String sName;
    private String sEmail;
    private String pName;
    private String bName;
    private String bEmail;
    private String sLocation;
    private String sOpenHours;
    private String sCloseHours;
    private String delLocation1;
    private String delLocation2;
    private String delLocation3;
    private String delLocation4;
    private  String delLocation5;
    private String delCharge1;
    private String delCharge2;
    private String delCharge3;
    private String delCharge4;
    private  String delCharge5;
    private int position;
    private int mHour, mMinute;
    int totalPrice;
    String pPrice;
    private String key;
    int imagePosition;
    int minteger;
    String stringImageUri;
    FirebaseAuth mAuth;
    DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;
    DatabaseReference userDatabase;
    private List<User> mUser;
    private List<Upload> mUploads;
    Daraja daraja;

//    private ApiClient mApiClient;
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;



    @Override
    public void onStart() {
        super.onStart();
        NetworkConnection networkConnection = new NetworkConnection();
        if (networkConnection.isConnectedToInternet(getActivity())
                || networkConnection.isConnectedToMobileNetwork(getActivity())
                || networkConnection.isConnectedToWifi(getActivity())) {

        } else {
            networkConnection.showNoInternetAvailableErrorDialog(getActivity());
            return;
        }
        String testEmail = mAuth.getInstance().getCurrentUser().getEmail();
        if (testEmail.equals(sEmail)) {
            button_make_offer.setVisibility(View.GONE);
            button_message.setVisibility(View.GONE);
//            button_add_to_cart.setVisibility(View.GONE);
            button_delete.setVisibility(View.VISIBLE);

            order_details.setVisibility(View.GONE);
            quantity.setVisibility(View.GONE);
            bPhone.setVisibility(View.GONE);
            choose_method.setVisibility(View.GONE);
            radioGroup.setVisibility(View.GONE);
            total_price.setVisibility(View.GONE);
            delivery_locations.setVisibility(View.GONE);
            radioGroup2.setVisibility(View.GONE);
            buyer_pickup_time.setVisibility(View.GONE);
            mButtonTimePicker.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "You are seller of this product", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buy, container, false);
        name = (TextView) v.findViewById(R.id.product_name);
        price = (TextView) v.findViewById(R.id.product_price);
        seller = (TextView) v.findViewById(R.id.product_seller);
        sellDate = (TextView) v.findViewById(R.id.product_date);
        location = (TextView) v.findViewById(R.id.seller_location);
        deliveryLocation = (TextView) v.findViewById(R.id.delivery_location);
        openHours = (TextView) v.findViewById(R.id.seller_open_hours);
        closeHours = (TextView) v.findViewById(R.id.seller_close_hours);
        delivery_locations = (TextView) v.findViewById(R.id.delivery_locations);
        button_make_offer = (Button) v.findViewById(R.id.offer_button);
        button_message = (Button) v.findViewById(R.id.msg_button);
//        button_add_to_cart = (Button) v.findViewById(R.id.cart_button);
        button_delete = (Button) v.findViewById(R.id.delete_button);
        display_quantity = (TextView) v.findViewById(R.id.integer_number);
        button_increase = (Button) v.findViewById(R.id.increase);
        button_decrease = (Button) v.findViewById(R.id.decrease);
        mButtonTimePicker = (Button) v.findViewById(R.id.pickup_time_button);
        pImage = (ImageView) v.findViewById(R.id.product_image);
        Desc_tag = (TextView) v.findViewById(R.id.Description_tag);
        Desc_text = (TextView) v.findViewById(R.id.Description);
        bName = mAuth.getInstance().getCurrentUser().getDisplayName();
        bEmail = mAuth.getInstance().getCurrentUser().getEmail();
        minteger = 0;
        display(minteger);
        location1 = (RadioButton) v.findViewById(R.id.location1);
        location2 = (RadioButton) v.findViewById(R.id.location2);
        location3 = (RadioButton) v.findViewById(R.id.location3);
        location4 = (RadioButton) v.findViewById(R.id.location4);
        location5 = (RadioButton) v.findViewById(R.id.location5);
        total_price = (TextView) v.findViewById(R.id.total_price);
        order_details = (TextView) v.findViewById(R.id.Details_tag);
        quantity = (LinearLayout) v.findViewById(R.id.quantity);
        choose_method = (TextView) v.findViewById(R.id.choose);
        buyer_pickup_time = (TextView) v.findViewById(R.id.buyer_time);
        mEditTextPickUpTime = (EditText) v.findViewById(R.id.pickup_time);
        bPhone = (EditText) v.findViewById(R.id.text_view_phone);
        radioGroup = (RadioGroup) v.findViewById(R.id.groupradio);
        radioGroup2 = (RadioGroup) v.findViewById(R.id.groupradio2);


        mUploads = new ArrayList<>();

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        mProgressDialog = new ProgressDialog(getActivity());
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true); //Set True to enable logging, false to disable.


        getAccessToken();

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            pName = bundle.getString("name");
            String pImageUrl = bundle.getString("imageUrl");
            pPrice = bundle.getString("price");
            //Bitmap bitmapImage = bundle.getParcelable("bitmapImage");
            sName = bundle.getString("userName");
            key = bundle.getString("key");
            String date = bundle.getString("date");
            String desc = bundle.getString("desc");
            sEmail = bundle.getString("email");
            sLocation = bundle.getString("location");
            sOpenHours = bundle.getString("openTime");
            sCloseHours = bundle.getString("closeTime");
            delLocation1 = bundle.getString("location1");
            delLocation2 = bundle.getString("location2");
            delLocation3 = bundle.getString("location3");
            delLocation4 = bundle.getString("location4");
            delLocation5 = bundle.getString("location5");
            delCharge1 = bundle.getString("deliveryCharge1");
            delCharge2 = bundle.getString("deliveryCharge2");
            delCharge3 = bundle.getString("deliveryCharge3");
            delCharge4 = bundle.getString("deliveryCharge4");
            delCharge5 = bundle.getString("deliveryCharge5");

            name.setText(pName);
            price.setText("Ksh " + pPrice);
            seller.setText(sName);
            sellDate.setText(date);
            location.setText(sLocation);
            openHours.setText(sOpenHours);
            closeHours.setText(sCloseHours);
            location1.setText(delLocation1);
            location2.setText(delLocation2);
            location3.setText(delLocation3);
            location4.setText(delLocation4);
            location5.setText(delLocation5);


            if (delLocation1 != null) {
                deliveryLocation.setText("Yes");
            }else {
                deliveryLocation.setText("No");
            }

            if (desc != null) {
                Desc_tag.setVisibility(View.VISIBLE);
                Desc_text.setVisibility(View.VISIBLE);
                Desc_text.setText(desc);
            }

            //pImage.setImageURI(Uri.parse(pImageUrl));
//            if (bitmapImage != null)
//                pImage.setImageBitmap(bitmapImage);
            if (pImageUrl != null) {
                String photoUrl = pImageUrl;
                Glide.with(this)
                        .load(photoUrl)
                        .into(pImage);
            }


            /*DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            DatabaseReference current = ref.child("fir-auth-431b5").child("user").child("token");
            //User currentUser = mUser.get(email);
*/

        }


        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        switch(selectedId) {
                            case R.id.rb_delivery:
                                delivery_locations.setVisibility(View.VISIBLE);
                                radioGroup2.setVisibility(View.VISIBLE);
                                buyer_pickup_time.setVisibility(View.GONE);
                                mButtonTimePicker.setVisibility(View.GONE);
                                break;
                            case R.id.rb_pickup:
                                buyer_pickup_time.setVisibility(View.VISIBLE);
                                mButtonTimePicker.setVisibility(View.VISIBLE);
                                delivery_locations.setVisibility(View.GONE);
                                radioGroup2.setVisibility(View.GONE);
                                break;
                        }

                    }
                });

        radioGroup2.clearCheck();

        radioGroup2.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButton2 = (RadioButton) group.findViewById(checkedId);
                        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                        switch(selectedId2) {
                            case R.id.location1:
                                totalPrice = Integer.parseInt(delCharge1) + totalPrice;
                                total_price.setText("Ksh " + totalPrice);
                                break;
                            case R.id.location2:
                                totalPrice = Integer.parseInt(delCharge2) + totalPrice;
                                total_price.setText("Ksh " + totalPrice);
                                break;
                            case R.id.location3:
                                totalPrice = Integer.parseInt(delCharge3) + totalPrice;
                                total_price.setText("Ksh " + totalPrice);
                                break;
                            case R.id.location4:
                                totalPrice = Integer.parseInt(delCharge4) + totalPrice;
                                total_price.setText("Ksh " + totalPrice);
                                break;
                            case R.id.location5:
                                totalPrice = Integer.parseInt(delCharge5) + totalPrice;
                                total_price.setText("Ksh " + totalPrice);
                                break;
                        }

                    }
                });

        mButtonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeChooser();
            }
        });

        button_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minteger = minteger + 1;
                display(minteger);
                totalPrice = Integer.parseInt(pPrice) * minteger;
                total_price.setText("Ksh " + totalPrice);
            }
        });

        button_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minteger = minteger - 1;
                display(minteger);
                totalPrice = Integer.parseInt(pPrice) * minteger;
                total_price.setText("Ksh " + totalPrice);
            }
        });



        button_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgFragment msgFragment = new MsgFragment();
                Bundle bundle = new Bundle();
                bundle.putString("sEmail", sEmail);
                bundle.putString("pName", pName);
                bundle.putString("sName", sName);
                bundle.putString("bName", mAuth.getInstance().getCurrentUser().getDisplayName());
                bundle.putString("bEmail", mAuth.getInstance().getCurrentUser().getEmail());
                msgFragment.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frag_container, msgFragment)
                        .addToBackStack(null).commit();


//                startActivity(new Intent(getActivity(), MsgActivity.class));
//                getActivity().finish();
            }
        });

        button_make_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minteger == 0) {
                    Toast.makeText(getActivity(), "No quantity has been selected", Toast.LENGTH_SHORT).show();
                }

                if (mEditTextPickUpTime.getText().toString().trim().isEmpty()) {
                    mEditTextPickUpTime.setError("Pickup time required");
                    mEditTextPickUpTime.requestFocus();
                }else{
                    mEditTextPickUpTime.getText().toString();
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(getActivity(), "No method has been selected", Toast.LENGTH_SHORT).show();
                }


                else {
                  RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
//                  Toast.makeText(getActivity(), radioButton.getText(), Toast.LENGTH_SHORT).show();

                    int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                    if (selectedId2 == -1) {
                        Toast.makeText(getActivity(), "No location has been selected", Toast.LENGTH_SHORT).show();
                    }else {

                        RadioButton radioButton2 = (RadioButton) radioGroup2.findViewById(selectedId2);
//                        Toast.makeText(getActivity(), radioButton2.getText(), Toast.LENGTH_SHORT).show();
                    }
                    if (bPhone.getText().toString().trim().isEmpty()) {
                        bPhone.setError("Phone number required");
                        bPhone.requestFocus();
                    }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Do you wish to submit this order?");
//                builder.setMessage("This will send an email notification along with your email id to the seller.");
                    builder.setMessage("Payment is via MPESA.The number you provided will be billed.");

                    builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            sendEmailToSeller();
//                            sendEmailToBuyer();
                                String phone_number = bPhone.getText().toString();
                                int amount = totalPrice;
                                performSTKPush(phone_number, amount);
                        }
                    });

                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog ad = builder.create();
                    ad.show();

                }
                }

            }
        });

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Alert!");
                builder.setMessage("Deletion is permanent. Are you sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        return v;
    }

    private void openTimeChooser() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        mEditTextPickUpTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void display(int number) {
        display_quantity.setText("" + number);
    }

    private void deleteProduct(){
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getActivity(), DrawerActivity.class));
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }

    public void performSTKPush(String phone_number,int amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        int businessCode = Integer.parseInt(BUSINESS_SHORT_CODE);
        int partyB = Integer.parseInt(PARTYB);
        long phoneNo = Long.parseLong(phone_number);
        STKPush stkPush = new STKPush(
                businessCode,
                Utils.getPassword(businessCode, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                amount,
                phoneNo,
                partyB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "CENT Limited", //Account reference
                "Payment of Product"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                    } else {
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void sendEmailToSeller() {
        String email = sEmail;
        String subject = "[Cent] Request for product " + pName;

        String msg = "unknown-user";
        if (bName != "")
            msg = bName;
        String thankMsg = "\n\nThank you for using Cent :)";
        String autoMsg = "\n\nThis is an auto generated email. Please do not reply to this email.";

        String message = "Hey " + sName + ". " + msg + " is requesting for your product \"" + pName + "\". Wait for further response from " + msg + ". If you want you can write to " + msg + " on email id " + bEmail + " ." + thankMsg + autoMsg;
        SendMail sm2s = new SendMail(getActivity(), email, subject, message);
        sm2s.execute();
    }

    private void sendEmailToBuyer() {
        String email = bEmail;
        String subject = "[Cent] Request Successful for " + pName;
        String thankMsg = "\n\nThank you for using Cent :)";
        String autoMsg = "\n\nThis is an auto generated email. Please do not reply to this email.";

        String message = "Hello " + bName + ". You have requested " + sName  +" for \"" + pName + "\". You can send message to " + sName + " in the app by clicking on message button." + thankMsg + autoMsg ;
        SendMail sm2b = new SendMail(getActivity(), email, subject, message);
        sm2b.execute();
    }


}
