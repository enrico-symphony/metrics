# metrics
Test Dropwizard vs Micrometer

This project is a test of Dropwizard and Micrometer on a simple Spring Boot 2 project.
Micrometer is Spring Boot 2 native, Dropwizard is the old one used usually on Spring Boot 1.5 projects

#metrics-dropwizard
This module use Dropwizard.
It exposes a web service on [http://localhost/greeting](http://localhost/greeting)
The metrics are sent via a reporter to the console (ConsoleReporter) and they are visible
on actuator [http://localhost/actuator](http://localhost/actuator)

#metrics-micrometer
This module use Micrometer, with Prometheus register.
It exposes a web service on [http://localhost:90/greeting](http://localhost:90/greeting) and
an actuator too [http://localhost:90/actuator](http://localhost:90/actuator)

#metrics-micrometer-custom
This module use Micrometer, with a custom register. The idea is trying to replicate the behavior
of Dropwizard Console Reporter. Please note that it is only a POC
It exposes a web service on [http://localhost:100/greeting](http://localhost:100/greeting) and
an actuator too [http://localhost:100/actuator](http://localhost:100/actuator)

#metrics-micrometer-legacy
This module use Micrometer, but integrating a Dropwizard reporter (ConsoleReporter) wrapped into
Micrometer registry. This solution can be used to easy migrate from Dropwizard to Micrometer if there are
some custom reporter used.
It exposes a web service on [http://localhost:110/greeting](http://localhost:110/greeting) and
an actuator too [http://localhost:110/actuator](http://localhost:110/actuator)