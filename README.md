
Aimsio Technical Assignment
==============

This application has been created using the Vaadin archetype library. 
In order to run it, you need to get a Vaadin Charts license key (https://vaadin.com/docs/v8/charts/java-api/charts-installing.html) 
and follow the steps below.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Client-Side compilation
-------------------------

The generated maven project is using an automatically generated widgetset by default. 
When you add a dependency that needs client-side compilation, the maven plugin will 
automatically generate it for you. Your own client-side customizations can be added into
package "client".

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

Developing a theme using the runtime compiler
-------------------------

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use the runtime compilation, open pom.xml and comment out the compile-theme 
goal from vaadin-maven-plugin configuration. To remove a possibly existing 
pre-compiled theme, run "mvn clean package" once.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .

Requirements
-------------------------

The provided CVS dataset contains the time-stamped list of signals received from equipment 
in the field. Each signal is from a certain piece of equipment in time of a status change. 
Implement a responsive VAADIN application that visualizes the # of signals over time. 
Here is a more detailed list of requirements:
1. Information about the database access is included in the e-mail.
2. Use maven to create your VAADIN application.
   * You can start from scratch or use this project as a starting point.
3. Your application must have the following components:
   * A Vaadin Chart component: horizontal axis shows the time, vertical axis shows the # of different signals, e.g. Engaged, Active, etc;
   * A ComboBox with all AssetUN’s. The user should be able to either view all the data or filter the data for a specific AssetUN, e.g. to see the different signals that were received from unit 3112;
   * A ComboBox with different statuses. The user should be able to either view all the data or filter the data to a specific status, e.g. to see the number of Engaged statuses.
   * Make sure that the two ComboBoxes work well together.
   * The application should be responsive, i.e. adjust itself dynamically based on the browser width/height
4. You are not allowed to load all data into memory all at once. Your program should construct a query and load data as needed.
5. Use software patterns of your choice
4. Bonus:
   * How would you test this application?
   * Upload your application on AWS and send the URL with your submission

Feel free to make any reasonable assumption about this assignment, innovate and
add your personal touches as you see fit.   