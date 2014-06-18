*** Settings ***

Documentation  A resource file with variables common to both HTML and Flex
...            versions of the application. The correct SUT specific resource
...            is imported based on ${SUT} variable. SeleniumLibrary is also
...            imported here so that no other file needs to import it.
Library        modules/PageObjects/
Library        Selenium2Library

*** Variables ***

${SUT}           html
${SERVER}        www.workopolis.com
${BROWSER}       firefox
${DELAY}         0
${INVALID USER}    demo
${INVALID PASSWD}  mode