*** Settings ***

Documentation  A test suite with a single test for valid login. This test has
...            a workflow that is created using keywords from the resource file.
Resource       common_resource.robot

Test Setup    Open Browser To English Home Page
Test Teardown  Close Browser After Run

*** Test Cases ***

Invalid Login
    Navigate To Sign In Page
    Set Sign In Email As    demo
    Set Sign In Password As    demo
    Submit Sign In Credentials
    Sign In Error Message Should Be  Sorry, we don't recognize that email address, username or password. Please try again.