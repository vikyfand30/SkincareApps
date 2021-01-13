package com.jefaskincare.mobile.android.activities.Model;

import androidx.annotation.NonNull;

public class Province {

    private String ProvId;
    private String ProvName;

    public String getProvId() {
        return ProvId;
    }

    public void setProvId(String provId) {
        ProvId = provId;
    }

    public String getProvName() {
        return ProvName;
    }

    public void setProvName(String provName) {
        ProvName = provName;
    }

    @NonNull
    @Override
    public String toString() {
        return ProvName;
    }
}
