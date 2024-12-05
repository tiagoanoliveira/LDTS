package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public void draw(TextGraphics graphics) {
    graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
    graphics.putString(position.getX(), position.getY(), "X");
}
