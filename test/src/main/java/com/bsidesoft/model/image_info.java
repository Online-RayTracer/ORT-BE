package com.bsidesoft.model;

import org.json.simple.JSONArray;

import java.lang.reflect.Array;

public class image_info {
    String name;
    Integer width;
    Integer height;
    Integer samples;
    Array cam_location;
    Array cam_lookat;
    Float cam_aperture;
    Array light_color;
    JSONArray objects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSamples() {
        return samples;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }

    public Array getCam_location() {
        return cam_location;
    }

    public void setCam_location(Array cam_location) {
        this.cam_location = cam_location;
    }

    public Array getCam_lookat() {
        return cam_lookat;
    }

    public void setCam_lookat(Array cam_lookat) {
        this.cam_lookat = cam_lookat;
    }

    public Float getCam_aperture() {
        return cam_aperture;
    }

    public void setCam_aperture(Float cam_aperture) {
        this.cam_aperture = cam_aperture;
    }

    public Array getLight_color() {
        return light_color;
    }

    public void setLight_color(Array light_color) {
        this.light_color = light_color;
    }

    public JSONArray getObjects() {
        return objects;
    }

    public void setObjects(JSONArray objects) {
        this.objects = objects;
    }
}
