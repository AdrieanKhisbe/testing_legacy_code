package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.harddependencies.user.User;

import java.util.List;

public class TripDAO {

    public static List<Trip> findTripsByUser(User user) {
        throw new DependendClassCallDuringUnitTestException(
                "TripDAO should not be invoked on an unit test.");
    }

    public List<Trip> findTrips(User user) {
       return findTripsByUser(user);
    }


}