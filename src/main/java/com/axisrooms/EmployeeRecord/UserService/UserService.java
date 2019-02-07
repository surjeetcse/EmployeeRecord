package com.axisrooms.EmployeeRecord.UserService;

import com.axisrooms.EmployeeRecord.LoggerInfo.Log4jHelloWorld;
import com.axisrooms.EmployeeRecord.common.StaticDumpHotel;
import com.axisrooms.EmployeeRecord.common.bean.scrunch.*;
import com.axisrooms.EmployeeRecord.common.enums.staticdump.OTA;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired(required = true)
    MongoTemplate mongoTemplate;
    @Autowired
    Location location;
    final static org.apache.log4j.Logger logger = Logger.getLogger(UserService.class);
    public String pushStaticDumpDataToExtranet() {
        Long count=0l;
        Long count2=0l;
        Long count3=0l;
        Query query = new Query();
        CommunicationInfo communicationInfo=new CommunicationInfo();
        Fees fees=new Fees();
        HotelAmenity hotelAmenity=new HotelAmenity();
        Image image=new Image();
        Information information=new Information();
        Point point=new Point();
        PointOfInterest pointOfInterest=new PointOfInterest();
        RoomAmenity roomAmenity=new RoomAmenity();
        RoomInformation roomInformation=new RoomInformation();
        RoomType roomType=new RoomType();
        UpdateDate updateDate=new UpdateDate();

        List<HotelAmenity> hotelAmenityList=new ArrayList<>();
        List<RoomAmenity>roomAmenityList=new ArrayList<>();
        List<RoomInformation> roomInformationList=new ArrayList<>();
        List<PointOfInterest> pointOfInterestList=new ArrayList<>();
        List<String> hotelPoliciesList=new ArrayList<>();
        List<Image>imageList=new ArrayList<>();
        String hotelUpdateDate="";
        String renovationDetails="";
        Map<String,String> stateMap=new HashMap<>();
        Map<String, String> cityMap = new HashMap<>();
        Map<String, String> notvalid = new HashMap<>();

        try {
            Pageable pageable = PageRequest.of(20, 5);
            query.addCriteria(Criteria.where("ota").is("TRAVELGURU")).with(pageable);
            List<StaticDumpHotel> staticDumpHotels = mongoTemplate.find(query, StaticDumpHotel.class);
            System.out.println("Number of Documents : "+staticDumpHotels.size());
            if (!(staticDumpHotels.isEmpty())) {
                for (StaticDumpHotel staticDumpHotel : staticDumpHotels) {
                    //System.out.println(staticDumpHotel);
                    String hotelId=staticDumpHotel.get_id();
                    OTA ota=staticDumpHotel.getOta();
                    information=staticDumpHotel.getInformation();
                    if(staticDumpHotel.getHotelAmenities()!=null)hotelAmenityList=staticDumpHotel.getHotelAmenities();
                    if(staticDumpHotel.getRoomInformations()!=null) {
                        roomInformationList = staticDumpHotel.getRoomInformations();
                        if (!(roomInformationList.isEmpty())) {
                            for (RoomInformation roomInformation1 : roomInformationList) {
                                if (roomInformation1.getAmenities() != null)
                                    roomAmenityList = roomInformation1.getAmenities();
                            }
                        }
                    }
                    if(staticDumpHotel.getPointsOfInterest()!=null)pointOfInterestList=staticDumpHotel.getPointsOfInterest();
                    if(staticDumpHotel.getHotelPolicies()!=null)hotelPoliciesList=staticDumpHotel.getHotelPolicies();
                    if(staticDumpHotel.getImages()!=null)imageList=staticDumpHotel.getImages();
                    if(staticDumpHotel.getUpdateDate()!=null) hotelUpdateDate=staticDumpHotel.getUpdateDate();else hotelUpdateDate="HotelUpdateDate is Not avalable";

                    String name=information.getName();
                    if(isAlpha(name)) {

                        try{
                            // Create file
                            FileWriter fstream = new FileWriter( "out.json",true);
                            BufferedWriter out = new BufferedWriter(fstream);

                            //count++;out.write("No -> "+count.toString());out.newLine();
                            String stateId="";
                            String cityId ="";
                            String state=information.getState();
                            String city=information.getCity();

                            if(stateMap.containsValue(state.toUpperCase())) {
                                for (Map.Entry<String, String> entry : stateMap.entrySet()) {
                                    if (entry.getValue().equals(state.toUpperCase())) {
                                        stateId=entry.getKey();
                                    }
                                }
                            }else{
                                stateId=location.getState(state);
                                stateMap.put(stateId,state);
                            }
                            if(!stateId.equals("0")) {
                                if(cityMap.containsValue(city.toUpperCase())) {
                                    for (Map.Entry<String, String> entry : cityMap.entrySet()) {
                                        if (entry.getValue().equals(city.toUpperCase())) {
                                            cityId=entry.getKey();
                                        }
                                    }
                                }else{
                                    cityId=location.getCity(city,stateId);
                                    cityMap.put(cityId,city);
                                }
                                count++;

                               logger.info(count+" ->"+city+" CityId ->"+cityId);

//                                System.out.println("State Is is ->" + stateId);
//                                System.out.println("City Id is ->" + cityId);
                            }else{
                                if(!(notvalid.containsValue(state.toUpperCase()))) {
                                    count3++;
                                    out.write("***********Hotel Location Details******************");out.newLine();
                                    out.write("No -> "+count3.toString());out.newLine();
                                    out.write("Hotel Id : "+hotelId);out.newLine();
                                    out.write("State Name : "+state);out.newLine();
                                    out.write("City Name :"+city);out.newLine();
                                    notvalid.put(hotelId,state.toUpperCase());
                                }

                            }

//                            out.write("Hotel Id : "+hotelId);out.newLine();
//                            out.write("OTA : "+ota);out.newLine();
//                            out.write("Hotel Name : "+information.getName());out.newLine();
//                            if(!(hotelAmenityList.isEmpty()))out.write("HotelAmenity Size : "+hotelAmenityList.size());else out.write("HotelAmenity is Not avalable");out.newLine();
//                            if(!(roomAmenityList.isEmpty()))out.write("RoomAmenity Size : "+roomAmenityList.size());else out.write("RoomAmenity is Not avalable");out.newLine();
//                            if(!(roomInformationList.isEmpty()))out.write("RoomInformation Size : "+roomInformationList.size());else out.write("RoomInformation is Not avalable");out.newLine();
//                            if(!(pointOfInterestList.isEmpty()))out.write("PointOfInterest Size : "+pointOfInterestList.size());else out.write("PointOfInterest is Not avalable");out.newLine();
//                            if(!(hotelPoliciesList.isEmpty()))out.write("HotelPolicies Size : "+hotelPoliciesList.size());else out.write("HotelPolicies is Not avalable");out.newLine();
//                            if(!(imageList.isEmpty()))out.write("Image Size : "+imageList.size());else out.write("Images is Not avalable");out.newLine();
//                            out.write("HotelUpdateDate :"+hotelUpdateDate);out.newLine();
                            //Close the output stream
                            out.close();
                        }catch (Exception e){//Catch exception if any
                            System.err.println("Error: " + e.getMessage());
                        }

                    }else count2++;
                }
            }
            System.out.println("Number of Hotel in Static Dump DB ->"+staticDumpHotels.size());
            System.out.println(stateMap.toString());
            System.out.println(cityMap.toString());
            System.out.println("Valid Hotel Location ->"+count);
            System.out.println("InValid Hotel Location ->"+count2);
            logger.info("***Finish***Inserted Hotel details saved in JsonFile.json and not inserted hotel details saved in NotInsertedHotel.json file");
        } catch (Exception ex) {
            logger.info(ex.toString());
        }
        return "***Finish***Inserted Hotel details saved in JsonFile.json and not inserted hotel details saved in NotInsertedHotel.json file";
    }
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
