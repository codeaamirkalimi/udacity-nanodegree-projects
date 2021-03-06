package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.cuisines.CuisinesResponse;
import com.emrekose.famula.model.geocode.GeocodeResponse;
import com.emrekose.famula.model.locations.LocationsResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivityRepository {

    private final ApiService apiService;

    @Inject
    public MainActivityRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Flowable<CuisinesResponse> getCuisines(int cityId, Double lat, Double lon) {
        return apiService.getCuisines(cityId, null, null)
                .onErrorResumeNext(t -> { Timber.e(String.valueOf(t)); })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<GeocodeResponse> getNearbyRestaurants(Double lat, Double lon) {
        return apiService.getGeoCode(lat, lon)
                .onErrorResumeNext(t -> { Timber.e(String.valueOf(t)); })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<LocationsResponse> getLocationDatas(String query) {
        return apiService.getLocations(query)
                .onErrorResumeNext(t -> { Timber.e(String.valueOf(t)); })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<GeocodeResponse> getLocationDatasByLatLon(Double lat, Double lon) {
        return apiService.getGeoCode(lat, lon)
                .onErrorResumeNext(t -> { Timber.e(String.valueOf(t)); })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
