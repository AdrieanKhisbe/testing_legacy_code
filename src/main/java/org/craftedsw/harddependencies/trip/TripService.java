package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        List<Trip> tl = new ArrayList<Trip>();
        User u = getLoggedUser();
        boolean f = false;
        if (u != null) {
            for (int i = 1; i <= user.getFriends().size(); i++) {
                User u2 = user.getFriends().get(i - 1);
                if (u2 != null) {
                    if (u2.equals(u) == true) {
                        f = true;
                        break;
                    }
                    continue;
                }
            }
            if (f) {
                tl = TripDAO.findTripsByUser(user);
            }
            return tl;
        } else {
            throw new UserNotLoggedInException();
        }

     
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
