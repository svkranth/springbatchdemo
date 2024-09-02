A simple tasklet job is created in config package.
Launcher package has the job launcher class to run above job.
Main method is in batchdemo package - BatchDemoApplication.java. This has to be executed to run the app and spring will automatically run the run method in launcher class to run the job.
mysql is used as job repository for spring batch metadata tables. See application.properties for properties to be added when auto configuring metadata tables for first time.
08/31 Changes:
Included a second job to demonstarte Chunk oriented step to read from a string array, reverse the string in processor and print the reversed string to console on writer.

09/02 Changes: 
JobThree is added to app. This Job reads instructor details from a csv file using FLat file Item reader and displays the contents to console in Item writer.
JobFour is added to app. This Job reads instructor details from mysql database table using Jdbc cursor item reader and writes the content to a csv file using flat file item writer.

All Item Readers can be found in path - src/main/java/com/vishnu/batchdemo/reader

All Item Writers can be found in path - src/main/java/com/vishnu/batchdemo/writer

All Jobs can be found in path - src/main/java/com/vishnu/batchdemo/config

The class used to launch jobs can be found in path - src/main/java/com/vishnu/batchdemo/launcher

The POJO class for instructor detail object that is to be used for all readers and writers can be found in path - src/main/java/com/vishnu/batchdemo/model
