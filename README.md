## 3-Tier Java Application ♨️    
This Java application follows a 3-tier architecture, incorporating RMI for communication between the client and server tiers. The backend is powered by MySQL for data storage using JDBC, and the front end utilizes Java Swing for a graphical user interface.

### Prerequisites

Before running the application, integrate the MySQL connector (JDBC) in your project.

Database configuration:

To run the application you have to make sure that you have a MySQL database created (`biblio`) that contains a table in which the subscribers are stored (`abonne`).

You can use a custom port number and table names as long as it is changed in the URL used in the code when connecting to the database.

### To run the application
1. Make sure all the prerequisites are fulfilled
1. Start the backend application `ServerImpl.java`
2. Start the client application `Abonne.java`

### Features
- RMI Communication: The application uses RMI for communication between the client and server tiers.
- JDBC and MySQL Integration: JDBC is employed to connect to a MySQL database for data storage and retrieval.
- Swing Frontend: Java Swing is utilized to create a graphical user interface for a seamless user experience.

Feel free to contribute and enhance the application!
