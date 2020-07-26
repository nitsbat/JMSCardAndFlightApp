package alert_app;

import model.Person;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AlertApp {
  public static void main(String[] args) throws NamingException {

    InitialContext initialContext = new InitialContext();
    Topic topicQueue = (Topic) initialContext.lookup("topic/personTopic");

    try {
      ActiveMQConnectionFactory activeConnection = new ActiveMQConnectionFactory();
      JMSContext context = activeConnection.createContext();

      JMSConsumer sharedConsumer1 = context.createSharedConsumer(topicQueue, "alertConsumer 1");
      JMSConsumer sharedConsumer2 = context.createSharedConsumer(topicQueue, "alertConsumer 2");

      for (int i = 0; i < 10; i += 2) {
        Message receive1 = sharedConsumer1.receive();
        Message receive2 = sharedConsumer2.receive();
        printForReceiver(receive1.getBody(Person.class));
        printForReceiver(receive2.getBody(Person.class));
      }

    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  private static void printForReceiver(Person person) {
    if (person.isAlertIsThere()) {
      System.out.println("Hey " + person.getName() + "..!!\nAlert - You have some notifications");
      if (person.getBalance() < 5000) {
        System.out.println(
            "Your bank account of "
                + person.getBankName()
                + " has balance("
                + person.getBalance()
                + ") lower than Rs 5000. Please deposit now.");
        System.out.println("*****************************");
      } else {
        System.out.println(
            "Your bank account of "
                + person.getBankName()
                + " has balance of Rs "
                + person.getBalance()
                + ".");
        System.out.println("*****************************");
      }
    } else {
      System.out.println("Hey " + person.getName() + "..!!\nSorry - No new Alerts");
      System.out.println("*****************************");
    }
  }
}
