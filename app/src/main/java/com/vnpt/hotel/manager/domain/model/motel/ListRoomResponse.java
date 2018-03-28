package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListRoomResponse {
    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    public String responseMessage;

    @SerializedName("roomList")
    @Expose
    @Setter @Getter
    public List<ListRoomModel> roomList = null;
}
