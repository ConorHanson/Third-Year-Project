#

# CA326

#

#

# Andrew Brennan

# Conor Hanson

#

#

# Rugby Statistics Application

#

# Functional Specification

#























**Page Content                                                                 Page number**

**1. Introduction**

1.1 Overview         1

1.2 Business Context        2

1.3 Glossary         2-3

**2. General Description**

2.1 Product / System Functions      3

2.2 User Characteristics and Objectives     4

2.3 Operational Scenarios       4-5

2.4 Constraints        5-6

**3. Functional Requirements**

 3.1 Creating an account/ Login      6

 3.2 Forgotten password       7

 3.3 Select club/create club       8

 3.4 Adding a team/view team       8

 3.5 Recording and uploading a match event     9

 3.6 Start/End match        10

 3.7 View team statistics       11

 3.8 Share stats        12

 3.9 Remove team        13

 3.10 Log out         14

** **

**4. System Architecture**

System Architecture Diagram       15-16

**5. High-Level Design Diagrams**

Context, DFD         16-18

**6. Preliminary Schedule                                                                **

Gannt Diagram        18-19

**7. Appendices                                                                       ** 20













## **1 Introduction**

**1.1: Overview**

The product that we are developing is a rugby statistics app. The mobile application will allow users to track their team in real-time during a rugby match. Furthermore, the user will be able to review their team&#39;s matches and analyse their positive / negative  statistics of that game.

Coming from rugby playing background, we recognised a problem that all lower level rugby teams face, that being stat tracking. Amatuer and school rugby coaches don&#39;t have the money, time or manpower to fund and/or use the modern equipment and stat-trackers that professional rugby teams have access to. Knowing this, we thought a free, user-friendly mobile application would be the perfect solution to fill this void. Currently stat tracking in rugby is an all or nothing task with no middle ground in terms of cost, level of detail and time consumption. Our application aims to provide this middle ground where a coach may not need to know how far a player runs or how many rucks he hits, but will want to know how many tackles they make and line breaks they make. We know that most coaches at grassroots will also have full time jobs or still be in education. This means they don&#39;t have the time to manually gather the match stats after the fact or to use the same tools available to the coaches in the professional setup. By collecting the data during the game, we hope to save these coaches some much needed time and effort.

The primary function of this product is to provide the user (be it a coach, substitute player or spectator) with an accessible means of tracking their team&#39;s game in real-time. The user interface of this application will be key, as we understand that not all users will be as &quot;tech savvy&quot; as others, so a simple GUI will be essential. We understand that rugby is played at an extraordinary pace, so input of data must be quick and smooth. The application will link to a database management system that will save and send users&#39; team/match information.

The secondary function of this product is to visualise the collected data in a quick and easy way for a coach or player to understand. As we are dealing with the lower levels of rugby we are dealing with enthusiastic but amateur users. This means that it is vital that our data visualisation is as user friendly as possible.

**1.2: Business Context**

This app has the potential to be published on the Google Play Store, so we know that there is an opportunity for income to be made as we could charge for the application. However understanding that we are targeting grassroots rugby where money can be hard to come by and not wanting to push the costs onto the coaches themselves, we figured that it&#39;d be best to keep the application free of charge. Charging even a minimal fee for the application would add up over a whole club/school and defeat the purpose of the application. We also know that there is still a chance here to gain revenue off Google&#39;s advertisements, if we choose to enable them on our application which while slightly annoying for a user is something people grow to get used to and don&#39;t really notice after a while.



**1.3: Glossary**

**GUI**

Shorthand for graphical user interface. This is how the user interacts with the application.

**SQL**

Structured Query Language. Used to send and receive information to/from a database.

**Grassroots**

A basic level of the sport.

**GDPR**

The General Data Protection Regulation. There to protect the data of EU citizens.

































## **2 General Description:**

**2.1: Product / System Functions**

The objective of this program is to provide a user of any age the ability to track their team&#39;s statistics on and off the pitch. Understanding this, we created a rough list of functions that we hope to implement into our application.

- Login system
  - Create a login system, so each user will have private access to their own account on any device.
- Create club.
  - The user can create/ select a club to use in the application.
- Password recovery
  - If a user forgets their password, they will be able to reset it via an email verification.
- Adding teams
  - The ability the add multiple teams for your club.
- Selecting teams
  - Selecting the team from your club that is playing the match.
- Viewing Teams
  - View the players in each team.
- Record an event that occured midst match
  - Record  Who,What,Where and When an event occurs on the pitch in real-time with a few simple screen taps.
- Sharing statistics
  - The ability to share team statistics with other people
- Viewing team statistics
  - View and analyze team&#39;s statistics once a match has been played.



**2.2: User Characteristics and Objectives:**

We hope to achieve an application that&#39;ll suit a community regardless of their age and technological proficiency. Due to the nature of the application, we can take it as a given that an interest in rugby would be a common characteristic between users. We aim to target teams with a lower, or no, budget that may not be able to afford the systems currently on the market. As a result of targeting this audience, we can assume most teams using the application will not have a dedicated statistician.This implies that the person physically using the application may not always be the same person. This means that our application needs to be intuitive to use as a user may not always be familiar with the application. Due to this potential for constant changing of user and the variety of demographics involved in grassroots rugby, the application has to be designed in a way that older users are able to use the application as easily as young users.

We assume that all users will have access to an Android device, of which they will launch and run the application off. Using Android will allow us to easily maintain and update the app in the future. The user must also have an internet connection to access the online database.

**2.3 Operational Scenarios.**

- **●** ** User login**
  - The user will be presented with a login screen. Here they will enter their team&#39;s username and password or create an account. If they have forgotten their password, a password recovery operation will begin.

- **●** ** User forgets password**
  - In the case that the user cannot remember their password, they will be taken to a password recovery screen. Here the user will enter their email address, and if it&#39;s linked to another account they will then receive a recovery password via email.

- **●** ** User creates account**
  - In the case that a user wishes to sign up to our service they will have to fill in a sign-up form. Details such as Name, Email and password will be required to complete signup.
- **●** ** User creates club**
  - After creating their own account the user may create a &quot;club&quot; which will encompass the different teams that are involved in that club. The club creator will give the club a name and a password that other users can use to join the club.
- **●** ** User joins club**
  - If a user wishes to join an existing club they can select join club and input the clubs name and password to join it.

- **●** ** Select club**
  - The user can also choose to go into a club they have previously joined. This will allow users to be a member of many different clubs as a coach may be involved in multiple clubs.
  - E.g. A club and a school

- **●** ** Create team**
  - When in a club a user may create a new team for that club. To do this the user will simply input the name of the team they wish to create. There will be no need for a password here to allow coaches to easily see how other teams are getting on. This is important as clubs act as a whole environment, so the community aspect is key.
- **●** ** Create match**
  - Once in the desired team the user can select create match to start a new match and begin to collect data.
- **●** ** View statistics**
  - In a team the user will have the option to view the statistics collected for that team. They will be able to filter these statistics to see what they want more easily.





** **

**2.4 Constraints**

We have taken into consideration the different aspects of our application and have discovered the few following constraints:

- **●** ** Online Database**
  - With the resources that are available to us, we figured database storage would be our biggest constraint. As we are limited to our wallets, we unfortunately don&#39;t have the funding to setup up an industrial scale database. Knowing this, when testing our application, we will limit the sign ups as well as data that each user can access / upload to their individual accounts.

- **●** ** Android App Development**
  - This will be the first time that the pair of us have ever developed a mobile application. We understand that we have a lot to learn, however as with previous modules and assignments in the past, we will practice independent learning. Luckily the  internet is extremely resourceful around app development so we shouldn&#39;t struggle that much.

- **●** ** Which Programming Language?**
  - The majority of Android applications are programmed using Java as it is Android&#39;s official language. The pair of us have worked with Java in the past however it is not our strongest programming language. Python is our most familiar language, and it is possible program android apps using Python framework. However the learning material is a lot more limited in comparison to Java. This introduces a tough decision as to which programming language we are going to settle on using.



- **●** ** Time Management**
  - At both second level and third level education neither of us have been given a project of this length, so we figure time management may be difficult at times. We plan on using apps such as trello to manage our time, and deadlines to the best of our capabilities. This will be tough, however we believe once we get into a routine, we should be able to manage.

- **●** ** GDPR**
  - When we first proposed our ideas to our supervisor Mark Roantree, he explained to us some potential issues that may arise surrounding GDPR with our application. We have to be careful using player names and personal information. One solution we have come up with for this is using player numbers in place of names which is a slight inconvenience to coaches but a small price to pay. We will continue to look into this further to try to come up with a better solution.





## **3 Functional Requirements**



**3.1 Creating an account/ Login**

**Description:**

Here the user will create their own personal account. They will be required to enter information such as their, Name, Email Address, and Password. Once their account is created, they will be able to login with their email and unique password. The user will then be greeted with a dashboard with their statistics and team&#39;s information



**Criticalities:**

We feel that the account creation and login functions in our app are the most critical one. Without it, there would be no means of storing match event&#39;s online. We prioritise the user to be able access their team&#39;s information anywhere, at any time, on any device. To do this a unique username and password (key) is needed to pull information from an online database.

**Technical Issues:**

As we are dealing with personal information that being, email addresses and passwords, we will need to find a means of securing this sensitive data. All passwords must be hashed, and as none of us have experience with hashing we will need to learn how to do this. Password recovery is another potential issue that may arise, and we will need to account for in the login screen.



**Dependencies:**

There aren&#39;t many dependencies for this, however users must have a valid email-address as well as an Android device to run the application off.















**3.2 Forgotten Password**

**Description:**

In the event that a user forgets his/her password, they will have to select the &quot;forgotten&quot; password button. A prompt will appear on screen, asking the user for their email-address. If the address corresponds to one in the database the user will receive a recovery email to change their password.



**Criticalities:**

We feel this requirement to be one of our main priorities. Passwords are forgotten all the time, so we feel a recovery option goes hand in hand with our application. This will prevent the loss of valuable data/statistics if the user were to forget their login details.



**Technical issues:**

We will need to learn how to email user&#39;s recovery links to their accounts without breaching their security. The email address will act as a key to their account information, so we will have to make good use of SQL to achieve this.



**Dependencies:**

The forgotten password function will be dependent on the user having created an account. They must also have an android device with an internet connection.



**3.3 Select Club/Create Club**

**Description:**

The user will be able to select the club on their dashboard(if they have joined the club)that they wish to access. If a user wishes to join a club, they must enter that clubs name and password. A user also can also create a club on their account. They will be prompted to create a name and password for that club.

**Criticalities:**

This feature is important as some user may coach different clubs, i.e a club and a school team. All of the school&#39;s information would then be kept separate to the other club.

**Technical issues:**

The only technical issue I can see arising with this feature is the database size. As we are limited to a small server, we need to be sure that we don&#39;t run out of space when testing this application. This ensures that our application won&#39;t run slowly.

**Dependencies** :

The user must both be logged in and have access to an Android device (with working internet)

**3.4 Adding a team/View team**

**Description:**

Here the user will be able to create/view to their rugby club. The user will be prompted with input the team&#39;s name and description. If the coach wishes they will be able to add information on players for that team. They can also view their club&#39;s current teams here if they wish.



**Criticalities:**

As most rugby clubs have multiple teams at different, or at the same age levels, we felt this is a critical function for our application. Without this features coaches wouldn&#39;t be able to manage multiple teams on the one account. Instead they would have no alternative option other than to create a separate login for each team.

**Technical Issues:**

Similar to the previous function, we feel that server space will be the main technical issues that we encounter. Some rugby schools or clubs can have 3 or more teams at each age level. Knowing this,we worry that strain may be put on our limited server. To avoid this problem we will limit the teams to 2 per account when in our testing phase.



**Dependencies:**

Again,this function will only be dependant on a few things. First, the user must have created an account and have logged in successfully. Secondly the user must have access to a handheld device running Android. It will also be dependant on the user being in a club. Viewing a team will be dependant on having created one.

















**3.5 Recording and uploading a match event**

**Description:**

Before the game begins the user must select the team that will be playing the game.

The user will then be prompted with a screen in which they select the event that occurred. Next the user will select where on the pitch this event happened, followed by the player that was involved. The built-in timer will also track when the event occured in the game. The information that is recorded will be pushed to an online database, that will be easily accessible to the user post-match.



**Criticalities:**

The entire app will be based around the statistics that are recorded during a match, because of this regard it as an extremely important step. Without the ability to record the match events, our app would have no way of adding to a team&#39;s personal database. Without the ability to upload these recorded events, the team&#39;s coach would have no way to review the game statistics post-match.



**Technical Issues:**

The main issue we may encounter regarding this function comes with the online Database. We will be limited to the size of our database&#39;s server, so we need to be sure that when testing we set limits to the amount of accounts. We also must limit the storage each account has access to. This practice ensures that our server won&#39;t be sluggish at sending and receiving data.

The ability to gather statistics offline was also a technical issue we thought could arise. We hope to avoid this by using a cloud database that will cache data while offline and upload it to the database when connection is restored.



**Dependencies:**

The user must have created an account and have logged in successfully. They must also be a member of a club, have created a team and have created a match













**3.6 Start/End match**

**Description:**

The user will click Start Match and select their team to begin the timer/game.The user will click the end match button once a game is finished. The database will group all of the match&#39;s events in separate match folders according to these two button press events. When the &quot;Start Match&quot; button is pressed the connection to the database server will be made. When &quot;End Match&quot; is pressed the connection will be broken.



**Criticalities:**

This function is critical as it will neatly store events into their corresponding match. This is a vital part of our application, as it will allow the user to easily navigate through their season of matches, and the statistics that correspond to it. Without this feature, the user would be faced with a wall of match events, i.e, (tackles,tries,penalties) with no idea as to which match they occurred in.



**Technical Issues:**

The main issue that may arise with this function, comes with the grouping of the matches. We will need to group each event in order from when the start button is pressed, to when the end match button is pressed and store it in a database. Making these stats accessible to the click of a button will be our greatest challenge as it&#39;ll involve a lot of SQL that we aren&#39;t familiar with.



**Dependencies:**

Again function will rely on two things. First, the user must have created an account and have logged in successfully. Secondly the user must have created a match.















**3.7 View Team Statistics:**

**Description:**

The user will be able to select a team from their club and view that team&#39;s season long statistics. These statistics will be based off the matches that they have previously recorded. When a player clicks on the team&#39;s statistics they wish to view, an orderly screen of match event in or of the time that they occurred will appear.



**Criticalities:**

The most important aspect surrounding this function would be the UI. Statistics are important to rugby coaches so we feel that easy to use, readable match stats are essential. To accomplish this, we will ensure that all statistics are mapped according to the game they&#39;re from. Each match will have the title of that game and the date that it was played.



**Technical issues:**

A technical issue may arise in the sense of displaying match statistics. We hope to implement these stats in a visual graph, however we understand that this would require some sort of computational program to draw graphs of the stats are they are recorded. With a bit of research into this area of programming, we feel that we should be able to overcome this issue.



**Dependencies:**

This will be dependent on two things. First, the user must have created an account and have logged in successfully. Secondly the user must have created a team, as well as recorded stats for at least one game. The user must also have access to a handheld device running Android as well as an internet connection to receive data from the online database.

















**3.8 Share Stats:**

**Description:**

The coach will be able to share the stats from a particular with other players or coaches. They will press the share button will be given the option to share the statistics using different means of communications.

**Criticalities:**

We feel that this is an extremely feature of our application. For a rugby team to improve, it&#39;s important that all players/coaches have access to stats.This allows these users examine where the team went wrong during a match, and ultimately improve on this in their futures within the team.



**Technical Issues:**

There is only one technical issues that I potentially see arising in implementing this feature, that being a visual representation of these shared statistics. We can&#39;t assume that every person receiving the shared match statistics will have the app on their devices. This means we will have to find/create a means of converting the app&#39;s statistics into a format that is readable on any platform, i.e email.



**Dependencies:**

This will be dependant on that the user has logged in and has created at least one team. The user must also have recorded the stats for one or more rugby games. The user must also have access to a handheld device running Android as well as an internet connection to connect to the database and to send that data to another person.















**3.9 Remove Team:**

**Description:**

This removes a team from the coach&#39;s club. This will be done by the simple press of the remove team button. A confirmation will appear on screen to ensure that the user doesn&#39;t accidentally do this.



**Criticalities:**

This is essential in the sense that a coach may be in charge of say Under 16&#39;s for the 2019/2020 season. When the 2020/2021 season comes along, most of the players will be in different age groups meaning the coach will have a whole new team. This feature allows the coach to have a fresh start with their statistics as well as adding their new line-up.



**Technical issues:**

I don&#39;t think there will be any technical issues with this aspect of our application. The only situation where I can see there being a slight problem is, if the user where to accidentally delete their team and erase all of their match stats.



**Dependencies:**

This aspect will be dependant on the user being logged in and having at least one team.The user must press the confirmation button to trigger this function.The user must also have access to a handheld device running Android.



















**3.10 Log Out:**

**Description:**

The user logs out of their account by pressing the logout button. Again they will have to press confirm to avoid accidental button presses.



**Criticalities:**

A lot of rugby clubs have a shared handheld device such as a tablet. If more than one coach wanted to avail of this app on the same device, a logout feature would be essential. This way coaches will have their own team&#39;s information in one account without the ability to view other clubs that the user may potentially coach. Another coach on the same device won&#39;t accidentally modify the other coach&#39;s data as they would have been logged out. The logout feature is also vital for security reasons. In the case that another person was using your phone, they would not be able to access your team&#39;s private information if you were logged out.



**Technical Issues:**

We don&#39;t think there will be any technical issues with this aspect of our application. The only case where I can see there being a problem is if the user where to accidentally log out mid match, thus interrupting the flow of the event taking.



**Dependencies:**

This function will be reliant on the user being logged in. The user must press the confirm logout button to trigger this function. The user must also have access to a handheld device with Android as well as an internet connection. This will instruct the database to stop sending data to that account when it&#39;s logged out.











.

## **4. System Architecture**

![System Architecture](functional_spec/SystemArchitecture.png "System architecture")


The system architecture of the application is fairly straight forward. The user interacts with the GUI which interacts with the cloud database, this allows the application to function offline as well as online. This adds robustness to the application in situations where there may be no/poor internet access as many users may be somewhere with no WIFI access and they may not have mobile data or may be using a tablet which has no facility for mobile data.













## **5. High-Level Design**

Here we use a context diagram and a Data flow diagram giving an overview of the system.

Context Diagram

This diagram shows the flow of information between the system and external entities. These external entities are the user and the database.

Data Flow Diagram

This diagram shows the flow of data through the application. It does this by showing the data stores, the processes and the external entities.

**Context Diagram:**
![Context Diagram](functional_spec/ContextDiagram.png "Context Diagram")

 
**Data Flow Diagram:**
![DFD](functional_spec/DFD.png "DFD")


 
## **6. Preliminary Schedule**

Our preliminary schedule is outlined below in a GANTT diagram. Our only Software requirement is a cloud database and out only hardware requirement is an Android device.

![Gantt](functional_spec/Gantt.png "Gantt")
 
**Appendices**

MYSQL:    [https://www.mysql.com](https://www.mysql.com/)

Wikipedia: [https://en.wikipedia.org/wiki/Gantt_chart](Wikipedia: https://en.wikipedia.org/wiki/Gantt_chart)

Android Studio: [https://developer.android.com/studio](https://developer.android.com/studio)

Google Docs: [https://docs.google.com](https://docs.google.com/)

Microsoft excel: [https://office.live.com/start/excel.aspx](https://office.live.com/start/excel.aspx)

Draw.io: [https://www.draw.io](https://www.draw.io/)