package com.scorpio.test.integration.server;

import com.scorpio.server.controller.ConnectionController;
import com.scorpio.server.controller.GameAccessController;
import com.scorpio.server.core.GameManager;
import com.scorpio.server.exception.WordSweeperException;
import com.scorpio.server.model.Game;
import com.scorpio.server.model.Player;
import com.scorpio.test.util.XMLUtil;
import com.scorpio.xml.Message;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;


public class JoinGameTest {
    private XMLUtil xml = new XMLUtil();
    @Before
    public void reset(){
        GameManager.reset();
    }

    @Test
    public void functionality_Basic(){
        ConnectionController router = new ConnectionController();
        GameAccessController gac = new GameAccessController();
        try {
            gac.createGame(new Player("testPlayer", null), "somePlace");
        }catch(WordSweeperException ex){
            fail();
        }

        Game g = GameManager.getInstance().findGameById("somePlace");
        int playersBefore = g.getPlayers().size();
        Message msg = xml.createMessageFromFile("testxml/joinGameRequest.xml");
        router.process(null, msg);
        int playersAfter = g.getPlayers().size();

        assert(playersBefore + 1 == playersAfter);
        assert(g.getPlayers().get(1).getName().equals("nextOne"));
    }


}
