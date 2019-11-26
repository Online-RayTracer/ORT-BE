package com.bsidesoft.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSymbol {

    private String type;

    private List<Float> color;

    private Float roughness = (float) 0;

    private Float ref_idx = (float) 0;

}
