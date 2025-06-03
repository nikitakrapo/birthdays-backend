# birthdays-backend

This is a backend part of Birthdays application built by [Ktor](https://github.com/ktorio/ktor)

You can also check other parts:
- [Mobile](https://github.com/nikitakrapo/birthdays-mobile) - Android + iOS clients built with KMP
- [Database](https://github.com/nikitakrapo/birthdays-database) - SQL DB migrations 

## Features

Here's a list of features included in this project:

| Name       | Description                                          |
|------------|------------------------------------------------------|
| Users      | You can register your own account with your birthday |
| Birthdays  | You can save local birthdays of your friends         |

OpenAPI spec is coming soon!

## Building & Running

Soon you'll be able to check the app by downloading it from Google Play, but if you really want to test it right now, 
here's the way to launch backend instance on your server.

For tutorials regarding how to build mobile app and set up DB, follow these links:
- [Mobile](https://github.com/nikitakrapo/birthdays-mobile)
- [Database](https://github.com/nikitakrapo/birthdays-database)

To build or run the project, you'll need to: 
1. Register a project in firebase, enable Auth & Analytics and download Admin SDK Json
2. Configure environmental variables

    | Variable                         | Description                                 |
    |----------------------------------|---------------------------------------------|
    | `DB_HOST`                        | Database host                               |
    | `DB_NAME`                        | Database name                               |
    | `DB_USER`                        | Name of DB user with enough permissions     |
    | `DB_PASSWORD`                    | Password of DB user with enough permissions |
    | `GOOGLE_APPLICATION_CREDENTIALS` | Path to google admin SDK json               |


