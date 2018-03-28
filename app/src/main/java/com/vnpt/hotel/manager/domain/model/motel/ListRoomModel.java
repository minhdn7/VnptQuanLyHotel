package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListRoomModel {
    @SerializedName("roomId")
    @Expose
    @Setter @Getter
    public Integer roomId;

    @SerializedName("roomNumber")
    @Expose
    @Setter @Getter
    public String roomNumber;

    @SerializedName("roomTypeName")
    @Expose
    @Setter @Getter
    public String roomTypeName;

    @SerializedName("services")
    @Expose
    @Setter @Getter
    public Integer services;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;

    @SerializedName("supports")
    @Expose
    @Setter @Getter
    public Integer supports;
}
