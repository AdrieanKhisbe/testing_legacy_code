package org.craftedsw.hardependancies;

import org.assertj.core.api.Assertions;
import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

import java.util.List;

/**
 * @author Adriean Khisbe
 */
public class TripServiceTest {


    private User loggedUser;
    private User userArg;
    private TripService tripService;

    
    @Test(expected = UserNotLoggedInException.class)
    public void getTrip_shouldthrowException_whenNotLoggedUSer() throws Exception {
        //given
        loggedUser = null;
        userArg = null;
        tripService = new TestableTripService();

        // then
        tripService.getTripsByUser(userArg);
    } 
    
    @Test
    public void getTrip_shouldreturnEmptyList_whenGivenUSerHasNoFriend() throws Exception {
        //given
        loggedUser = new User();
        userArg = new User();
        TripService tripService = new TestableTripService();
        
        // then
        List<Trip> trips = tripService.getTripsByUser(userArg);
        Assertions.assertThat(trips).isEmpty();
    }
    
    
    
    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }
    
}
