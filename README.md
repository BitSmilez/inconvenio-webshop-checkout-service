# inconvenio-webshop-checkout-service

This is the Checkout Microservice, responsible for managing customer orders and handling checkout-related events. It provides a RESTful API to interact with the orders and integrates with a message queue for processing the orders. Features:

    Process customer orders through a message queue
    Retrieve the latest order by user ID
    Retrieve all orders by user ID
    Message queue integration

Requirements

    Java 11+
    Spring Boot
    Docker
    Docker Compose

## Installation

Clone the repository:



    git clone https://github.com/BitSmilez/inconvenio-webshop-checkout-service.git

## Usage

Navigate to the project directory:




To start the Checkout Microservice using Docker, run the following command:

    docker-compose up

This will start the microservice and any required infrastructure, such as a message queue, in separate containers. The API will be accessible on http://localhost:8080.
API Endpoints

    GET /checkout/latest/{userID}: Retrieve the latest order made by the user with the given userID.
    GET /checkout/all/{userID}: Retrieve all orders made by the user with the given userID.
    POST /checkout/queue: Publish a checkout event to the message queue for processing.
