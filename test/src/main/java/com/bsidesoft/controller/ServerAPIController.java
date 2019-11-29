package com.bsidesoft.controller;

import com.bsidesoft.model.payload.ImageInfo;
import com.bsidesoft.renderer.*;
import com.bsidesoft.service.StorageService;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.lang.Thread.currentThread;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ServerAPIController {

    private StorageService storageService;

    @PostMapping(value = "/renderer", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public Resource postRequest(@RequestBody ImageInfo imageInfo) throws IOException {

        long start = System.currentTimeMillis();

        renderer renderer = new renderer();
        renderer.width = imageInfo.getWidth();
        renderer.height = imageInfo.getHeight();
        renderer.samples = imageInfo.getSamples();

        renderer.light_color = new linear_color(imageInfo.getLight_color().get(0), imageInfo.getLight_color().get(1), imageInfo.getLight_color().get(2));
        renderer.filepath = "./produceimage/" + imageInfo.getName();

        vec3 lookfrom = new vec3(imageInfo.getCam_location().get(0), imageInfo.getCam_location().get(1), imageInfo.getCam_location().get(2));
        vec3 lookat = new vec3(imageInfo.getCam_lookat().get(0), imageInfo.getCam_lookat().get(1), imageInfo.getCam_lookat().get(2));
        float dist_to_focus = lookfrom.diff(lookat).size();
        renderer.cam = new camera(lookfrom, lookat, new vec3(0,1,0), imageInfo.getCam_vfov(),
                          (float)renderer.width/renderer.height, imageInfo.getCam_aperture(), dist_to_focus);

        ArrayList<hitable> objects = new ArrayList<hitable>();

        for (int i = 0; i < imageInfo.getObjects().size(); i++) {

            switch (imageInfo.getObjects().get(i).getMaterial().getType()) {
                case "metal" : objects.add(new sphere(new vec3(imageInfo.getObjects().get(i).getLocation().get(0),
                                                imageInfo.getObjects().get(i).getLocation().get(1),
                                                imageInfo.getObjects().get(i).getLocation().get(2)),
                                                imageInfo.getObjects().get(i).getSize(), new metal(
                                                        new linear_color(imageInfo.getObjects().get(i).getMaterial().getColor().get(0),
                                                                         imageInfo.getObjects().get(i).getMaterial().getColor().get(1),
                                                                         imageInfo.getObjects().get(i).getMaterial().getColor().get(2)),
                                                                         imageInfo.getObjects().get(i).getMaterial().getRoughness())));

                case "lambertian": objects.add(new sphere(new vec3(imageInfo.getObjects().get(i).getLocation().get(0),
                                                imageInfo.getObjects().get(i).getLocation().get(1),
                                                imageInfo.getObjects().get(i).getLocation().get(2)),
                                                imageInfo.getObjects().get(i).getSize(), new lambertian(
                                                        new linear_color(imageInfo.getObjects().get(i).getMaterial().getColor().get(0),
                                                                         imageInfo.getObjects().get(i).getMaterial().getColor().get(1),
                                                                         imageInfo.getObjects().get(i).getMaterial().getColor().get(2)))));

                case "dielectric": objects.add(new sphere(new vec3(imageInfo.getObjects().get(i).getLocation().get(0),
                                                imageInfo.getObjects().get(i).getLocation().get(1),
                                                imageInfo.getObjects().get(i).getLocation().get(2)),
                                                imageInfo.getObjects().get(i).getSize(), new dielectric(
                                                        imageInfo.getObjects().get(i).getMaterial().getRef_idx())));
            }
        }

        renderer.world = new hitable_list(objects.toArray(new hitable[0]));
        Thread this_thread = currentThread();
        renderer.on_rendered = this_thread::interrupt;

        renderer.start_render_async();
        System.out.println("Rendering started...");

        while (true) {
            try {
                    Thread.sleep(2000);
            } catch (InterruptedException ignored) {

            }
            float progress = renderer.get_progress();
            System.out.printf("Progress: %.2f%%\n", progress * 100);
            if (progress >= 1) break;
        }
        System.out.println("Image rendered successfully!");

        long s = (System.currentTimeMillis() - start) / 1000;
        System.out.printf("Time took: %d:%02d:%02d", s / 3600, s % 3600 / 60, s % 60);

        File file = new File("./produceimage/" + imageInfo.getName());
        InputStream is = FileUtils.openInputStream(file);

        return new InputStreamResource(is);

    }
}