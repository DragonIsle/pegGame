# Peg solitaire solution finder

This program finds solution for solitaire peg game:
https://en.wikipedia.org/wiki/Peg_solitaire

Two kind of desks implemented: "Triangle" and "English"

Result presented as space-separated sequence of turns, where turn is two number pair(from-to)

Board cells enumerated from left to right and from top to bottom,
 for example triangle board enumerated like this:
<pre> 
        1 
       2 3
     4  5  6 
   7  8  9  10 
 11 12 13 14 15

</pre>

## Getting Started

To run this project, use
```aidl
mvn spring-boot:run
```
and then go to localhost:8080

## Running the tests

```aidl
mvn test
```