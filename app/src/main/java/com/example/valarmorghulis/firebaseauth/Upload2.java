package com.example.valarmorghulis.firebaseauth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload2 {
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
    String mPayBill, mTillNo, mPhoneNo;

    public Upload2(String name, String imageUrl, String price, String desc, String location,
                  String opentime, String closetime, String category, String payBill, String tillNo, String phoneNo) {
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
