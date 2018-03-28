package com.vnpt.hotel.manager.domain.model.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class InvoiceModel {

  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("invoices") private List<Invoice> invoices;

  @AllArgsConstructor(suppressConstructorProperties = true)
  public static class Invoice {
    @Expose @Setter @Getter @SerializedName("invoiceId") private int invoiceId;
    @Expose @Setter @Getter @SerializedName("roomNumber") private String roomNumber;
    @Expose @Setter @Getter @SerializedName("amount") private String amount;
    @Expose @Setter @Getter @SerializedName("invoiceItems") private List<Object> invoiceItems;
    @Expose @Setter @Getter @SerializedName("checkInDate") private String checkInDate;
    @Expose @Setter @Getter @SerializedName("checkOutDate") private String checkOutDate;
    @Expose @Setter @Getter @SerializedName("amountRoom") private String amountRoom;
    @Expose @Setter @Getter @SerializedName("amountService") private String amountService;
  }

}
