A simple tasklet job is created in config package.
Launcher package has the job launcher class to run above job.
Main method is in batchdemo package - BatchDemoApplication.java. This has to be executed to run the app and spring will automatically run the run method in launcher class to run the job.
mysql is used as job repository for spring batch metadata tables. See application.properties for properties to be added when auto configuring metadata tables for first time.
08/31 Changes:
Included a second job to demonstarte Chunk oriented step to read from a string array, reverse the string in processor and print the reversed string to console on writer/
