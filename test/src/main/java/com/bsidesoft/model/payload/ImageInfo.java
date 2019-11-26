package com.bsidesoft.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {

    private String name;

    private int width;

    private int height;

    private int samples;

    private List<Float> cam_location;

    private List<Float> cam_lookat;

    private Float cam_aperture;

    private Float cam_vfov;

    private List<Float> light_color;

    private List<Material> objects;

}
