# Vehicle Management System

This is a comprehensive Vehicle Management System developed using Java, JavaFX, and MySQL, designed to manage vehicle-related operations efficiently. The system includes features like customer management, vehicle details tracking, stock management, reservation handling, and service management, along with additional functionalities such as email notifications and report generation.

## Features

- **Dashboard**: Overview of key metrics like available stock, monthly vehicle arrivals, and recent reservations.
- **Customer Management**: Manage customer information, including personal details, vehicle ownership, and email notifications.
- **Vehicle Management**: Track and manage various types of vehicles, their details, and associated services.
- **Stock Management**: Monitor and update available stock for essential items like engine oil, cleaning liquid, and gear oil.
- **Reservation System**: Manage vehicle reservations, including date, time, and customer details. Automatic email notifications are sent to customers upon reservation.
- **Service Management**: Track and manage vehicle services. Customers can make appointments for vehicle services, and the system sends confirmation emails automatically.
- **Employee Management**: Manage employee details and roles within the system.
- **Invoice Generation**: Generate invoices for customer payments, detailing services rendered and costs incurred.
- **Service Report Generation**: Use JasperReports to generate detailed service reports for customers.
- **Forgot Password**: Admin users can recover their password via an email code sent through the system.
- **Utilities**: Includes tools like a calculator and notepad for quick access during operations.

## Technologies Used

- **Java**: Core programming language used for application logic.
- **JavaFX**: Used for building the graphical user interface (GUI).
- **MySQL**: Relational database used for storing and managing data.
- **JasperReports**: Used for generating invoices and service reports.
- **JavaMail API**: Used for sending email notifications to customers.
- **JDK 11**: Java Development Kit version used for development.

## Installation and Setup

1. **Clone the Repository**: Clone the project from the repository to your local machine.
   ```bash
   git clone <repository-url>

  

 ## Database Setup:
1. Install MySQL and create a new database for the application.
2. Import the provided SQL file to set up the database schema and initial data.

## Configure Database and Email Connection:

1. Update the database connection settings in the application (e.g., `DB_URL`, `DB_USER`, `DB_PASSWORD`).
2. Configure the email settings in the application (e.g., SMTP server, port, email credentials) to enable email notifications.

## Build and Run the Application:

1. Compile the Java code using JDK 11.
2. Run the application from your preferred IDE or terminal.

## Usage:

1. Launch the application and log in with your admin credentials.
2. Use the navigation menu on the left to access different modules such as Dashboard, Customer, Vehicles, Stocks, Reservation, Service, and Employee.
3. The application automatically sends email notifications to customers upon adding them to the system, making reservations, or scheduling services.
4. Generate invoices and service reports using the built-in JasperReports feature.
5. If the admin forgets their password, they can use the "Forgot Password" option to receive a recovery code via email.


## Dashboard 

![Screenshot from 2024-08-28 23-47-55](https://github.com/user-attachments/assets/e69cfd07-a737-41c7-8219-fdcd1398fbe2)



Contributing
If you'd like to contribute to this project, please fork the repository and submit a pull request.

License
This project is licensed under the MIT License - see the LICENSE file for details.

