package com.vnpt.hotel.manager.domain.model.response.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 30/3/2018.
 */

public class UpdateBookingResponse {
    @SerializedName("amount")
    @Expose
    @Getter @Setter
    public Integer amount;

    @SerializedName("bookingDate")
    @Expose
    @Getter @Setter
    public String bookingDate;

    @SerializedName("bookingId")
    @Expose
    @Getter @Setter
    public Integer bookingId;

    @SerializedName("createDate")
    @Expose
    @Getter @Setter
    public String createDate;

    @SerializedName("createdBy")
    @Expose
    @Getter @Setter
    public Integer createdBy;

    @SerializedName("customerName")
    @Expose
    @Getter @Setter
    public String customerName;

    @SerializedName("hotelId")
    @Expose
    @Getter @Setter
    public Integer hotelId;

    @SerializedName("lastUpdate")
    @Expose
    @Getter @Setter
    public String lastUpdate;

    @SerializedName("lastUpdatedBy")
    @Expose
    @Getter @Setter
    public Integer lastUpdatedBy;

    @SerializedName("phone")
    @Expose
    @Getter @Setter
    public String phone;

    @SerializedName("qrNumber")
    @Expose
    @Getter @Setter
    public String qrNumber;

    @SerializedName("responseCode")
    @Expose
    @Getter @Setter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Getter @Setter
    public String responseMessage;

    @SerializedName("roomTypeId")
    @Expose
    @Getter @Setter
    public Integer roomTypeId;

    @SerializedName("roomTypeName")
    @Expose
    @Getter @Setter
    public String roomTypeName;

    @SerializedName("status")
    @Expose
    @Getter @Setter
    public String status;

    @SerializedName("userId")
    @Expose
    @Getter @Setter
    public Integer userId;
}
