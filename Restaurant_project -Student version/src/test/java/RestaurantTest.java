import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
class RestaurantTest {
    Restaurant restaurant=Mockito.mock(Restaurant.class);
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant r = new Restaurant("ahaaar","mumbai",openingTime , closingTime);

    Restaurant rspied = Mockito.spy(r);


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime mockCurrTime = LocalTime.parse("11:20");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertTrue(rspied.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime mockCurrTime = LocalTime.parse("23:20");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertFalse(rspied.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Part3 Total Order Value>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    

//    @Test
//    public void calculte_total_ordervalue_given_menu_list_in_string() {
//        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
//        restaurant.addToMenu("Sweet corn soup",119);
//        restaurant.addToMenu("Vegetable lasagne", 269);
//        restaurant.addToMenu("Mutton lasagne", 469);
//        restaurant.addToMenu("Vegetable biriyani", 369);
//        List<String> Selectedmenu = new ArrayList<String>();
//        //Test1
//        Selectedmenu.add(restaurant.getMenu().get(0).getName());
//        Selectedmenu.add(restaurant.getMenu().get(3).getName());
//        int totalOrderValue = restaurant.totalOrderValue(Selectedmenu);
//        Assertions.assertEquals(totalOrderValue,488);
//
//        // Test2
//        Selectedmenu.add(restaurant.getMenu().get(2).getName());
//        totalOrderValue = restaurant.totalOrderValue(Selectedmenu);
//        Assertions.assertEquals(totalOrderValue,957);
//
//    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<Part3 Total Order Value>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}