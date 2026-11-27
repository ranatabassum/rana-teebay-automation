# TeeBay Automation Challenge

## Project Overview

This repository contains the automation testing solution and supporting documentation for the TeeBay Buggy application assessment.

## Tech Stack

- Java 21
- Playwright Java
- Maven
- JUnit 5

## Automated Test Coverage

### Authentication
- Login

### Product Management
- Create Product
- Update Product
- Delete Product
- View Product Details

### Product Browsing
- Browse Products
- Product Filtering

### User Features
- Account Settings Update
- Buy Product
- Rent/Borrow Product

## Project Structure

```text
src/test/java

├── base
├── pages
├── tests
└── utils

src/test/resources

├── config
└── testdata
```

## Prerequisites

Before running the project, ensure the following are installed:

- Java 21 or later
- Maven
- Node.js
- Playwright Browsers

## Installation

Clone the repository:

```bash
git clone <repository-url>
```

Navigate to the project directory:

```bash
cd rana-teebay-automation
```

Install dependencies:

```bash
mvn clean install
```

## Execute Tests

Run all tests:

```bash
mvn test
```

Run a specific test:

```bash
mvn -Dtest=LoginTest test
```

## Testing Approach

The application was evaluated using:

- Happy Path Testing
- Exploratory Testing
- Validation Testing
- Boundary Value Analysis
- UI/Wireframe Verification

## Deliverables

This submission includes:

- Playwright automation framework
- Automated test cases
- Bug report document
- AI usage disclosure document

## AI Usage

AI-assisted development was used for:

- Framework setup guidance
- Playwright implementation assistance
- Automation design review
- Bug reporting standardization
- Documentation preparation

Detailed prompt history is available in the AI_USAGE.md file.
