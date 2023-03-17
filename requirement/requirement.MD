1. Issue JWT based on the server's digital signature.
2. Authenticate the server using the JWT token.
3. For PUT/POST/DELETE, fetch the user details based on the email ID and digital signature validation from the footer.
4. Based on the user role, allow user to access the URL


```json
//Json to fetch the token. If the signature contains ABC, issue the token.
{
  "payload": {
    "url": "string",
    "ts": 0
  },
  "signature": "abc"
}
```
PUT/POST/DELETE payload
```json
//If the signature xyz matches the emaiid then allow the user to access the permitted URLs defined as per the role
{
	"payload": {
		"accountId": "kb4",
		"desc": "some random description"
	},
	"footer": {
		"emailId": "kb@gmail.com",
		"signature": "xyz"
	}
}
```
User's email address, signature, and roles are saved in the user table.

```java
@EnableGlobalMethodSecurity(
  prePostEnabled = true, 
  securedEnabled = true, 
  jsr250Enabled = true)
```

