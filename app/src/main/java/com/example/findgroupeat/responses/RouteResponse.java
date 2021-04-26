package com.example.findgroupeat.responses;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RouteResponse {

    private List<LatLng> routes;

    public List<LatLng> getRoutes() {
        return routes;
    }

    public void setRoutes(List<LatLng> routes) {
        this.routes = routes;
    }
}
