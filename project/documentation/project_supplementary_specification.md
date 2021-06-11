# Pharmacy - Supplementary Specification

# Introduction

The Supplementary Specification captures the system requirements that are not readily captured in the use cases of the use-case model. 
Such requirements include:
-	Legal and regulatory requirements, including application standards.
-	Quality attributes of the system to be built, including usability, reliability, performance, and supportability requirements.


# Non-functional Requirements


-	**Quality attributes** are and should be quantifiable in specifications by the definition of some appropiate and practical scale of measure.
-	**Source of stimulus** :the user who buys the drugs.
-	**Stimulus**: stock of the drugs, the amount of money for discount. 
-	**Environment**: the user need to have money to be able to buy drugs.
-	**Artifact**: is the whole system
-	**Response**: the activity determined by the arrival of the stimulus
-	**Response measure**: the quantifiable indication of the response
-	**Tactics**
]
## Availability
-	This desktop application has the availability only when it is opened, it can be further developed and to be transformed to a web page which will make the availability 24 hours, 7 days/week.
## Performance
-	Performance is the amount of useful work accomplished by the application. The app communicates in real time with the user.
## Security
-	Security involves protection against attacks. Refistered users will have encrypted passwords in the database. If a user is not registered, he cannot access the app. Some types of users have restricted access. The administrator has a specific page which can be accesed only by him.
## Testability
-	If the testability of the software artifact is high, finding faults in the system by means of testing is easier. 
## Usability
-The system overall can be used by a Pharmacy company.

# Design Constraints
-	First of all the MVC arhitectural pattern and Factory Method design pattern will be used. After will be used Client-Server arhitectural pattern. For the view of application will be used JavaFX.

# Resources

* http://www.upedu.org/process/gdlines/md_srs.htm
* Example of Supplementary Specification: http://csis.pace.edu/~marchese/SE616_New/Samples/Example%20%20Supplementary%20Specification.htm
