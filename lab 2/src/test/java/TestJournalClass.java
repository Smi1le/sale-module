import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Test;

public class TestJournalClass {
    private Journal journal;

    public TestJournalClass() {
        journal = new Journal();
        fillJournal(journal);
        BasicConfigurator.configure();
    }

    @Test
    public void Check_If_Alex_Is_Exists() {
        int count = journal.find("alex");
        Assert.assertTrue(count > 0);
    }

    @Test
    public void Check_If_Alex_Equal_Two() {
        int count = journal.find("alex");
        Assert.assertTrue(count == 2);
    }

    @Test
    public void Check_Is_Kim_Not_Exists() {
        int count = journal.find("kim");
        Assert.assertTrue(count == 0);
    }

    @Test
    public void Check_Is_Letters_Start_With_B_Ten_Number() {
        int count = journal.find("b");
        Assert.assertTrue(count == 10);
    }

    @Test
    public void Check_Is_Word_Russian_Is_One() {
        int count = journal.find("russian");
        Assert.assertTrue(count == 1);
    }
    private void fillJournal(Journal journal) {
        journal.add("alexey");
        journal.add("alexander");
        journal.add("kirim");
        journal.add("pupok");
        journal.add("julia");
        journal.add("sveta");
        journal.add("kolya");
        journal.add("dima");
        journal.add("artem");
        journal.add("nastya");
        journal.add("sergey");
        journal.add("kukushka");
        journal.add("bikini");
        journal.add("flower");
        journal.add("mother");
        journal.add("father");
        journal.add("brother");
        journal.add("sister");
        journal.add("colonel");
        journal.add("captain");
        journal.add("bill");
        journal.add("jim");
        journal.add("bear");
        journal.add("bill");
        journal.add("bred");
        journal.add("bread");
        journal.add("home");
        journal.add("taxi");
        journal.add("fristailo");
        journal.add("frisbee");
        journal.add("kill");
        journal.add("soup");
        journal.add("gangsta");
        journal.add("nothing");
        journal.add("drop");
        journal.add("football");
        journal.add("basketball");
        journal.add("volleyball");
        journal.add("kitchen");
        journal.add("dron");
        journal.add("basic");
        journal.add("zed");
        journal.add("red");
        journal.add("green");
        journal.add("indigo");
        journal.add("rom");
        journal.add("rome");
        journal.add("whiskey");
        journal.add("house");
        journal.add("price");
        journal.add("cost");
        journal.add("call");
        journal.add("cat");
        journal.add("motorcycle");
        journal.add("cycle");
        journal.add("journal");
        journal.add("live");
        journal.add("cook");
        journal.add("bathroom");
        journal.add("food");
        journal.add("feed");
        journal.add("fork");
        journal.add("deep");
        journal.add("east");
        journal.add("english");
        journal.add("russian");
    }
}
