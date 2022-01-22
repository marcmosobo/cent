package com.example.valarmorghulis.firebaseauth.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class STKPush {
    @SerializedName("BusinessShortCode")
    @Expose
    public Integer businessShortCode;
    @SerializedName("Password")
    @Expose
    public String password;
    @SerializedName("Timestamp")
    @Expose
    public String timestamp;
    @SerializedName("TransactionType")
    @Expose
    public String transactionType;
    @SerializedName("Amount")
    @Expose
    public Integer amount;
    @SerializedName("PartyA")
    @Expose
    public Long partyA;
    @SerializedName("PartyB")
    @Expose
    public Integer partyB;
    @SerializedName("PhoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("CallBackURL")
    @Expose
    public String callBackURL;
    @SerializedName("AccountReference")
    @Expose
    public String accountReference;
    @SerializedName("TransactionDesc")
    @Expose
    public String transactionDesc;
    private final static long serialVersionUID = 2770749262469596525L;

    /**
     * No args constructor for use in serialization
     *
     */
    public STKPush() {
    }

    /**
     *
     * @param transactionType
     * @param partyA
     * @param password
     * @param amount
     * @param phoneNumber
     * @param callBackURL
     * @param accountReference
     * @param partyB
     * @param businessShortCode
     * @param timestamp
     * @param transactionDesc
     */
    public STKPush(Integer businessShortCode, String password, String timestamp,
                   String transactionType, Integer amount, Long partyA, Integer partyB,
                   String phoneNumber, String callBackURL, String accountReference,
                   String transactionDesc) {
        super();
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }


}
