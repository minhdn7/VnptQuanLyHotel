package com.vnpt.hotel.manager.domain.model.response.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class ListMotelOverViewResponse {
    @SerializedName("hotels")
    @Expose
    @Getter @Setter
    public List<MotelOverViewModel> hotels = null;

    @SerializedName("responseCode")
    @Expose
    @Getter @Setter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Getter @Setter
    public String responseMessage;
}
