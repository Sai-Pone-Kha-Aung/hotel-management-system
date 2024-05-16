# Hotel Management System

This is a Hotel Management System implemented in Java with JavaFX for the user interface and MySQL for the database. It includes features for hotel booking, spa booking, food ordering, and provides two different interfaces for users and administrators.

## Features

- **User Interface**
  - Hotel Booking: Users can browse available rooms and book them for specific dates.
  - Spa Booking: Users can schedule spa appointments.
  - Food Ordering: Users can order food from the hotel's restaurant.

- **Admin Interface**
  - Room Management: Admins can add, edit, or remove rooms from the system.
  - Booking Management: Admins can view and manage bookings made by users.
  - Spa Management: Admins can manage spa services and appointments.
  - Menu Management: Admins can update the restaurant menu and manage food orders.

## Technologies Used

- **Java**: The core programming language used for the backend logic.
- **JavaFX**: For building the graphical user interfaces.
- **MySQL**: Database management system for storing hotel, spa, and restaurant data.
- **JDBC**: Java Database Connectivity for connecting Java applications with the MySQL database.

## Installation

1. Clone the repository:

   ```
   git clone https://github.com/Sai-Pone-Kha-Aung/hotel-management-system.git
   ```

2. Import the project into your favorite Java IDE.

3. Set up MySQL database:
   - Create a MySQL database named `hotel_management`.
   - Import the provided SQL schema (`hotel_management.sql`) to create the necessary tables and data.

4. Configure database connection:
   - Open `src/Database/ConnectionProvider.java`.
   - Modify the `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` constants to match your MySQL configuration.

5. Build and run the project.

## Usage

- **User Interface**:
  - Users can log in or sign up.
  - After logging in, users can access the hotel booking, spa booking, and food ordering features.

- **Admin Interface**:
  - Admins can log in using predefined credentials.
  - After logging in, admins can access the management functionalities such as room management, booking management, spa management, and menu management.

## Contributors

- [Sai Pone Kha Aung](https://github.com/Sai-Pone-Kha-Aung)
  
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
