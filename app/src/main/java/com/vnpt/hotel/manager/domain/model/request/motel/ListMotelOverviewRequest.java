package com.vnpt.hotel.manager.domain.model.request.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class ListMotelOverviewRequest {
    @SerializedName("pageIndex")
    @Expose
    @Setter @Getter
    private Integer pageIndex;

    @SerializedName("pageSize")
    @Expose
    @Setter @Getter
    private Integer pageSize;

    @SerializedName("userId")
    @Expose
    @Setter @Getter
    private Integer userId;

    public ListMotelOverviewRequest(Integer pageIndex, Integer pageSize, Integer userId) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.userId = userId;
    }
}
