package com.bsidesoft.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;

public class image_info {
    String name;
    int width;
    int height;
    int samples;
    float[] cam_location;
    float[] cam_lookat;
    float cam_aperture;
    float[] light_color;
    JSONObject objects;
}
