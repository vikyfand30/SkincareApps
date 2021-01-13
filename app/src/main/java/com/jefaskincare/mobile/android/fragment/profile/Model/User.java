package com.jefaskincare.mobile.android.fragment.profile.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String userid;
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String address;
    private String regionid;
    private String reffcode;
    private String rolename;
    private String point;
    private String result = "1";
    private String message;
    private String addressname;
    private String addressphone;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getReffcode() {
        return reffcode;
    }

    public void setReffcode(String reffcode) {
        this.reffcode = reffcode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddressname() {
        return addressname;
    }

    public String getAddressphone() {
        return addressphone;
    }
}
