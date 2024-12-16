package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Coin;

public class CoinViewer implements ElementViewer<Coin>{
    @Override
    public void draw(GUI gui, Coin coin) {
        gui.drawCoin(coin.getPosition());
    }
}
