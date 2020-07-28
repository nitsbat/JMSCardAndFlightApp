# JMSCardAndFlightApp

The application contains two programs w.r.t JMS2.x and Messsaging Queue. Flight Checkin App is done using Point to Point communication while Credit Card App is done using Pub-Sub method.

The Project will require a JMS provider which in my case is ActiveMQ.
Download Apache ActiveMq artemis from here https://activemq.apache.org/components/artemis/download/

Go to Apache artemis bin folder and follow the below steps :
* Create a broker - Run this command under bin folder ./artemis create //location-path
* Provide default settings for all.
* Then go to the location-path where the broker is created and go to its bin folder.
* run teh broker with the command - ./artemis run

#### The console of the broker will be visible at http://localhost:8161/console.

Fork the project and you can add logic to either of the above two application.

