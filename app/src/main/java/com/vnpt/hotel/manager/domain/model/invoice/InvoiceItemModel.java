package com.vnpt.hotel.manager.domain.model.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by VietNH on 3/17/2018.
 */

@AllArgsConstructor(suppressConstructorProperties = true)
public class InvoiceItemModel {
    @Expose @Setter @Getter @SerializedName("itemName") private String itemName;
    @Expose @Setter @Getter @SerializedName("itemCount") private String itemCount;
    @Expose @Setter @Getter @SerializedName("itemPrice") private String itemPrice;
    @Expose @Setter @Getter @SerializedName("itemAmount") private String itemAmount;
}
