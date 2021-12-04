# ATG
CMS 270 final project: a test generator and grader program that displays a graph about the exam's scores.

## How to run
This program requires [JFreeChart](https://www.jfree.org/jfreechart/) and Java SE 17.

Two driver classes are available: AEG reads a file containing the test taker's name and answers, delineated
by semicolons, outputs incorrectly answered questions and their correct answers to System.out, then displays
a graph showing the user's per subject score and past attempts, courtesy of JFreeChart.

The second one, GUI2, starts a Swing application that presents the test-taker a user-friendly interface
to answer questions. The end result is the same compared to the command-line driver.
