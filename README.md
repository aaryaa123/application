# Application task

Thank you for applying to our job offering.
In the following are a bunch of tasks which could appear in a similar kind in your upcoming workload.
The first part contains general questions.
Please answer the questions after forking this repo and keep it private.
The second part asks for a common git scenarios.
The third part requires implementation tasks.

## Part 1: Questions

Please answer the following quesitons shortly inside this file.

### Why is health data such as this especially protected by the data protection and privacy regulations?

- Health data is especially protected because it is sensitive personal information that can directly impact an individual's privacy, dignity, and well-being if misused or disclosed. Regulations like GDPR and HIPAA ensure that such data is only collected, processed, and stored under strict conditions to prevent unauthorized access, misuse, or breaches.

### How do you provide animation logic in Unity-based game?

- Animation logic in Unity is typically provided using the Animator Controller, which allows developers to define animation states and transitions between them. Scripts in Unity (C#) can also control animations by triggering specific parameters in the Animator Controller or by directly manipulating properties using Animator methods like SetTrigger, Play, or CrossFade.

### Please explain how file permissions work in Linux. What different kinds of permissions are there? How are they assigned to a file or directory?

- File permissions in Linux define who can read, write, or execute a file or directory.

 There are three main types of permissions:

Read (r): Permission to read the contents of a file or list the contents of a directory.
Write (w): Permission to modify a file or create/delete files within a directory.
Execute (x): Permission to execute a file as a program or access a directory.

Permissions are assigned to three categories of users:

Owner: The user who owns the file.
Group: A group of users who share the same permissions.
Others: All other users on the system.

## Part 2: Git skills

Git is a common tool to keep track of code development and collaborative programming.
Before continuing please commit your answers so far, thanks.

### Git merge

If you check the branches of this repository you will recognize another branch.
Please merge the branch and its containing files into your main branch.
Resolve possible merge conflicts.

## Part 3: Simple demo app

### Implementation task

Create an android app which allows the user to register a patient with firstname, lastname, date of birth, weight, shoesize.
Additionally the app should instruct the user to take four pictures of the patients feet, the top and bottom per foot.
The backend can be mocked and does not have to be implemented by you. However, it should ideally be a REST API endpoint.

### What privacy issues are related to this feature?

- Sensitive Data: Patient details (name, DOB, weight) and foot images are sensitive personal and health data, requiring strict protection.
Unauthorized Access: Data must be secured to prevent unauthorized access or misuse.
Data Transmission: Storing or transmitting data without encryption risks leaks.
User Consent: Explicit consent is required for collecting and using personal data.
Data Retention: Storing data indefinitely without a clear policy violates privacy norms.
Misuse Potential: Data used beyond its intended purpose breaches trust and regulations.


After completing the tasks please send us an invite to your forked repository (Nikolajewitsch, istiyaksiddiquee ).
