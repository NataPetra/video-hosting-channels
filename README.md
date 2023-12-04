# Video hosting channels

REST web service for working with video hosting channels on Spring Boot

## Getting Started

### Prerequisites
- Java 17
- Docker

### Technology Stack

#### Backend:
- Java 17
- Spring Boot 3.1.6
- Spring Data JPA
- Liquibase
- Gradle
- Lombok
- MapStruct

#### Database
- PostgreSQL

#### Containerization
- Docker
- docker-compose

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/NataPetra/video-hosting-channels.git
    ```
   
2. **Build and run the Docker containers:**
    ```bash
    docker-compose up --build
    ```

## Usage

- creating and editing user information
- creating and editing channel information
- user subscription to the channel
- unsubscribe the user from the channel
- displaying a list of all channels with pagination and filtering by name, language and category
- displaying a list of all user subscriptions (only channel names)
- receiving detailed information on the channel

### Example API Requests:

1. **Creating and editing user information:**
    ```bash
   curl --location 'http://localhost:8080/users' \
   --header 'Content-Type: application/json' \
   --data-raw '{
      "login": "name",
      "name": "John",
      "email": "john@example.com"
   }'
   
    curl --location --request PUT 'http://localhost:8080/users/6' \
    --header 'Content-Type: application/json' \
    --data-raw '{
       "login": "name",
       "name": "John A",
       "email": "john@example.com"
    }'
    ```

2. **Creating and editing channel information:**
    ```bash
    curl --location 'http://localhost:8080/channels' \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "Channel Name",
        "description": "Channel Description",
        "authorId": 1,
        "languageId": 2,
        "image": "123456789",
        "categoryId": 1
    }'
   
   curl --location 'http://localhost:8080/channels/image?uuid=123456789' \
   --form 'image=@"/path/name.png"'
   
   curl --location --request PUT 'http://localhost:8080/channels/4' \
   --header 'Content-Type: application/json' \
   --data '{
        "name": "Channel Name Change",
        "description": "Channel Description",
        "authorId": 1,
        "languageId": 2,
        "image": "123456789",
        "categoryId": 1
   }'
    ```

3. **User subscription to the channel:**
    ```bash
    curl --location --request POST 'http://localhost:8080/channels/4/subscribe/5'
    ```

4. **Unsubscribe the user from the channel:**
    ```bash
    curl --location --request POST 'http://localhost:8080/channels/4/unsubscribe/5'
    ```

5. **Displaying a list of all channels with pagination and filtering by name, language and category:**
    ```bash
    curl --location 'http://localhost:8080/channels/all?name=Channel&page=0&size=10&language=Russian&category=Food'
   
    curl --location 'http://localhost:8080/channels/image/123456789'
    ```

6. **Displaying a list of all user subscriptions (only channel names):**
    ```bash
    curl --location 'http://localhost:8080/users/1/subscriptions'
    ```

7. **Receiving detailed information on the channel:**
    ```bash
    curl --location 'http://localhost:8080/channels/1'
    ```