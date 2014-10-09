Mustache template engine example: how to create custom reports based on TestNG results.
======

Implemented via Maven, TestNG, Mustache.java.

Main usage: creating custom test results reports, email templates, etc.
You can find detailed instruction here: http://qa-automation-notes.blogspot.com/2014/10/custom-reporting-engine-with-mustache.html and http://automated-testing.info/t/mustache-template-engine-ili-kak-sozdavat-kastomnye-reporty-part-2/5178

Inside of project you will see: 
 
 - Custom TestNG listener.
 - ISuite wrappers.
 - Mustache template and related resources.

Mustache template syntax looks like the following:
```xml
    {{#suites}}
        <table class="overviewTable" width="100%">
            <tr>
                <th colspan="8" class="header suite">
                    {{name}} {{#translate}}testGroupSectionTitle{{/translate}}:
                </th>
            </tr>
            {{#testResults}}
                <tr class="columnHeadings">
                    <th width="24%">{{#translate}}testGroupHeader{{/translate}}</th>
                    <th width="18%">{{#translate}}startTimeHeader{{/translate}}</th>
                    <th width="18%">{{#translate}}endTimeHeader{{/translate}}</th>
                    <th width="8%">{{#translate}}executionTimeHeader{{/translate}}</th>
                    <th width="8%">{{#translate}}passedTestsHeader{{/translate}}</th>
                    <th width="8%">{{#translate}}skippedTestsHeader{{/translate}}</th>
                    <th width="8%">{{#translate}}failedTestsHeader{{/translate}}</th>
                    <th width="8%">{{#translate}}passRateHeader{{/translate}}</th>
                </tr>
                <tr class="test" align="center">
                    <td class="test" align="center"><a href="#">{{name}}</a></td>
                    <td class="date" align="center">{{startDate}}</td>
                    <td class="date" align="center">{{endDate}}</td>
                    <td class="duration" align="center">{{duration}}s</td>
                    <td class="{{highlightPassed}} number" align="center">{{passed}}</td>
                    <td class="{{highlightSkipped}} number" align="center">{{skipped}}</td>
                    <td class="{{highlightFailed}} number" align="center">{{failed}}</td>
                    <td class="passRate" align="center">{{passRate}}%</td>
                </tr>
                <tr></tr>
            {{/testResults}}
        </table>
    {{/suites}}
```

HTML report output:

![plugin layout](http://2.bp.blogspot.com/-uGZ-ADoaS7E/VDUZ3Fj5MxI/AAAAAAAAAbM/aOU-SZPUw5U/s1600/report.png)
