package com.vnpt.hotel.manager.domain.model.response.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class MotelOverViewModel {
    @SerializedName("address")
    @Expose
    @Setter @Getter
    public String address;

    @SerializedName("emptyRooms")
    @Expose
    @Setter @Getter
    public String emptyRooms;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("hotelName")
    @Expose
    @Setter @Getter
    public String hotelName;

    @SerializedName("pictures")
    @Expose
    @Setter @Getter
    public String pictures;

    @SerializedName("totalBooking")
    @Expose
    @Setter @Getter
    public String totalBooking;

    @SerializedName("totalRoom")
    @Expose
    @Setter @Getter
    public String totalRoom;
}
