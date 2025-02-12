# ATM Management System - Secure Bank

## Overview

This project is a simple Java-based ATM banking system with a graphical user interface (GUI) implemented using Java Swing. It allows users to set and authenticate a PIN, deposit and withdraw money in multiple currencies, and view real-time currency conversions.

## Features

- **PIN Authentication:** Users can set a PIN and must authenticate before performing transactions.
- **Deposit & Withdraw:** Users can deposit or withdraw money in multiple currencies (INR, USD, EUR, GBP).
- **Currency Conversion:** Automatically converts deposited/withdrawn amounts to INR for internal balance management.
- **Balance Display:** Displays the account balance in INR and allows conversion into selected currencies.
- **Exit Button:** Allows users to close the application safely.
- **User-Friendly GUI:** A well-structured, easy-to-use interface with color themes for clarity.

## Technologies Used

- **Java** - Programming language
- **Swing (Javax.swing)** - GUI framework
- **AWT (Java.awt)** - Event handling and UI management

## Installation & Usage

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/Kanishk101/atm-banking-system.git
   ```
2. **Navigate to the Project Directory:**
   ```sh
   cd atm-banking-system
   ```
3. **Compile the Java Program:**
   ```sh
   javac Bank.java
   ```
4. **Run the Program:**
   ```sh
   java Bank
   ```

## How to Use

1. **Set PIN:** Enter a PIN and click `Set PIN` to initialize authentication.
2. **Authenticate:** Enter the same PIN and click `Authenticate` to unlock transactions.
3. **Deposit Money:**
   - Enter an amount.
   - Select a currency (INR, USD, EUR, GBP).
   - Click `Deposit` to add money.
4. **Withdraw Money:**
   - Enter an amount.
   - Select a currency.
   - Click `Withdraw` (ensuring sufficient balance).
5. **View Conversion Rates:**
   - The bottom section shows conversion rates for all supported currencies.
6. **Exit Application:** Click `Exit` to close the application.

## Example Usage

```
1. Set PIN: 1234
2. Authenticate using PIN: 1234
3. Deposit: 50 USD → Converted to INR
4. Withdraw: 500 INR
5. View updated balance & currency conversions
```

## Future Enhancements

- **Database Integration:** Store user balances persistently.
- **Multi-user Support:** Different users with individual balances.
- **ATM Simulation:** Add card insertion and transaction history.
- **Networked Banking:** Online transaction handling.

## License

This project is licensed under the MIT License.

## Author

Kanishk Srivastava - [kanishk.s2015@gmail.com](mailto\:kanishk.s2015@gmail.com)

