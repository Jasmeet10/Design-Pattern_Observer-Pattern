# CSX42: Assignment 3
## Name: Jasmeet Kaur Dua

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentskills/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

ant -buildfile studentskills/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

 ant -buildfile studentskills/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

  ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dout1="output1.txt" -Dout2="output2.txt" -Dout3="output3.txt" -Dmodify="modify.txt" -Ddebug="____Enter the Value____" -Derror="error.txt"


 

Note: Arguments accept the absolute path of the files.
1. for Ddebug- values can be 1,2,3,4,5
      4 - Console write, only represent the console write out put.
      5 - File write , only gives the file as an output.
      1,2,3 - will give both console write and file write.


-----------------------------------------------------------------------
## Description:

1. Tree - BST: O(n) for search and insert 
  However, its other name is sorted tree, so it is helping when we need the data in accending order
reference : https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
2. HashMap - To capture the errors which need to ignore but logged into the error file
Time Complexity : O(1)-insert and search.
3. set: Used for skills.
Time Complexity : O(log (n)) for add, remove, and contains.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [10/July/2020]


# csx42-summer-2020-assign3-Jasmeet10
