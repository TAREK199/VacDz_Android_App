package com.tarek.vaccins.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Calendar;

import java.util.List;

public class CalendarResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }




    public class Data {

        @SerializedName("calendrier")
        @Expose
        private List<Calendar> calendrier = null;

        public List<Calendar> getCalendrier() {
            return calendrier;
        }

        public void setCalendrier(List<Calendar> calendrier) {
            this.calendrier = calendrier;
        }
    }

}
