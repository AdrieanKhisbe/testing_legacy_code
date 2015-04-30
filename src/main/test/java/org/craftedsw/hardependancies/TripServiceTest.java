package org.craftedsw.hardependancies;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

/**
 * @author Adriean Khisbe
 */
public class TripServiceTest {


    private User loggedUser;
    private User userArg;

    @Test(expected = UserNotLoggedInException.class)
    public void getTrip_shouldthrowException_whenNotLoggedUSer() throws Exception {
        //given
        loggedUser = null;
        userArg = null;

        TripService tripService = new TripService(){
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };

        // then
        tripService.getTripsByUser(userArg);
    }
}
