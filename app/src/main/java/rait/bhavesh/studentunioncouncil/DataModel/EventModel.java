package rait.bhavesh.studentunioncouncil.DataModel;

import java.util.Date;

/**
 * Created by Archana on 6/5/2016.
 */
public class EventModel {

    public String name;
    public String imageUrl;
    public String date;
    public String venue;

    public EventModel()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
