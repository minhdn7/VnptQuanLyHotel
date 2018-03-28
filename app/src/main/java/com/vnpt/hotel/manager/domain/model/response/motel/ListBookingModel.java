package com.vnpt.hotel.manager.domain.model.response.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListBookingModel {
    @SerializedName("amount")
    @Expose
    @Setter @Getter
    public Integer amount;

    @SerializedName("bookingDate")
    @Expose
    @Setter @Getter
    public String bookingDate;

    @SerializedName("bookingId")
    @Expose
    @Setter @Getter
    public Integer bookingId;

    @SerializedName("createDate")
    @Expose
    @Setter @Getter
    public String createDate;

    @SerializedName("createdBy")
    @Expose
    @Setter @Getter
    public Integer createdBy;

    @SerializedName("customerName")
    @Expose
    @Setter @Getter
    public String customerName;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("lastUpdate")
    @Expose
    @Setter @Getter
    public String lastUpdate;

    @SerializedName("lastUpdatedBy")
    @Expose
    @Setter @Getter
    public Integer lastUpdatedBy;

    @SerializedName("phone")
    @Expose
    @Setter @Getter
    public String phone;

    @SerializedName("qrNumber")
    @Expose
    @Setter @Getter
    public String qrNumber;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    public String responseMessage;

    @SerializedName("roomTypeId")
    @Expose
    @Setter @Getter
    public Integer roomTypeId;

    @SerializedName("roomTypeName")
    @Expose
    @Setter @Getter
    public String roomTypeName;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;

    @SerializedName("userId")
    @Expose
    @Setter @Getter
    public Integer userId;
}
