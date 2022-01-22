package com.example.valarmorghulis.firebaseauth;

import android.text.Editable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mKey;
    private String mEmail;
    private String mUser;
    private String mPrice;
    private String mDate;
    private String mDesc;
    private String mLocation;
    private String openTime;
    private String closeTime;
    private String mCategory;
    String mLocation1, mLocation2, mLocation3, mLocation4, mLocation5;
    String mDeliveryCharge1, mDeliveryCharge2, mDeliveryCharge3, mDeliveryCharge4, mDeliveryCharge5;
    String mPayBill, mTillNo, mPhoneNo;

    public Upload() {
        //empty constructor needed
    }

//    public Upload(String name, String imageUrl, String price, String desc) {
//        if (name.trim().equals("")) {
//            name = "No Name";
//        }
//
//        mName = name;
//        mImageUrl = imageUrl;
//        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        mDesc = desc;
//        mUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
//        mPrice = price;
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//        Date date = new Date();
//        mDate = df.format(date);
//
//    }

    public Upload(String name, String imageUrl, String price, String desc, String location,
                  String opentime, String closetime, String category, String location1,
                  String deliveryCharge1, String location2,
                  String deliveryCharge2, String location3,
                  String deliveryCharge3, String location4,
                  String deliveryCharge4, String location5,
                  String deliveryCharge5, String payBill, String tillNo, String phoneNo) {
        mName = name;
        mImageUrl = imageUrl;
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mDesc = desc;
        mUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        mPrice = price;
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        mDate = df.format(date);
        mLocation = location;
        openTime = opentime;
        closeTime = closetime;
        mCategory = category;
        mLocation1 = location1;
        mDeliveryCharge1 = deliveryCharge1;
        mLocation2 = location2;
        mDeliveryCharge2 = deliveryCharge2;
        mLocation3 = location3;
        mDeliveryCharge3 = deliveryCharge3;
        mLocation4 = location4;
        mDeliveryCharge4 = deliveryCharge4;
        mLocation5 = location5;
        mDeliveryCharge5 = deliveryCharge5;
        mPayBill = payBill;
        mTillNo = tillNo;
        mPhoneNo = phoneNo;
    }


    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getEmail() { return mEmail; }
    public void setEmail(String email) { mEmail = email; }

    public String getUserName() { return mUser; }
    public void setUserName(String userName) { mUser = userName; }

    public String getDesc() { return mDesc; }
    public void setDesc(String desc) { mDesc = desc; }

    public String getPrice() {return mPrice;}
    public void setPrice(String price) { mPrice = price;}

    public String getLocation() {
        return mLocation;
    }
    public void setLocation(String location) {
        mLocation = location;
    }

    public String getOpenTime() { return openTime; }
    public void setOpenTime(String openTime) { this.openTime = openTime; }


    public String getCloseTime() { return closeTime; }
    public void setCloseTime(String closeTime) { this.closeTime = closeTime; }

    public String getCategory() { return mCategory; }
    public void setCategory(String category) { this.mCategory = category; }

    public String getLocation1() { return mLocation1; }
    public void setLocation1(String location1) { this.mLocation1 = location1; }


    public String getLocation2() { return mLocation2; }
    public void setLocation2(String location2) { this.mLocation2 = location2; }

    public String getLocation3() { return mLocation3; }
    public void setLocation3(String location3) { this.mLocation3 = location3; }

    public String getLocation4() { return mLocation4; }
    public void setLocation4(String location4) { this.mLocation4 = location4; }

    public String getLocation5() { return mLocation5; }
    public void setLocation5(String location5) { this.mLocation5 = location5; }

    public String getDeliveryCharge1() { return mDeliveryCharge1; }
    public void setDeliveryCharge1(String deliveryCharge1) { this.mDeliveryCharge1 = deliveryCharge1; }

    public String getDeliveryCharge2() { return mDeliveryCharge2; }
    public void setDeliveryCharge2(String deliveryCharge2) { this.mDeliveryCharge2 = deliveryCharge2; }

    public String getDeliveryCharge3() { return mDeliveryCharge3; }
    public void setDeliveryCharge3(String deliveryCharge3) { this.mDeliveryCharge3 = deliveryCharge3; }

    public String getDeliveryCharge4() { return mDeliveryCharge4; }
    public void setDeliveryCharge4(String deliveryCharge4) { this.mDeliveryCharge4 = deliveryCharge4; }

    public String getDeliveryCharge5() { return mDeliveryCharge5; }
    public void setDeliveryCharge5(String deliveryCharge5) { this.mDeliveryCharge5 = deliveryCharge5; }

    public String getPayBill() { return mPayBill; }
    public void setPayBill(String payBill) { this.mPayBill = payBill; }

    public String getTillNo() { return mTillNo; }
    public void setTillNo(String tillNo) { this.mTillNo = tillNo; }

    public String getPhoneNo() { return mPhoneNo; }

    public void setPhoneNo(String phoneNo) { this.mPhoneNo = phoneNo; }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

    public String getDate(){ return mDate; }
    public void setDate(String date) { mDate = date; }
}