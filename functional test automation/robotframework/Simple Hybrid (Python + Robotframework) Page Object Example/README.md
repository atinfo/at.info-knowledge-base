# Simple Hybrid (Python + Robotframework) Page Object Example

I've used as a base adamgoucher's code (https://github.com/adamgoucher/robotframework-pageobjects)
and fixed path's so it can be usable by RobotFramework (http://robotframework.googlecode.com/hg/doc/userguide/RobotFrameworkUserGuide.html#using-physical-path-to-library)

# Solution

Just needed to fix path for PageObject inside scripts/common_resource.robot so it can be reachable by RB.

```
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
```

Related discusssion is here http://automated-testing.info/t/robot-framework-pycharm/4657
