package org.craftedsw.harddependancies.user;

import org.assertj.core.api.Assertions;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

/**
 * @author Adriean Khisbe
 */
public class UserTest {

    @Test
    public void addUser_reallyAddUser_IfUserNotnull() throws Exception {
        //Given
        User userWithFutureFriend = new User();
        User futureFriend = new User();

        userWithFutureFriend.addFriend(futureFriend);

        Assertions.assertThat(userWithFutureFriend.getFriends()).contains(futureFriend);

    }
}
