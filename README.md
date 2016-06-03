# angular2-oauth2-swagger2

There is a back end server (Spring Boot) with OAuth2 server and a basic Spring Rest web service (to retrieve users).


The front end (web) is an Angular2 app that performs a login by calling the back end oauth2 server.

# Install mongo db
Any version 3 will do.

# To build

mvn clean install (from top level).


# Start server
cd server
./start
http:localhost:8080

# Start web
cd web
npm install
npm start

http://localhost:3000


# Swagger
http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html



# Tip 1

The best tip I can recommend is to get your swagger.json and paste it into :  http://editor.swagger.io

You get your swagger.json from <your server>/<your context path>/v2/api-docs/


# Tip 2

You must understand this statement:

Just to give you a high level idea without getting into the code, security has different pieces that all work together in concert

The API itself needs to be protected. This is achieved by using, for simplicity sake, spring security and may also use a combination of servlet container and tomcat/jersey etc.
   - This means doing something like this to your API:
   
       @ApiOperation(value = "Get blah blah", 
           authorizations = {
             @Authorization(
                 value="oauth", 
                 scopes = { @AuthorizationScope(scope = "read") }
                 )
           }
         )
         
         The value of authorization must match the oauth2 
                         
The security scheme which describes the techniques you've used to protect the api. Spring fox supports whatever schemes swagger specification supports (ApiKey, BasicAuth and OAuth2 (certain profiles))

  - This means you MUST setup securitySchemes on the Swagger Docket object
  
Finally the security contexts which actually provides information on which api's are protected by which schemes.

  - This means you MUST setup securityContexts on the Swagger Docket object

# Tip 3

Official Documentation links are:

* https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X


# Tip 4

Treat any CORS errors from Swagger Ui with extreme caution, this is often a very misleading error message.

# Tip 5

Now the OAuth2 (certain profiles) bit is significant.  There are the following OAuth2 profiles:

https://docs.oracle.com/cd/E39820_01/doc.11121/gateway_docs/content/oauth_flows.html

* Authorization Code (or Web Server) Flow - this is implemented in this example, and is supposed to be supported in Swagger
* Implicit Grant (or User Agent) Flow - this *IS* supported by swagger, but involves presenting an authorization url ending in /oauth/dialog
* Resource Owner Password Credentials Flow
* Client Credentials Grant Flow - only to be used by confidential clients - not implemented in Swagger.
* OAuth 2.0 JWT Flow - not configured in this example

  
# Tip 6

You must call your oauth2 'name', when you do a new OAuth(<name goes here>)




