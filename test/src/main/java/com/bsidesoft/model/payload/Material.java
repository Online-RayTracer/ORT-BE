package com.bsidesoft.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    private List<Float> location;

    private Float size;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MaterialSymbol material;

}
