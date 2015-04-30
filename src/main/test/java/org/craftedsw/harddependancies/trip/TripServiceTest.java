package org.craftedsw.harddependancies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.*;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Adriean Khisbe
 */
public class TripServiceTest {

    private static User JOHN = new User();
    private static User CHUCK = new User();

    private User loggedUser;
    private User userArg;
    private TripService tripService;
    private TripDAO tripDao;
    private List<Trip> daoTripResult;

    @Before
    public void setUp() throws Exception {
        tripDao = Mockito.mock(TripDAO.class);
        tripService = new TripService(tripDao);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void getTrip_shouldthrowException_whenNotLoggedUSer() throws Exception {
        //given
        loggedUser = null;
        userArg = null;

        // then
        tripService.getFriendTrips(loggedUser, userArg);
    }

    @Test
    public void getTrip_shouldReturnNoTrip_whenGivenUSerHasNoFriend() throws Exception {
        //given
        loggedUser = JOHN;
        userArg = CHUCK;

        // then
        List<Trip> trips = tripService.getFriendTrips(loggedUser, userArg);
        assertThat(trips).isEmpty();
    }

    @Test
    public void getTrip_shouldGiveTheTripOfTheLoggedUser_whenGivenUserIsFriendOFLoggedInUser() throws Exception {
        //given
        loggedUser = JOHN;
        userArg = CHUCK;
        userArg.getFriends().add(loggedUser);

        daoTripResult = new ArrayList<Trip>();
        Trip trip = new Trip();
        daoTripResult.add(trip);
        Mockito.stub(tripDao.findTrips(userArg)).toReturn(daoTripResult);


        // then
        List<Trip> trips = tripService.getFriendTrips(loggedUser, userArg);
        assertThat(trips).isNotEmpty();
        assertThat(trips).contains(trip);
    }

    @Test
    public void getTrip_shouldNoTrips_whenGivenUSerIsNoFriendOfLoggedUser() throws Exception {
        //given
        loggedUser = JOHN;
        userArg = CHUCK;

        for (int i = 0; i < 3; i++)
            userArg.addFriend(new User());

        daoTripResult = new ArrayList<Trip>();
        Trip trip = new Trip();
        daoTripResult.add(trip);

        // then
        List<Trip> trips = tripService.getFriendTrips(loggedUser, userArg);
        assertThat(trips).isEmpty();
    }

}
