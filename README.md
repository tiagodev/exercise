# Exercise with Google Maps API

<b>Tecnology Stack:</b><br>
<b>Client</b><br>
Angular 6.0.2<br>
NodeJS 8.11.1<br>
NPM 6.0.1
<br><br>
<b>Services</b><br>
Spring 5.0.6<br>
Hibernate 5.2.17<br>
Tomcat 9.0.8<br>
JDK 8
<br><br>
<b>Database</b><br>
H2 1.4.197<br><br>
<b>Tests</b><br>
JUnit 4.12<br>
Mockito 1.10.19<br>
Karma 1.7.1<br><br>
<b>Static Analysis</b><br>
FindBugs<br>
PMD<br><br>
<b>Build Tool</b><br>
Gradle 4.7<br>
Angular CLI 6.0.1<br><br>
<b>Services Documentation</b><br>
Enunciate 2.11.0<br><br>
<b>TODO</b><br>
Flyway (with JPA Integration), Cucumber, Jester, Selenium, etc.

# Build
gradlew fullClean - clean services and client projects<br>
gradlew fullBuild - build services and client projects and download dependencies<br>
gradlew cargo[Deploy|Redeploy]Remote - Deploy/Redeploy services WAR to Tomcat<br>
ng serve - build and serve client

# Documentation & Reports
<b>REST API</b> - project-dir/services/dist/docs/api/<br>
<b>JUnit</b> - project-dir/services/build/reports/tests/<br>
<b>FindBugs</b> - project-dir/services/build/reports/findbugs/<br>
<b>PMD</b> - project-dir/services/build/reports/pmd/<br>

# Development
Eclipse<br>
@angular/cli<br>
Npm

