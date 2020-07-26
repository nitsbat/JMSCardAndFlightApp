package card_app;

import model.Person;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDate;

public class CardApp {
  public static void main(String[] args) throws NamingException {

    InitialContext initialContext = new InitialContext();
    Topic topicQueue = (Topic) initialContext.lookup("topic/personTopic");

    try {
      ActiveMQConnectionFactory acitveConnection = new ActiveMQConnectionFactory();
      JMSContext context = acitveConnection.createContext();
      JMSProducer producer = context.createProducer();
      Person person = new Person();
      for (int i = 0; i < 10; i++) {
        producer.send(topicQueue, getPersonDetails(person));
        System.out.println(" Message " + (i + 1) + " successfully Sent .!");
      }

    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  private static Person getPersonDetails(Person person) {
    person.setName("Nitin");
    person.setContactNo(21312312L);
    person.setCardDate(LocalDate.of(2020, 07, 21));
    person.setBankName("SBI");
    person.setBalance(1000d);
    person.setAlertIsThere(true);
    return person;
  }
}
