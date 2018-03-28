package com.vnpt.hotel.manager.domain.model.request.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListBookingRequest {
    @SerializedName("fromDate")
    @Expose
    @Setter @Getter
    public String fromDate;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("page")
    @Expose
    @Setter @Getter
    public Integer page;

    @SerializedName("phone")
    @Expose
    @Setter @Getter
    public String phone;

    @SerializedName("qrNumber")
    @Expose
    @Setter @Getter
    public String qrNumber;

    @SerializedName("size")
    @Expose
    @Setter @Getter
    public Integer size;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;

    @SerializedName("toDate")
    @Expose
    @Setter @Getter
    public String toDate;

    @SerializedName("userId")
    @Expose
    @Setter @Getter
    public Integer userId;
}
