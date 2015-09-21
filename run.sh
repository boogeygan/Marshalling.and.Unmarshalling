#!/bin/bash
echo "***\$***"
if [ $# -eq 0 ] 
then
echo "Invalid Number of arguments. Please give a file name"
exit 1;
fi
if [ ! -f "$1" ] 
then
echo "Invalid File."
exit 1;
fi

javac -d ./ PROGRAMS/*.java
fileName=`readlink -f "$1"`
#echo "Input file is: " "$fileName"

#make background to the server-process 
#this is for the custom-byte based format
java CustomByteServer "$fileName" &
sleep 5
java CustomByteClient

#this is for the XML based 
java XMLServer "$fileName"  &
sleep 5
java XMLClient
rm *.class
echo "***\$***"
