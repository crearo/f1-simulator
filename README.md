# F1-Simulator
A project to simulate the F1 experience by setting your own parameters

I found this interesting question on GeekForGeeks. (Yes, okay I've become one of them :P)
But it really did catch my eye. Firstly, because it was Java oriented, and not extremely algorithmic in nature. The question asks you to solve it keeping Java coding practises intact.
Ive tried my best to do so, and I wouldnt mind constructive criticism.
Secondly, its just fun to make simulations, isnt it? :D I spent almost an hour after coding this, messing around with the values and looking at the outcome. And just for the record, I spent 2 hours on coding it.
Though it reminds me more of horse race than an F1, here's the question. 
---

http://www.geeksforgeeks.org/google-interview-question-for-java-position/

Google Interview Question for Java Position
The solution will be evaluated on following parameters.
      Object Oriented Design aspects of the solution.
      Overall coding practices.
      Working test cases of the solution.

You can use Ant/Maven as build tools for the solution, Junit, Mockito or other testing frameworks.
You may also include a brief explanation of your design and assumptions along with your code.

Problem Statement: In a Formula-1 challenge, there are n teams numbered 1 to n. Each team has a car and a driver. Car’s specification are as follows:
– Top speed: (150 + 10 * i) km per hour
– Acceleration: (2 * i) meter per second square.
– Handling factor (hf) = 0.8
– Nitro : Increases the speed to double or top speed, whichever is less. Can be used only once.

Here i is the team number.
The cars line up for the race. The start line for (i + 1)th car is 200 * i meters behind the ith car.

All of them start at the same time and try to attain their top speed. A re-assessment of the positions is done every 2 seconds(So even if the car has crossed the finish line in between, you’ll get to know after 2 seconds). During this assessment, each driver checks if there is any car within 10 meters of his car, his speed reduces to: hf * (speed at that moment). Also, if the driver notices that he is the last one on the race, he uses ‘nitro’.

Taking the number of teams and length of track as the input, Calculate the final speeds and the corresponding completion times.

-----
Do look at the code, I wouldn't mind constructive criticism!
-----

