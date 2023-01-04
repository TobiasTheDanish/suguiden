#!/bin/bash

clear
find ./src/ -type f -name "*.java" > sources.txt
javac -d ./out/ @sources.txt
cd out
java Main
