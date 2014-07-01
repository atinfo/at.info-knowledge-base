Javascript / XUL example: how to modify FirePath extension to allow saving locators with custom names
======

Implemented via JS, XUL.

Main usage: saving key/value pairs - name=locator - by a given path.

You can find detailed instruction in the following [article](http://qa-automation-notes.blogspot.com/2014/07/firepath-upgrade-saving-locators-in.html).

Also please have a look to related discussion here http://automated-testing.info/t/firepath-update-js-xul-kak-sohranyat-lokatory-vmeste-s-imenami-po-ukazannomu-puti/4744

Inside of project you will see: 
 
 - Common FirePath extension sources downloaded from [SVN repository](https://code.google.com/p/firepath/source/checkout)
 - Custom UI components implementation: locator name, file path, save button.
 - Custom saving method, that can parse existing / create new file and add / replace selected locator by specified name.

Final **FirePath** layout would be the following:

![firepath layout](http://2.bp.blogspot.com/-BpJ6OhvL8u4/U7LGF79MoTI/AAAAAAAAAR4/mnSGAjE2gk8/s1600/panel+view.png)
