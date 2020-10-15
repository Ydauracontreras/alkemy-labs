
# ALKEMY LABS APP

Web application where university students can register for the subjects they want to take


## Common setup

```bash
git clone https://github.com/Ydauracontreras/alkemy-labs
```

## Build Tools
```
Java y SpringBoot
```
# Base de datos
```
Base de datos de tipo relacional con MSQL
```

# Deploy
```
Heroku
```

# Requests

| Endpoint               | HTTP    | Description                        |
| -------------          | ------  | -----------------------------------|
| `/auth/register`       | POST    | allows USER registration           |
| `/auth/login`          | POST    | allows USERS login                 |
| `/managers`           | POST    | MANAGER registration               |
| `/managers`           | GET     | MANAGER list                       |
| `/managers/id`         | GET   | Retrive an specific MANAGER with `id`|
| `/managers/id`         | PUT   | Update an specific MANAGER with `id` |
| `/teachers`           | POST    | TEACHER registration               |
| `/teachers`           | GET     |TEACHER list                       |
| `/teachers/id`         | GET   | Retrive an specific TEACHER with `id`|
| `/teachers/id`         | PUT   | Update an specific TEACHER with `id` |
| `/teachers/id`         | DELETE   | Delete an specific TEACHER with `id` |
| `/teacher/id/subjects` | GET   | list subjects by TEACHER  |
| `/subjects`            | POST    | SUBJECT registration               |
| `/subjects`            | GET     |SUBJECT list                       |
| `/subjects/id`         | GET   | Retrive an specific SUBJECT  with `id`|
| `/subjects/id`         | PUT   | Update an specific SUBJECT  with `id` |
| `/subjects/id`         | DELETE   | Update an specific SUBJECT  with `id` |
| `/subjects/id/teachers` | POST   | ADD subjects to TEACHER  |
| `/students `           | POST  | STUDENT registration                   |
| `/students `           | GET   | STUDENT list                           |
| `/students/id`         | GET   | Retrive an specific STUDENT with `id`  |
| `/students/id`         | PUT   | Update an specific STUDENT with `id`   |
| `/students/id`         | PUT   | Update an specific STUDENT with `id`   |
| `students/id/enrollements`     | POST   | enroll a STUDENT in a subject |
| `/students/id/subjects`        | GET    |list subjects by STUDENT       |
