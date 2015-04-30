package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TripService {
    
    private TripDAO dao;

    public TripService(TripDAO dao) {
        this.dao = dao;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();

        if (loggedUser == null) throw new UserNotLoggedInException();

        return user.getFriends().contains(loggedUser) ? dao.findTrips(user) : Collections.<Trip>emptyList();
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
