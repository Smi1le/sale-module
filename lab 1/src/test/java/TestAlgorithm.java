import javafx.util.Pair;
import model.Distance;
import org.junit.Assert;
import org.junit.Test;

public class TestAlgorithm {

    @Test
    public void Max_And_Min_Distance_Between_Words_Five() {
        String text = "Today is very good a sunny day";
        String word1 = "day";
        String word2 = "Today";
        Distance distance = Algorithm.calculate(text, word1, word2);
        Assert.assertTrue(distance.getMin() == 5);
        Assert.assertTrue(distance.getMax() == 5);
    }

    @Test
    public void Max_Distance_Equal_Twelve_And_Min_Distance_Equal_Five() {
        String text = "Today is very good a sunny day and tomorrow will be very good day too.";
        String word1 = "day";
        String word2 = "Today";
        Distance distance = Algorithm.calculate(text, word1, word2);
        Assert.assertTrue(distance.getMin() == 5);
        Assert.assertTrue(distance.getMax() == 12);
    }

    @Test
    public void Max_Distance_Equal_Eleven_And_Min_Distance_Equal_Five() {
        String text = "Today is very good a sunny day and tomorrow will be very day too.";
        String word1 = "Today";
        String word2 = "day";
        Distance distance = Algorithm.calculate(text, word1, word2);
        Assert.assertTrue(distance.getMin() == 5);
        Assert.assertTrue(distance.getMax() == 11);
    }

    @Test
    public void Max_Distance_Equal_Thirteen_And_Min_Distance_Equal_Five() {
        String text = "Today is very good a sunny day and tomorrow will be very good day day too.";
        String word1 = "Today";
        String word2 = "day";
        Distance distance = Algorithm.calculate(text, word1, word2);
        Assert.assertTrue(distance.getMin() == 5);
        Assert.assertTrue(distance.getMax() == 13);
    }
}
