
# RideOn - Cab booking application backend

This repository contains the backend implementation for a cab booking application, delivering services similar to Uber and other ride-hailing platforms.
The one who wants to reach their daily destination on time or the one who is enthusiastic about traveling and exploring new places, RideOn offers quick and secure ride services with its adaptive and strategic driver-matching patterns.

## Key Features

- **Ride Management:**
	 Booking rides with customizable pickup and drop-off locations. Real-time updates on ride status (e.g., conformed, ongoing, completed)

- **Driver Matching Strategies**
	Efficient algorithms to match riders with drivers based on pickup location and driver rating.
	
- **Dynamic Fare Calculation**
	Calculates ride fare based on Distance traveled, Surge pricing during peak hours.

- **Authentication and Authorization**
	JWT Authentication securely handles user login sessions. Role-based access control to manage permissions for passengers(riders), drivers, and admins.
	
- **Wallet and Payment Processing**
	Integrated wallet system for seamless payment. Wallet balance management, transaction history, and automatic fare deductions after ride completion
	
- **Event Notifications:**
	System can create and promote events, with passengers receiving timely email notification upon ride completion.
	
- **Review System**
	Empowering both passengers and drivers to provide and view ratings and feedback.


## Technologies 
 * **Back-End:** Spring Boot
 * **Database:** PostgreSQL 
 * **Authentication:** JWT, Spring Security
 * **Notifications:** Email notifications for even and booking updates
## Database Diagram

![Database Diagram](https://github.com/vinay-pentam/RideOn/blob/main/src/main/resources/static/UberApp_Database_Diagram.png?raw=true)

# API Endpoints (Overview)

| Endpoint | Method | Description |
| --- | --- | ---- |
| `/auth/signup` | POST  | Creates an account and issue a jwt. |
| `/auth/login`  | POST  | Authenticates the user and issues a jwt. |
| `/auth/onboardNewDriver/{id}`  | POST  |Allows only admins to onboard a new driver.|
| `/rider/requestRide/`  | POST  | Enables a rider to request a ride.  |
| `/driver/acceptRide/{id}`  | POST  | Allows drivers to accept a ride request. |
| `/rider/getAllMyRides`  | GET  | List the ride history of a rider. |
| `/driver/getAllMyRides`  | GET  |  List the ride history of a driver. |

# Get Started
## Prerequisites
- **Java 17+**
- **Postgres**
- **Maven**
## Setup and Installation
1. **Clone the repository to your local computer.**
```
  git clone https://github.com/vinay-pentam/RideOn
```
2. **Navigate to Project Directory**
```
  cd RideOn
```
3. **Add you JWT SecretKey and Mail credentials**
 - In resources/application.properties add you JWT SecretKey
 ```
 jwt.secretKey = YOUR_SECRET_KEY
```
- Create and add your Professional email and app passowrd
```
spring.mail.username = YOUR_MAIL@gmail.com
spring.mail.password = YOUR_APP_PASSWORD
```

> [!NOTE]
> Keep your secretKey and credentials secured

4. **Configure the Database:**
- Set up a PostgreSQL database.
- In the application.properties update the properties as needed.
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
```
5. **Access RideOn**
-  Open your browser and go to
```
  Open your browser and go to http://localhost:8080
```
## Future Enhancements
- **Pre-plan Ride:** Allow customers to pre-book their ride at a specified point in time.
- **UI/UX Improvements:** Introducing a responsive and visually appealing frontend interface to complement the backend functionalities.
- **Review System Enhancement:** Advanced driver rating and review system.

- **Payment System Enhancement:** Integration with third-party payment gateways.
## Feedback

I appreciate your feedback! Please open an issue on GitHub if you encounter any problems or have suggestions for improvement.\
vinaypentam0312@gmail.com


## Author

- [@vinay-pentam](https://www.github.com/vinay-pentam)

