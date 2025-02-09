package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Date {

    private String dateEnd;
    @JsonProperty("date_start")
    private String dateStart;
    @JsonProperty("date_end")
    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
}
