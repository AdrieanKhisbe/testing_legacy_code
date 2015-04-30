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

    public List<Trip> getFriendTrips(User loggedUser, User friend) throws UserNotLoggedInException {
        if (loggedUser == null) throw new UserNotLoggedInException();

        boolean areFriends = friend.getFriends().contains(loggedUser);
        return areFriends ? dao.findTrips(friend) : Collections.<Trip>emptyList();
    }

}
