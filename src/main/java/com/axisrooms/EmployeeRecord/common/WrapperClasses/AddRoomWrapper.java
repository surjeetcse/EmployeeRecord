package com.axisrooms.EmployeeRecord.common.WrapperClasses;

import java.io.Serializable;
import java.util.List;

public class AddRoomWrapper implements Serializable
{

    public long id;
    public long productId;
    public String displayName;
    public String userid;
    public String type;
    public boolean isDorm;
    public String description;
    public int totalRoom;
    public String bedType;
    public String roomView;
    public String roomViews;
    public String extraBedType;
    public int maxAdults;
    public int maxChild;
    public int maxInfant;
    public String isSmoking;
    public List<String> amenities;
    public String roomSize;
    public int baseOccupancy;
    public String extraAdultAfterX;

}
