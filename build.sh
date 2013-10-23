#!/bin/bash

#compile
javac Nebuloid.java nebuloid/*.java

#run
#java Nebuloid

#package as jar
jar cfm ../Nebuloid.jar Manifest.txt Nebuloid.class nebuloid/*.class images

#run from jar
java -jar ../Nebuloid.jar
