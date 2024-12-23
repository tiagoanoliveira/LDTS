package com.t04g05.model;
import com.t04g05.model.Position;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import static org.static.jupiter.api.Assertions.assertEquals;

public class PositionTest{

    @Test
    public void testConstructor(){
        Position position = new Position(5, 10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

}