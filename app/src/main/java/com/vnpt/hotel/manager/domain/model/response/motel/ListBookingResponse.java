package com.vnpt.hotel.manager.domain.model.response.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListBookingResponse {
    @SerializedName("bookingList")
    @Expose
    @Setter @Getter
    public List<ListBookingModel> bookingList = null;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    public String responseMessage;
}
