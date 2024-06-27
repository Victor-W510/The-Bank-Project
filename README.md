The-Bank-Project
Overview

The-Bank-Project is a Java-based application designed to provide a simple yet effective banking system interface using Java Swing for the GUI and database management for storing and retrieving data. The project uses JFrame for window management and various Java Swing components to create a user-friendly interface. Additionally, the project utilizes ObjectOutputStream and FileOutputStream for serializing and saving object data to files.
Features

    User Authentication: Allows users to log in and create accounts.
    Account Management: Users can view and manage their bank accounts.
    Transactions: Supports deposit and withdrawal among others.
    Transaction History: Users can view a history of their transactions.
    Database Integration: A relational database is used for storing user and transaction data.
    Object Serialization: Uses ObjectOutputStream and FileOutputStream to serialize and save account and customer objects to files.

Technologies Used

    Java: The core programming language used for developing the application.
    Java Swing: Used for building the graphical user interface (GUI).
    JFrame: Utilized for window management.
    ObjectOutputStream and FileOutputStream: Used for serializing and saving objects to files.

Getting Started
Prerequisites

    JDK 8 or higher
    An IDE (e.g., IntelliJ IDEA, Eclipse)

Installation

    Clone the repository:

    sh
    git clone https://github.com/Victor-W510/The-Bank-Project.git

Navigate to the project directory:

sh
cd The-Bank-Project


Compile and run the application:

sh

    javac -d bin src/**/*.java
    java -cp bin com.vicwes1.Main

Project Structure

bash

/The-Bank-Project
│
├── /src
│   ├── /com
│   │   ├── /vicwes1
│   │   │   ├── Account.java
│   │   │   ├── BankLogic.java
│   │   │   ├── CreditAccount.java
│   │   │   ├── Customer.java
│   │   │   ├── GUI.java
│   │   │   ├── GUI2.java
│   │   │   ├── HelpGetCustomer.java
│   │   │   ├── ListOfCustomers.java
│   │   │   ├── ListOfAccounts.java
│   │   │   ├── Main.java
│   │   │   ├── SavingsAccount.java
│   │   │   └── /utils
│   │   │       └── Utils.java
│   └── /resources
└── /bin

Usage

The application's GUI includes the following main components:

    Search Functionality: Allows users to search for customers.
    Create Customer: Provides an interface for creating new customer accounts.
    Get all Customers: Retrieves and displays a list of all customers.

Example Code for Object Serialization

This project makes use of ObjectOutputStream and FileOutputStream to save the state of account and customer objects to files. Here’s an example of how they are used:

java

// Example of saving an object to a file
try (FileOutputStream fileOut = new FileOutputStream("account.ser");
     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
    out.writeObject(account);
} catch (IOException i) {
    i.printStackTrace();
}

// Example of loading an object from a file
try (FileInputStream fileIn = new FileInputStream("account.ser");
     ObjectInputStream in = new ObjectInputStream(fileIn)) {
    Account account = (Account) in.readObject();
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}

Contributing

    Fork the repository.
    Create a new branch (git checkout -b feature-branch).
    Commit your changes (git commit -m 'Add some feature').
    Push to the branch (git push origin feature-branch).
    Open a Pull Request.

