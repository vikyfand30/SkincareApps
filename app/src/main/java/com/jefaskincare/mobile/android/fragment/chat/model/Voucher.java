package com.jefaskincare.mobile.android.fragment.chat.model;

import android.content.Intent;

public class Voucher {

    private Integer voucherID;
    private String voucherPrice;
    private String voucherKet;

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(String voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getVoucherKet() {
        return voucherKet;
    }

    public void setVoucherKet(String voucherKet) {
        this.voucherKet = voucherKet;
    }

    public  Voucher (Integer voucherID, String voucherPrice, String voucherKet){
        this.voucherID = voucherID;
        this.voucherPrice = voucherPrice;
        this.voucherKet = voucherKet;
    }



}


