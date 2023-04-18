Author : Phillip Marais
Email : Phillipmar27@gmail.com
Number : 0794898533

OS : windows
Java version 18
----------------------------------------------------------------------------------
                Desription of program :
----------------------------------------------------------------------------------
The program reads in the input of cell phone towers from a text file located in the same directory.
It then creates an Arraylist of objects of cell phone towers that acts as the source of information.

I then use a algorithm (That I created by myself) to identify where the frequencies should be assigned.
After they are assigned the program prints out the arraylist of cell phone towers.

The way my algorithm works is to maximise the distance of towers with the same frequency, while trying to minimise
the towers with different frequencies.
----------------------------------------------------------------------------------
                Desription of algorithm :
----------------------------------------------------------------------------------
1-It start by taking the first unassigned tower in the arraylist. It then calculates the farthest tower (in kilometers)
and then it assigns it the same frequencies. 
2-Afterwards it calculates the closest tower and assigns it and it's farthest
tower the next frequency.It repeats this part(number of times depend on the number of frequencies left) and assigns those towers the remaining frequencies.
3-If there are more unassigned towers go step 1.
----------------------------------------------------------------------------------
                HOW TO RUN :
----------------------------------------------------------------------------------
In the commandline
1.compile development_task.java with "javac .\development_task.java"
2.run the program with "java .\development_task.java" 
----------------------------------------------------------------------------------
                Warnings :
----------------------------------------------------------------------------------
The program gives one warning one the command line when running. This should be ignored.

----------------------------------------------------------------------------------
                THANK YOU 
----------------------------------------------------------------------------------
Thank you for the opportunity to prove myself!
