# Flexiart POS System

Flexiart POS System is a Java-based Point of Sale (POS) software designed to streamline sales, manage inventory, and generate reports for businesses. This README provides an overview of the project's key details and functionalities.

## Project Details

- **Language:** Java
- **Builder:** Ant
- **Java Platform:** JDK 1.7
- **Database:** H2 Embedded Database
- **File Location Path:** C:/Flexiart POS
- **Main Class:** home
- **Libraries:**
  - AbsoluteLayout
  - commons-beanutils-1.8.2
  - commons-collections-3.2.1
  - commons-digester-2.1
  - commons-javaflow-20060411
  - commons-logging-1.1
  - groovy-all-2.0.1
  - h2-1.3.176
  - iText-2.1.7.js2
  - jasperreports-5.6.0
  - jcalendar-1.4
  - jfreechart-1.0.12
  - mysql-connector-java-5.1.22-bin
  - poi-3.7-20101029

## Panels

The POS system consists of the following panels:

- Sales
- Supplier
- Employee
- Product
- Customers
- Invoice
- GRN (Purchases)
- Stock
- Return (Not Implemented)
- Reports

## Key Functions

### Sales Panel

- Select a customer
- Select a product (via combo box or by typing barcode)
- Add products to the cart
- Remove items from the cart
- Clear the entire cart
- Calculate sub-total, shipping cost, tax rate, discount %, grand total, and balance
- Pay and print the invoice

### Supplier Panel

- Add, search, update, and delete suppliers
- View supplier reports

### Employee Panel

- (Similar to Supplier Panel)

### Product Panel

- (Similar to Supplier Panel)

### Customers Panel

- (Similar to Supplier Panel)

### Invoice Panel

- View all invoice details
- Search invoices by ID, customer, or status (paid, unpaid, partial)

### GRN Panel

- Add purchase orders
- Calculate sub-total, discount %, and net total
- Save and manage GRNs
- View GRN reports

### Stock Panel

- View all stock details and total value
- Search stock by barcode or product
- Add new quantities to stock

### Return Panel

- (Not Implemented)

### Reports Panel

- View sales reports
- View sales by invoice ID
- (Additional reports may be added in the future)

## Installation

To run the Flexiart POS System:

1. Ensure you have JDK 1.7 or higher installed.
2. Clone this repository.
3. Build the project using Ant.
4. Run the application.

## Contributing

Contributions to this project are welcome! Feel free to submit bug reports, feature requests, or pull requests.

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE License - see the [LICENSE](LICENSE) file for details.
