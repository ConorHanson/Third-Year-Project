# **Technical Specification**

**Conor Hanson and Andrew Brennan**

### Contents:



#### 1.  Introduction

1.1 Overview

1.2 Glossary



#### 2. System Architecture
2.1 System Architecture diagram


#### 3. High Level Design

3.1 Data Flow Diagram

3.2 Class Diagram

3.3 Context Diagram



#### 4.Problems and Resolution

4.1 Accessing specific data

4.2 Data Visualisation

4.3 Multiple stat takers

4.4 Timestamp glitch

4.5 Calculating averages







#### 5.Installation Guide

5.1 Physical device

5.2 Emulated device




#### 6.Testing

6.1 Ad-hoc testing

6.2 Unit-Testing

6.3 Firebase Test Lab

6.4 User Testing




#### 7.References

***






**1. Introduction**

**1.1 Overview**

Our application is an online mobile application for android devices that allows users to record and view rugby match statistics. The application is specifically aimed at grassroots rugby where personnel, budgets and time are tight. With these factors in mind the application is designed to be easy and quick to use both in the recording of statistics and the viewing of them. The idea for the app is to be more efficient than using pen, paper and spreadsheets, to speed up post-match analysis and to provide a viable option for in game stats recording for organisations that might not have the resources to invest in the options targeted at the professional organisations currently available.

Our application is an Android application written primarily in java, we chose android as the cost associated with developing an application for IOS rules it out and Android is the most popular remaining mobile OS by a distance. We use a firebase database to store the data collected as it is a cloud-based database which allows for live updates, it also allows for data to still be collected while the user is offline which could be useful for matches in remote locations. We utilise AnyChart and MPAndroidChart to graph the data for clear, easy to read visualisation.

The application works by allowing a user to create teams and create matches for those teams. In those matches the user can take the desired match stats and can also view the stats live. Multiple users can access the match live by using the same account and entering the same game. This allows users to have the option of having multiple stat takes and multiple stat viewers and also to swap roles mid match. Users also have the option to go back and view the stats for games after they have finished.

**1.2 Glossary**

**IOS** - A mobile operating system created and developed by Apple.

**Grassroots Rugby** – Nonprofessional rugby e.g. schools, youths and adult rugby where players are not paid.

**Ad-hoc testing** - A method of software testing without any planning and documentation

**Firebase** - A platform used to store data and retrieve on/from the cloud.

**Anychar** t -  A library for data visualization in the means of charts and diagrams. (Trial version used for this app)

**MPAndroidChart** - Alibrary also used for data visualization in the means of charts. (apache license 2)

**Query** – A request for data from a database.

**Unit test** - software testing where individual units of a program/application is tested.

**2. System Architecture**
***

The overall System Architecture was quite a simple concept. On mobile devices we&#39;d have 1 to many users at a time all of which would be using the mobile rugstat app. This application would both push and pull from our Firebase database based on the pitch event buttons that were pressed.

![](technical_manual/1.png)

Users: Multiple users can use the app at the same time. They can pick and change their own roles once in the application. In the live match section of the application users can choose to take stats or to view live stats, users can switch between these two at will. Users can also access past matches.

Application: The application is essentially the apk containing the java code that runs the app and &quot;queries&quot; that push and pull from the database

Firebase: Firebase is a Backend-as-a-Service (Baas) . It is categorized as a NoSQL database program, which stores data in JSON-like documents. It allows data to be synced across all clients in real time and remains available when the app goes offline. Below is how we stored our match statistics in our real-time, Firebase database. 
![](technical_manual/20200306_161036.png)






**3. High-Level Design**
***

Our Project is extremely database heavy. For these reasons, we drew a Data Flow diagram and context diagram. We also included a class diagrams to represent the interaction and cardinality that they have with one another.

**Data Flow Diagram:**


![](technical_manual/2.png)




**Class Diagram**


![](technical_manual/3.png)


**Context Diagram:**


![](technical_manual/4.png)


**4. Problems and Resolution**
***

**Accessing specific data**

As we wanted a user to be able to have multiple teams and multiple matches for those teams we had to find a way to organise the firebase database in a way that allowed this and to be able to pull data based on the users selections. We struggled with this at first but found a solution using intents to send data between activities.

**Data visualisation:**

Finding a means of displaying our data was difficult, we had many ideas however we settled on the idea that visual graphs was our best option. We did research on different online libraries that were available for Android that we could use to display our match stats. The first library we found was Anychart. Anychart had extremely aesthetic graphs however it struggled to do a task that we wanted the most (that being displaying multiple graphs in one Android activity). Anychart would only display one graph instead of two as the second graph that we were drawing was using a complex query heavy method. We resolved this problem by using a second library, MPAndroidChart. This chart displayed data in a similar manner to Anychart, and when run didn&#39;t cause any problem even though there were two different graphs on the same page.

**Multiple statistic takers**

We wanted to allow teams to have multiple statistic takers for matches and for a while were unsure how to do this. The solution we came up with was to make it possible for users to access the same game by entering the name of a game created. This though must be done before a game starts, ideally simultaneously.

**Timestamp glitch**

We noticed that our timestamps would go negative on certain occasions. After some ad-hoc testing we soon realised the reason for this was  that times were only being recorded in minutes and seconds. When the time would go past the hour the timer would bug out and go into the negatives. This was a simple fix of, adjusting the timer to count hours, as well as minutes and seconds.





**Calculating Averages:**

Getting some of the graphs to work was difficult at first, especially getting the averages. A lot of Android studios built in methods are asynchronous which made the whole process confusing. We used _logs_ to figure out where the application was calculating averages wrong. We discovered the solution was that these asynchronous methods were not updating the the global variables. To solve this we calculated the averages inside the on\_data change methods in local variables.





**5. Installation Guide**
***

Installing the application is a very straightforward process thanks to it being developed for Android.

The application can be run on both a physical or emulated android device.

**Running on a physical device-**

1.  The user must have an android device.

2.  The user must have access to the apk.

3.  The user must select to download the apk on their desired device.

4.  a.   On older android versions you may have to do the following: Go to Menu - Settings - Security and check Unknown Sources to allow your phone to install apps from sources other than the Google Play Store
    
    b.   On newer android versions you'll simply be prompted to allow the downloading of APK's the first time you do it

5.  The application is now installed select the app icon in your menu to open it.

**Running on an emulated device-**

1.  The user must have Android Studio installed on their machine.

2.  The user must then download the &quot;Rugstats&quot; folder from git..

3.  The user must drag this folder into their Android Studio projects folder.

4.  THe user must then open Android studio and import the project

5.  Once these steps are complete the user can run the application by clicking play and selecting their desired emulator they wish to run the application on. (device installation may be required)

6.  The app will then run as normal as if on a smartphone.



**6. Testing**
***

**6.1. Ad-hoc Testing-**

In the early stages of our app&#39;s development stage we weren&#39;t fully sure what the end goal was and what features we would eventually add to our app. Because of this we didn&#39;t want to test with users yet. As we were programming our app we tested the updates in attempts to break them. We would repeatedly try unique scenarios until our new feature would break and from there repair it. From testing our visuals with 0 stats, to testing the limit of our app by stressing it with multiple stats in the space of seconds.

**6.2 - Unit Testing:**

We understood that we couldn&#39;t solely rely on Ad-hoc testing to catch all the bugs that are app had. To overcome this, we used JUnit to frequently test various methods after each build on android studio. Android studio provides great support for Junit and there were many resources online to guide us through our testing process. Unit test files can be found in our Gitlab repository.

**6.3. Firebase Test Lab:**

As our project is extremely database heavy, we figured that we should find a means of testing it alongside the application. Luckily the database platform (Firebase) that we were using, provided an handy, effective method of testing our application. We uploaded our APK to the Firebase Test Lab and used their &quot;Robo Test&quot; to test our applications&#39; features. The Robo Test stepped through the various screens of our application and tested it&#39;s features alongside our Realtime Database. It passed this test, and here is the breakdown of the test.


![](technical_manual/7.png)


A detailed crawl graph of this test can also be found in our GItlab repository.



**6.4 User Testing:**

Possibly one of the most important methods of testing came in the form of user testing. Andrew being a rugby coach, made the process of distributing the APK with amongst rugby coaches to gather usability feedback easy. We used an anonymous Google form to gather user feedback on the application. This provided an easy, visual method of viewing the various coaches&#39; remarks. The test results can be found in the testing folder on our GitLab repository.

**7 - References:**
***

Anychart - [https://www.anychart.com/](https://www.anychart.com/)

MpAndroidChart - [https://github.com/PhilJay/MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)

Firebase: [https://firebase.google.com](https://firebase.google.com/)

Firebase Youtube tutorial : [https://www.youtube.com/playlist?list=PLGCjwl1RrtcQ3o2jmZtwu2wXEA4OIIq53](https://www.youtube.com/playlist?list=PLGCjwl1RrtcQ3o2jmZtwu2wXEA4OIIq53)