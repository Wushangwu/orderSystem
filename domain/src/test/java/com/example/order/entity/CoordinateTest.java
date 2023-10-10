package com.example.order.entity;


import com.example.order.exception.DisCorrectInputException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class CoordinateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void check_latitude_more_than_80() {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input latitude is not correct");
        new Coordinate("90","0","0","0");
    }

    @Test
    public void check_latitude_less_than_80() {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input latitude is not correct");
        new Coordinate("-95","0","0","0");
    }


    @Test
    public void check_longitude_more_than_180() {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input longitude is not correct");
        new Coordinate("0","190","0","0");
    }

    @Test
    public void check_longitude_less_than_180() {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input longitude is not correct");
        new Coordinate("0","-190","0","0");
    }

}