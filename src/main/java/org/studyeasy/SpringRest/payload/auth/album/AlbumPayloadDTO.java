package org.studyeasy.SpringRest.payload.auth.album;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AlbumPayloadDTO {

    @NotBlank
    @Schema(description="Album name", example="travel", requiredMode=RequiredMode.REQUIRED)
    private String name;

    @NotBlank
    @Schema(description="Album Description", example="Description")

    private String description;
}
