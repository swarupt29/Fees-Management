
![Logo](https://github.com/swarupt29/Fees-Management/assets/118194258/3514b1cf-3b84-4f2c-8e55-ea2c9aefbf8e)


# Fees Management System

The Fees Management System is a Java application created as part of the Coders Cave virtual internship. It simplifies fee record management for educational institutions and organizations.

## Table of Contents
- Features
- Installation
- Usage
- Contributing
- Licence

## Features
- View, add, update, and delete fee records.
- User-friendly graphical interface.

## Installation

1. **Clone this repository or download the source code**

```bash
  git clone https://github.com/swarupt29/Fees-Management.git
```
2. **Compile the Java source code to create the executable JAR file**

```bash
  javac FeesManagementSystem.java
```
3. **Run the application**

``` bash
  java FeesManagementSystem
```

## Installation with Eclipse

Follow these steps to set up and run the Random Password Generator project using Eclipse:

1. **Clone or Download the Repository**: 
   - Clone this repository using `https://github.com/swarupt29/Fees-Management.git` or download the source code as a ZIP archive and extract it.

2. **Open Eclipse**:
   - Launch Eclipse if it's not already open.

3. **Import the Project**:
   - Go to `File > Open Projects from File System...` in Eclipse.

4. **Select the Project Folder**:
   - Click on "Directory" and navigate to the folder where you cloned or extracted the project.

5. **Import as a General Project**:
   - Eclipse may recognize it as a general project. If it does, select the project and click "Finish."

6. **Set Up the Java Environment**:
   - Ensure you have the Java Development Kit (JDK) and Java Runtime Environment (JRE) properly configured in your Eclipse workspace.

7. **Run the Application**:
   - Locate the `RandomPasswordGeneratorGui.java` file in the project.
   - Right-click on it and select `Run As > Java Application`.

8. **Use the Random Password Generator**:
   - The application window will open, allowing you to customize and generate random passwords.

## Database
In the source code, locate the following line:

```
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fees", "root", "root");
```

Modify the connection URL, username and password as needed.

- MySQL
  
    ```
    connection = DriverManager.getConnection("jdbc:mysql://your-database-host:your-port/your-database-name", "your-username", "your-password");
    ```

**Syntax for other Databases**
  - PostgreSQL
    ```
    connection = DriverManager.getConnection("jdbc:postgresql://your-database-host:your-port/your-database-name", "your-username", "your-password");
    ```

- SQLite
    ```
    connection = DriverManager.getConnection("jdbc:sqlite:/path/to/your/database/file.db");
    ```

**Table Creation Query for this project**
```
CREATE TABLE YourTableName (
    Name VARCHAR(255),
    Class VARCHAR(255),
    Contact VARCHAR(255),
    Amount DECIMAL(10, 2)
);
```
**If you dont wan't to add data one by one manually then paste the following command**

```
INSERT INTO YourTableName (Name, Class, Contact, Amount)
VALUES
    ('Name1', 'Class1', 'Contact1', 4923.45),
    ('Name2', 'Class2', 'Contact2', 4987.60),
    ('Name3', 'Class3', 'Contact3', 4950.75),
    ('Name4', 'Class4', 'Contact4', 4912.30),
    ('Name5', 'Class5', 'Contact5', 4978.25),
    ('Name6', 'Class6', 'Contact6', 4945.80),
    ('Name7', 'Class7', 'Contact7', 4930.50),
    ('Name8', 'Class8', 'Contact8', 4965.90),
    ('Name9', 'Class9', 'Contact9', 4905.10),
    ('Name10', 'Class10', 'Contact10', 4999.99);
```

## Usage
1. Launch the application.
   
     ![Alt text](https://github.com/swarupt29/Fees-Management/assets/118194258/f471b99c-8c06-4a56-a625-b52f62d8f058)
   
2. Use the provided options to manage fee records:

   - **View Data:** To view a list of fee records.
     
   ![Alt text](https://github.com/swarupt29/Fees-Management/assets/118194258/f471b99c-8c06-4a56-a625-b52f62d8f058)


   - **Add Entry:** To add a new fee entry with student details and fee amount.
     
   ![Alt text](https://github.com/swarupt29/Fees-Management/assets/118194258/bbefceb2-a51c-4a53-b85b-1ec01573477b)  
     
   - **Update Entry:** To update an existing fee entry.
     
   ![Alt text](https://github.com/swarupt29/Fees-Management/assets/118194258/d6ef0ef8-18ea-4725-8069-cda05ffe8b89)
     
   - **Delete Entry:** To delete a fee entry.
     
   ![Alt text](https://github.com/swarupt29/Fees-Management/assets/118194258/9b692def-56ee-495a-bcb0-82e76c98eb75)
     

3. Perform database operations as needed.


# Contributing
Contributions to this project are welcome! If you find a bug or have an enhancement in mind, please open an issue or create a pull request with your changes. Be sure to follow the project's coding standards and guidelines.

# License
This project is not licensed, which means you are free to use it as you see fit. You can use, modify, and distribute the software without any specific licensing restrictions.
