Java example: how to run the only failed tests from TeamCity build
Implemented via maven, junit.

Main usage: collecting tests failed during tests execution into junit test suite

Inside of project you will see:

Implementation for getting test results from:
 * TeamCity build by build id
 * Latest TeamCity build
 * TeamCity tests csv file
Filtering tool for getting only failed tests
JUnit test suites for all that cases