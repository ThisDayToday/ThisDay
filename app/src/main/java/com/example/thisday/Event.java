package com.example.thisday;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Event")
public class Event extends ParseObject {

    public static final String KEY_NAME = "eventName";
    public static final String KEY_TYPE = "eventType";
    public static final String KEY_DATE = "evteentDa";
    public static final String KEY_LOCATION = "eventLocation";
    public static final String KEY_DESCRIPTION = "eventDescription";
    public static final String KEY_IMAGE = "eventImage";
    public static final String KEY_ATTENDEES = "eventAttendees";
    public static final String KEY_ORGANIZATION = "eventOrganization";
    public static final String KEY_INPROFILE= "inProfile";

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescrition(String description){ put(KEY_DESCRIPTION, description); }

    public ParseFile getImage(){ return getParseFile(KEY_IMAGE); }


    public void setImage(ParseFile parseFile){ put(KEY_IMAGE, parseFile); }

    public String getName() { return getString(KEY_NAME); }

    public void setName(String name){ put(KEY_NAME, name); }

    public String getType() { return getString(KEY_TYPE); }

    // may want to make this an enum
    public void setType(String type){ put(KEY_TYPE, type); }

    public String getDate() { return getString(KEY_DATE); }

    public void setDate(Date date){ put(KEY_DATE, date); }

    public ParseGeoPoint getLocation() { return getParseGeoPoint(KEY_LOCATION); }

    public void setLocation(ParseGeoPoint location){ put(KEY_LOCATION, location); }

    public Number getAttendees() { return getNumber(KEY_ATTENDEES); }

    public void setAttendees(Number attendees){ put(KEY_ATTENDEES, attendees); }

    public ParseUser getOrganization() { return getParseUser(KEY_ORGANIZATION);}

    public void setAttendees(ParseUser organization){ put(KEY_ORGANIZATION, organization); }

    public void setInProfile(Boolean b){ put(KEY_INPROFILE, b); }

    public boolean inProfile() { return getBoolean(KEY_INPROFILE);}
}
