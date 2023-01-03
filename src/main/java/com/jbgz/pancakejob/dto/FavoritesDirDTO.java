package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class FavoritesDirDTO {
    private Integer favoritesDirId;
    private String favoritesDirName;
    private Map<String,List<FavoritesDTO>> favorites;
}
