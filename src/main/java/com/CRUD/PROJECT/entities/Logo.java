package com.CRUD.PROJECT.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logo {
    private String url;
    private String asset_id;
    private String public_id;
}
