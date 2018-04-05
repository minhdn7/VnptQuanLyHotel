package com.vnpt.hotel.manager.domain.model.request.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 30/3/2018.
 */

public class UpdateBookingRequest {
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

    @SerializedName("customerName")
    @Expose
    @Setter @Getter
    public String customerName;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;
}
