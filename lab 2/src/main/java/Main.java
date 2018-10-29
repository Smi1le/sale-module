import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

@Slf4j
public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Journal journal = new Journal();
        journal.add("alexey");
        journal.add("alexander");
        int one = journal.find("alex");
        int two = journal.find("lex");
        log.info("Find {} -> {}", "alex", one);
        log.info("Find {} -> {}", "lex", two);
    }
}
