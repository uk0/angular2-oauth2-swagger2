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
http://localhost:8080/swagger-ui/index.html



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

# Tip 7

Don't import swagger-ui using the maven import.  This copy of swagger-ui is out of date, get the latest from here:

https://github.com/swagger-api/swagger-ui/tree/master/dist

download it, and stick in src/main/resources/static (if you are using Spring Boot), else where ever your public htdocs are placed.

Then, edit the index.html file and setup url so the swagger.json can be retrieved by swagger ui, ie. = "/v2/api-docs"
/v2/api-docs will be available if you have swagger-core imported (aka maven/gradle etc.).

# Tip 8

I don't think you can just deploy the editor locally on your server.  You either use the cloud version or 
you run another web server (ie. node)

# Current State

* Swagger Editor

I can now paste my swagger.json (/v2/api-docs) into editor.swagger.io.  As my CorsFilter currently allows ("*") - not very secure!
I can then send in my request and authenticate in the editor screen.

In order to get an access token, I run a curl:

curl localhost:8080/oauth/token -d "grant_type=password&scope=write&username=admin&password=free4all" -u foo:bar --trace-ascii /dev/stdout

I then paste in this access token into the authorization popup.  Not very user friendly, but better than nothing.

* Swagger Ui

Now I am using the latest version of Swagger Ui, I get the authorize button up, I can select which grant I want, but when I click on 
authorize it takes me to /login rather than asking me for an access token.

What is happening is I am hitting /oauth/authorize, passing this:
/oauth/authorize?response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fswagger-ui%2Fo2c.html&realm=your-realms&client_id=foo&scope=global&state=oauth2 reached end of additional filter chain; proceeding with original chain

and the authorization endpoint is complaining: User must be authenticated with Spring Security before authorization can be completed.  It wants me to have authenticated first.

I mean, I do want the user to have authenticated first anyway, as I don't want them to have to run curl to get an access token and paste this in.

The question then is (I suppose) is that I need to enable a /login mechanism on the back end server.

So I really need to setup implicit flow.

Trouble is oauth is intercepting my /login request and not let it get through to the Spring Security filter.

# Tip 9

Ordering the interceptors.  So, I have now ordered my interceptors, and made the form login web security configuration have greater precedence.  This makes the Swagger Ui work and allow me to 
grant permission.  Then, finally, I had to tell the form login web security configuration to 'allow' the call to the /users REST service through, and of course the oauth2 web security config
picked it up instead.