package com.manbirkakkar.openweather.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain implements Parcelable {

    public final static Creator<Rain> CREATOR = new Creator<Rain>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Rain createFromParcel(Parcel in) {
            Rain instance = new Rain();
            instance._3h = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Rain[] newArray(int size) {
            return (new Rain[size]);
        }

    };

    @SerializedName("3h")
    @Expose
    public double _3h;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_3h);
    }

    public int describeContents() {
        return 0;
    }

}
