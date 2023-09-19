# REST API to manage a task list in JAVA (To-Do List)
This application is a REST API developed with Spring Boot that allows the management of a task list (to-do list). Each task has a title, a description, and a status (pending, completed). The application provides the following operations:


-) Create a new task 

-) Get a list of all tasks.

-) Get a task by its ID.

-) Update the status of a task (pending/completed).

-) Delete a task by its ID.

# Requirements
-) Java 8 or higher installed on your system.

-) Maven installed on your system.

-) A configured and accessible PostgreSQL instance. You must configure the database credentials in the application.properties file.

# Execution
Follow these steps to run the app:

1. Clone or download this repository to your local machine.
2. Configure the PostgreSQL database by editing the src/main/resources/application.yml file. Make sure you provide the correct URL, username, and password.
   datasource:

   url: jdbc:postgresql://localhost:5432/seeri
   
    username:  #User the db 
   
    password:  #Passw the db
3. Open a terminal in the root directory of the project and run the following command to build and run the application:
   mvn spring-boot:run

   The application will run at http://localhost:9003. You can change the port in the application.properties file if necessary.
# API endpoints
## Type State 
* ### Get a list of all type state

  patch: '/api/v1/full-types-states'

  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Cantidad de items 2",
      "data": "[{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 11:26:44 AM\",\"createDate\":\"Sep 5, 2023, 10:39:28 PM\",\"state\":true},{\"typeStateId\":5,\"name\":\"completada\",\"createDate\":\"Sep 6, 2023, 11:27:13 AM\",\"state\":true}]"
      }
* ### Search Data of by type state
  patch: '/api/v1/by-type-state/{id}'
  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Proceso Realizado",
      "data": "{\"typeStateId\":1,\"name\":\"pendiente\",\"state\":true}"
      }
* ### Create a new type state
  patch: '/api/v1/save-type-state'

  ######  Request body (JSON):
      {
      "name":"completada",
      "state":"true"
      } 
  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Proceso Realizado",
      "data": "{\"typeStateId\":7,\"name\":\"completada\",\"createDate\":\"Sep 6, 2023, 3:02:02 PM\",\"state\":true}"
      }
* ### Update Data  type state
   patch: '/api/v1/update-type-state/{id}'

  ######  Request body (JSON):
      {
      "name":"pendiente",
      "state":"true"
      }
  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Proceso Realizado",
      "data": "{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 2:58:43 PM\",\"state\":true}"
      }
 * ### Delete type state
  patch: '/api/v1/delete-type-state/{id}'

  ###### Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Proceso Realizado",
      "data": "{\"typeStateId\":7,\"name\":\"completada\",\"state\":true}"
       }
## Task
 * ###    Get a list of all tasks
   patch: /api/v1/full-task

   ###### Request body (JSON):
       {
       "success": true,
       "warning": false,
       "message": "Cantidad de items 1",
       "data": "[{\"id\":6,\"titulo\":\"Correr\",\"descripcion\":\"Correr todos los días\",\"state\":{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 11:26:44 AM\",\"createDate\":\"Sep 5, 2023, 10:39:28 PM\",\"state\":true},\"createDate\":\"Sep 6, 2023, 2:28:55 PM\"}]"
       }
* ###  BY DATA
   It is requested to search for data by any of these filters
   patch: '/api/v1/by-task/{idTask}' 
   ######  Request body (JSON):

   ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Cantidad de items 1",
      "data": "[{\"id\":6,\"titulo\":\"Correr\",\"descripcion\":\"Correr todos los días\",\"state\":{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 11:26:44 AM\",\"createDate\":\"Sep 5, 2023, 10:39:28 PM\",\"state\":true},\"createDate\":\"Sep 6, 2023, 2:28:55 PM\"}]"
      }
* ### Create a new task
   patch: '/api/v1/save-task'

  ######  Request body (JSON):
      {
      "titulo":"Correr",
      "descripcion":"Correr todos lod",
      "state":"1"
      }'

  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Task creada: ",
      "data": "{\"id\":6,\"titulo\":\"Correr\",\"descripcion\":\"Correr todos los días\",\"state\":{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 11:26:44 AM\",\"createDate\":\"Sep 5, 2023, 10:39:28 PM\",\"state\":false},\"createDate\":\"Sep 6, 2023, 2:28:55 PM\"}"
      }
* ### Update task

   patch: '/api/v1/update-task/{id}'

  ######  Request body (JSON):
      {
      "titulo":"prueba",
      "descripcion":"Prueba 123",
      "state":"1"
      }

  ######   Successful response (JSON):
      {
      "success": true,
      "warning": false,
      "message": "Task Actualizado: ",
      "data": "{\"id\":1,\"titulo\":\"prueba\",\"descripcion\":\"Prueba 123\",\"state\":{\"typeStateId\":1,\"name\":\"pendiente\",\"updateDate\":\"Sep 6, 2023, 11:26:44 AM\",\"createDate\":\"Sep 5, 2023, 10:39:28 PM\",\"state\":true},\"updateDate\":\"Sep 6, 2023, 11:32:21 AM\"}"
      }
 * ###  Delete a task by its ID

   patch: '/api/v1/delete-task/{id}'

   ######   Successful response (JSON):
       {
       "success": true,
       "warning": false,
       "message": "Task eliminado",
       "data": "{\"id\":5,\"titulo\":\"Correr\",\"descripcion\":\"Correr todos los días\"}"
       }
# Evidence
The application includes unit and functional tests:

- Unit tests are located in the src/test directory and cover the CRUD operations of tasks.
- Functional tests verify that the API communicates correctly with the database and meets the functional requirements.
#  Contributions
Feel free to contribute to this project! You can open issues (issues) or submit pull requests to improve the app.
