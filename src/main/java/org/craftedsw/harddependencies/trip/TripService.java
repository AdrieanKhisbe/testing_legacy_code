package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        List<Trip> trips = new ArrayList<Trip>();
        User loggedUser = getLoggedUser();
        
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        boolean areFriends =  user.getFriends().contains(loggedUser);
        if (areFriends) {
            trips = findTripsByUser(user);
        }
        return trips;
        
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
