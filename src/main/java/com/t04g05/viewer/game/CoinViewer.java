package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Coin;

import java.io.IOException;

public class CoinViewer implements ElementViewer<Coin>{
    @Override
    public void draw(GUI gui, Coin coin) throws IOException {
        gui.drawCoin(coin.getPosition());
    }
}
