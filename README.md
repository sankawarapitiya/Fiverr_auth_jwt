# Fiverr_auth_jwt

Authanticate Admin User & get Valid Token 
-----------------------------------
URL : localhost:8080/api/v1/auth/authenticate
Method  : POST
BODY: 
{
    "email": "admin@123.com",
    "signature":"adm"
}
-----------------------------------then you will receive Valid Token for admin Role 
Using this Token You can CREATE / UPDATE / DELETE users in the system


-------------------------------------
USER rold only can get the User list 
-------------------------------------
URL : localhost:8080/api/v1/auth/authenticate
Method  : POST
BODY: 
{
    "email": "user@123.com",
    "signature":"usr"
}
-------------------------------------

Get All users 
--------------
URL : localhost:8080/api/v1/user-controller/users
Method  : GET
--------------------------------------------------


