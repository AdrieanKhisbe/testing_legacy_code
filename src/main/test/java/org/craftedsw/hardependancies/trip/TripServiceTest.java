package org.craftedsw.hardependancies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Adriean Khisbe
 */
public class TripServiceTest {


    private User loggedUser;
    private User userArg;
    private TripService tripService;
    private List<Trip> daoTripResult;


    @Before
    public void setUp() throws Exception {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class)
    public void getTrip_shouldthrowException_whenNotLoggedUSer() throws Exception {
        //given
        loggedUser = null;
        userArg = null;

        // then
        tripService.getTripsByUser(userArg);
    }

    @Test
    public void getTrip_shouldReturnNoTrip_whenGivenUSerHasNoFriend() throws Exception {
        //given
        loggedUser = new User();
        userArg = new User();

        // then
        List<Trip> trips = tripService.getTripsByUser(userArg);
        assertThat(trips).isEmpty();
    }

    @Test
    public void getTrip_shouldGiveTheTripOfTheLoggedUser_whenGivenUserIsFriendOFLoggedInUser() throws Exception {
        //given
        loggedUser = new User();
        userArg = new User();
        userArg.getFriends().add(loggedUser);

        daoTripResult = new ArrayList<Trip>();
        Trip trip = new Trip();
        daoTripResult.add(trip);

        // then
        List<Trip> trips = tripService.getTripsByUser(userArg);
        assertThat(trips).isNotEmpty();
        assertThat(trips).contains(trip);
    }

    @Test
    public void getTrip_shouldNoTrips_whenGivenUSerIsNoFriendOfLoggedUser() throws Exception {
        //given
        loggedUser = new User();
        userArg = new User();
        for (int i = 0; i < 3; i++)
            userArg.addFriend(new User());

        daoTripResult = new ArrayList<Trip>();
        Trip trip = new Trip();
        daoTripResult.add(trip);

        // then
        List<Trip> trips = tripService.getTripsByUser(userArg);
        assertThat(trips).isEmpty();
    }


    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return daoTripResult;
        }

    }

}
