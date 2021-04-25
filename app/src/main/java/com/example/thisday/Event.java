package com.example.thisday;

import com.parse.Parse;
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
    public static final String KEY_DATE = "eventDate";
    public static final String KEY_LOCATION = "eventLocation";
    public static final String KEY_DESCRIPTION = "eventDescription";
    public static final String KEY_IMAGE = "eventImage";
    public static final String KEY_ATTENDEES = "eventAttendees";
    public static final String KEY_ORGANIZATION = "eventOrganization";
    public static final String KEY_OBJECT_ID = "objectId";

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

    public String getDate() { return  getString(KEY_DATE); }

    public void setDate(Date date){ put(KEY_DATE, date); }

    public String getLocation() { return getString(KEY_LOCATION); }

    public void setLocation(ParseGeoPoint location){ put(KEY_LOCATION, location); }

    //public Number getAttendees() { return getNumber(KEY_ATTENDEES); }

    public void setAttendees(Number attendees){ put(KEY_ATTENDEES, attendees); }

    public ParseUser getOrganization() { return getParseUser(KEY_ORGANIZATION);}

    public void setAttendees(ParseUser organization){ put(KEY_ORGANIZATION, organization); }

    public String getAttendees(){return getString(KEY_ATTENDEES);}


}
