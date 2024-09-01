# Spring Boot OpenAPI
## Motivation 
On a project where I was working on the past we used generated clients from the spring-boot application in the angular front-end. This approach was new to me and I really do like this kind of solution.
This was a motivation for me to create a small "Hello-World" like project where the clients are generated via OpenApi and used in an Angular front-end
Based on the following source the project is based.

Source:
- https://www.youtube.com/watch?v=nZL-ZwD1bG4
## Problems
### HttpClient Syntax problem
In the first attempt, 6 months ago, I had a problem with the HttpClient which was similar to the same problem which I got now. Then I have used Angular 17 and now 18.  
For both the solution is the same and means that an HttpClient needs to be provided to the component by adding it in the providers section.
```
// in file: app.config.ts
export const appConfig: ApplicationConfig = {
  providers: [
    ...
    provideHttpClient(withFetch())
  ]
};  
```
Source:
- https://www.youtube.com/watch?v=zXoXmzzEefA
- https://www.youtube.com/watch?v=5JcMras7aaA
- https://stackoverflow.com/questions/77482678/no-provider-for-httpclient

### HttpClient Syntax request problem
After the syntax problem was cleared that another problem appeared. The HttpClient won't fire requests toward the back-end server. I guess It was my fault anywhere why this didn't work, based on the code I have.
As information, the HttpClient was used as constructor injection and then used in the onInit method.
What I do is to create the small angular example from scratch like shown in the source below. The tne request has worked and I could work further on this example. 

Source
- https://www.youtube.com/watch?v=3vQpYKlHmS0 
### Wrong content type
Wrong mime was used when retrieving the data from the server. Instead of the json a blob recognized by the front-end.   
The solution was to explicit describe the request and response content types of the end-points (consuming and producing) on the backend side.
By changing that way tha OpenApi generator had generated correct clients and the communication went well.
For generating the clients following command was defined in the package.json file: `"openapi-generator-cli generate -o ./generated-clients -i http://localhost:8080/v3/api-docs -g typescript-angular"`
## Syntax sugar
### Short import path in Angular
```
// in file: tsconfig.json
"paths": {
    "@generated-clients": ["generated-clients"]
}

// in file app-components.ts
import {PersonControllerService} from "@generated-clients";
```
Source:
- https://stackoverflow.com/questions/39131423/angular2-import-shortcut
## Using the example
### back-end
1. start the backend spring-boot application
2. add some users, i.e. with the "generated-requests.http".
### front-end
1. In package.json use the "start"-script for starting the front-end.
2. If the clients are not generated then start the "generate-clients-via-openapi"-script. Warning: the backend must run because the script uses the link "http://localhost:8080/v3/api-docs" from the back-end.
## Information
The angular application was created like described here: https://www.npmjs.com/package/@angular/create.