#Sunhill Code Test project

Here's my attempt to fill the required technical test for your recruiting process.
 
Time spent in implementation: a bit less than two hours, which was the maximum time allowed.

It has been created quite simply with the following
tools:
- Java 1.8 (thought I didn't use any special feature from this version)
- Maven (Embedded 3.3.9 version on Eclipse )
- SpringBoot 2.0.1.RELEASE for both web and test parts

As I said, it has been a very simple approach from your requirements, so I hope I didn't felt in a too oversimplified solution for this. I tried to focus on make this solution as easy to mantain as possible. It is built around using Spring dependency injection over interfaces instead of asking for a particular qualifier. This would help to easily replace the service or dao implementations by replacing the implementing classes. As long as they keep the existing interfaces, the application should work.
Well, short of. Running out of time I only make an endpoint to handle transfers between accounts. That part, rest endpoint, controller and dao were added on the last minutes to have something to run from this application, sorry about that rush. It should be running from generating a jar file, or
by running CodeTestApplication class as application.

The main goal for the chosen structure is to keep Account and AccountService as core parts of the incoming logic for this application. Currently, both of them are abstract, so they can't be instancied/injected, but can be used to extends other new account types with they basic, mandatory fields and the two usual mandatory fields: deposit and withdrawal. For current account type, deposit works for both of them, based on not being limited by a specific logic, but withdrawal is not implemented in AccountService because they way it works is different from each account type. Maybe I could had implemented the current SavingsService implementation on AccountService, and overrided it on CheckignsService, so we have a default implementation for it as deposit has.

I didn't used any library beside Spring framework. On my GitHub repository I have a couple of projects which use more, like Apache Commons Collections to handle collections filtering or searching, or Mockito to mock services in JUnit. I usually tend to look around if what it seems like a common use case could be already implemented on a library and use it instead of losing time trying to implement something which already exists, and it is probably more tested and used than my own code. However, I know this is not the same than actually using on the current technical test. 

Feel free to look around on my other GitHub repos.