package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        List<Trip> tl = new ArrayList<Trip>();
        User loggedUser = getLoggedUser();
        boolean f = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend != null) {
                    if (friend.equals(loggedUser)) {
                        f = true;
                        break;
                    }
                }
            }
            if (f) {
                tl = findTripsByUser(user);
            }
            return tl;
        } else {
            throw new UserNotLoggedInException();
        }


    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
