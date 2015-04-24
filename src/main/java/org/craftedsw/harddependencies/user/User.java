package org.craftedsw.harddependencies.user;

import org.craftedsw.harddependencies.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<User> friends = new ArrayList<User>();
    private List<Trip> trips = new ArrayList<Trip>();

    public List<User> getFriends() {
        return friends;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
