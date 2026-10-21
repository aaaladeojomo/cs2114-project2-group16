# cs2114-project2-group16

## How to Compile/Run the Program and Tests
Right click the file DizzySnakes.java and "Run As" java application to start the working game.
Right click the file DizzySnakes.java and "Run As" Junit Test to run the tests.

## Reflection
A lot of the changes we made were adding or changing classes so they better matched what other group members wrote. For example, in Snake class
we needed to add extra getter and setter methods so the other classes could be passed information, which we missed in the spec. We also removed our node class since we did not realize how much of the code was already given to us.
We did not change much about our LinkedChain class. It was only altered to better fit what we wrote in other classes, like method names.
For our unknowns, we have definetly become more comfortable with using and implementing LinkedChains. The project itself was very helpful in
getting to understand it better. We also learned how to create a text based map, which wasn't as hard as it seemed when we were planning it.
What doesn't work yet is some of our power ups, and obstacles. We also have not done anything with the creating multiple snakes stretch goal.

## Next Steps
What is left to finish for the MVP is fixing some of the Segment, Map, and Snake classes so they fully implement our plan. We also need to add some
of the smaller things we skipped for now like score and placing obstacles.
The stretch goals we will attempt are the power-ups like shield and more, and if we have time we can attempt to make the game work with two snakes.
We will need more tests to handle bad inputs that are with the powerups, like if they are null. We also need to create more situations where the snake could potentially run into itself or have a problem with borders, like for example doing more testing for along the edge of the map and with objects we place on the board.

## Attribution Table

Snake - Sophie Linton, I wrote all the methods, constructors, fields, and descriptions. 
SnakeTest - Sophie Linton, I wrote all the test methods and used GenAI to figure out why some of the tests weren't covering all the cases.

SnakeSegment - Sophie Linton, I wrote all the methods, constructors, fields, and descriptions.
SnakeSegmentTest -

SpecialSegment - Chris Tricarico, I implemented all the methods, constructors, fields, and added descriptions
SpecialSegmentTest - Chris Tricarico, I implemented all the test methods and ensured they covered all the methods in the SpecialSegment class

NormalSegment - 
NormalSegmentTest

SnakeGame

LinkedChain - Ayomide Alade-Ojomo, i implemented the tests for the linkedchain class and made sure that it worked how we would want too for our classes



GameBoard

Snake Game - 
