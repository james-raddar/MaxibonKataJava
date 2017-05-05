package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    public static final String DEVELOPER_NAME = "Pedro";
    private KarumiHQ karumiHq;
    @Mock
    private Chat mockChat;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        karumiHq = new KarumiHQ(mockChat);
    }

    // Test 4
    @Property
    public void there_are_always_maxibons_after_developer_visits_fridge(@From(DevelopersGenerator.class) Developer developer) throws Exception {
        karumiHq.openFridge(developer);

        assertTrue(karumiHq.getMaxibonsLeft() > 2);
    }

    // Test 5
    @Property
    public void there_are_always_between_two_and_twelve_maxibons_after_developer_visits_fridge(@From(DevelopersGenerator.class) Developer developer) throws Exception {
        karumiHq.openFridge(developer);

        assertTrue(karumiHq.getMaxibonsLeft() > 2);
        assertTrue(karumiHq.getMaxibonsLeft() <= 12);
    }

    // Test 6
    @Property
    public void there_are_always_between_two_and_twelve_maxibons_after_developer_group_visits_fridge(List<@From(DevelopersGenerator.class) Developer> developers) throws Exception {
        karumiHq.openFridge(developers);

        assertTrue(karumiHq.getMaxibonsLeft() > 2);
        assertTrue(karumiHq.getMaxibonsLeft() <= 12);
    }

    // Test 7
    @Property
    public void hungry_developer_requests_maxibons(@From(HungryDevelopersGenerator.class) Developer developer) throws Exception {
        karumiHq.openFridge(developer);

        assertTrue(karumiHq.getMaxibonsLeft() >= 10);
        verify(mockChat).sendMessage("Hi, I'm " + developer.getName() + " and we are running out of Maxibons");
        verifyNoMoreInteractions(mockChat);
    }

    // Test 8
    @Property
    public void not_so_hungry_developer_requests_maxibons(@From(NotSoHungryDevelopersGenerator.class) Developer developer) throws Exception {
        karumiHq.openFridge(developer);

        assertTrue(karumiHq.getMaxibonsLeft() <= 10);
        verify(mockChat, never()).sendMessage(anyString());
    }

    // Test 9
    @Test
    public void is_developer_takes_4_there_are_6_maxibons_remaining() throws Exception {
        Developer developer = new Developer(DEVELOPER_NAME, 4);

        karumiHq.openFridge(developer);

        assertEquals(6, karumiHq.getMaxibonsLeft());
    }

    // Test 10
    @Test
    public void is_developer_takes_9_there_are_11_maxibons_remaining() throws Exception {
        Developer developer = new Developer(DEVELOPER_NAME, 9);

        karumiHq.openFridge(developer);

        assertEquals(11, karumiHq.getMaxibonsLeft());
    }

    // Test 11
    @Test
    public void is_developer_takes_15_there_are_10_maxibons_remaining() throws Exception {
        Developer developer = new Developer(DEVELOPER_NAME, 15);

        karumiHq.openFridge(developer);

        assertEquals(10, karumiHq.getMaxibonsLeft());
    }

    // Test 12
    @Property
    public void after_developer_open_fridge_there_are_more_than_2_maxibons_remaining(
            @From(KarumiesGenerator.class) KarumiHQ karumiHQ, @From(DevelopersGenerator.class) Developer developer) throws Exception {
        karumiHQ.openFridge(developer);

        assertTrue(karumiHQ.getMaxibonsLeft() > 2);
    }
}
