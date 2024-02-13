package org.studyeasy.SpringRest.payload.auth.album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhotoPayloadDTO {
    
    private String name;

    private String description;

}
