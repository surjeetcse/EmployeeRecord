package com.axisrooms.EmployeeRecord.common.bean.scrunch;

import com.axisrooms.EmployeeRecord.common.enums.staticdump.ImageCategory;
import com.axisrooms.EmployeeRecord.common.enums.staticdump.ImageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
    //What is the image of - Bed room, entrance, bathroom etc.
    private String description;
    //What type of image is it - thumbnail, wide angle etc.
    private ImageType type;
    //URL or part there of - means to access the image
    private String url;

    private ImageCategory imageCategory;
    //Is this the default image of the property - 1 - yes, 0 - No.
    private String defaultImage;
    //Which room does this image belong to
    private String roomTypeId;

    @Tolerate
    public Image() {

    }
}
