# Baby Name Data Analyzer

**Baby Name Data Analyzer** is a Java-based application designed to analyze and query large datasets of baby names, their frequencies, and popularity trends over various years. This tool allows users to interactively retrieve data insights such as the most popular names for a given year and gender, the rank of a specific name, and the year when a name-gender combination was most popular.

## Features

- **Interactive Query Interface**: Users can easily interact with the application to get answers to specific queries about baby names and their popularity.
- **Data Loading and Parsing**: Efficiently loads and parses large text datasets of baby names using simple Java file I/O.
- **Custom Data Structures**: Uses efficient data structures to store and retrieve data, optimizing for speed and memory usage.
- **Modular Design**: Adopts a modular object-oriented design, separating concerns into distinct components for UI, data handling, and business logic.

## Industry-Standard Technologies and Techniques Used

- **Java Programming Language**: Utilizes Java, one of the most widely used programming languages in the industry, known for its platform independence, performance, and robustness.
- **Java Collections Framework**: Leverages the Java Collections Framework for efficient data storage and retrieval, using `Map`, `List`, and other data structures to manage in-memory datasets.
- **File I/O with `java.io`**: Employs Java’s simple file handling classes (`File` and `Scanner`) for reading large datasets, demonstrating a clear and straightforward approach to file management without reliance on advanced libraries.
- **Object-Oriented Programming (OOP)**: Follows OOP principles by organizing the application into classes (`UIHandler`, `DataHandler`, `LogicHandler`) that encapsulate specific responsibilities, promoting code reuse, and maintainability.
- **Data-Driven Design**: Uses data-driven design techniques to ensure that the application can handle varying datasets dynamically and efficiently.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher installed.
- A dataset of baby names, formatted as text files, stored in a specific folder.

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/baby-name-data-analyzer.git
   cd baby-name-data-analyzer
