package security_app;

import model.Person;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDate;

public class SecurityApp {
  public static void main(String[] args) throws NamingException {
    InitialContext initialContext = new InitialContext();
    Topic topicQueue = (Topic) initialContext.lookup("topic/personTopic");

    try {
      ActiveMQConnectionFactory activeConnection = new ActiveMQConnectionFactory();
      JMSContext context = activeConnection.createContext();
      context.setClientID("client1");
      JMSConsumer durableConsumer1 =
          context.createDurableConsumer(topicQueue, "securityConsumer 1");
      durableConsumer1.close();

      Thread.sleep(10000);

      JMSConsumer durableConsumer2 =
          context.createDurableConsumer(topicQueue, "securityConsumer 1");
      for (int i = 0; i < 10; i++) {
        Message receive = durableConsumer2.receive();
        Person person = receive.getBody(Person.class);
        if (person.getCardDate().isAfter(LocalDate.now())) {
          System.out.println("Hey " + person.getName() + ". Please Secure your account.");
          System.out.println("**********************************");
        } else {
          System.out.println("Hey " + person.getName() + ". Your Account is secured.");
          System.out.println("**********************************");
        }
      }

    } catch (Exception ex) {
      System.out.println(ex);
    }
  }
}
