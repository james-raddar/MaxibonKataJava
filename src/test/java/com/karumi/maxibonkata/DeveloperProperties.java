package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private static final String ANY_NAME = "Pedro";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    // Este test con numeros negativos daria error porque en el constructor del Developer forzamos
    // a que si el numero de maxibones es negativo que sea 0 y lo detecta el framework de tests
    // y nos arroja este error:
    // Property the_number_of_maxibons_is_always_zero_or_greater falsified for args shrunken
    /*@Property
    public void the_number_of_maxibons_is_always_zero_or_greater(int maxibonNumber) {
        Developer developer = new Developer(ANY_NAME, maxibonNumber);
        assertEquals(maxibonNumber, developer.getNumberOfMaxibonsToGrab());
    }*/

    // Test 1
    @Property
    public void the_number_of_maxibons_is_always_zero_or_greater_always_positive_values(
            @From(PositiveIntGenerator.class) int maxibonNumber) {
        Developer developer = new Developer(ANY_NAME, maxibonNumber);
        assertEquals(maxibonNumber, developer.getNumberOfMaxibonsToGrab());
    }

    // Test 2
    @Property
    public void the_name_is_assigned_in_construction(String name) {
        Developer developer = new Developer(name, ANY_NUMBER_OF_MAXIBONS);
        assertEquals(name, developer.getName());
    }

    // Test 3
    @Test
    public void the_number_of_maxibons_associated_to_every_karumies_is_already_assigned() {
        assertEquals(3, Karumies.PEDRO.getNumberOfMaxibonsToGrab());
        assertEquals(0, Karumies.DAVIDE.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.ALBERTO.getNumberOfMaxibonsToGrab());
        assertEquals(2, Karumies.SERGIO.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.JORGE.getNumberOfMaxibonsToGrab());
    }
}

