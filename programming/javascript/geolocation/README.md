# geo-mock

If you are testing geolocation-based features, you may have been need to change your geolocation according to different tests. One more purpose is to use geolocation speed change. This javascript mock will help you, you can use it inside any test within any programming language.

Just include this file to your testing page or execute the whole code in page context.
```
   <script type="text/javascript" src="geo-mock.js"></script>
```
 
And use it:

<ul>
<li>
latitude, longitude mock:
<ul><li>window.mockGeo.setPosition(10, -15.1212);</li></ul>
</li>

<li>
geolocation error mock:
<ul>
<li>window.mockGeo.setPositionErr(mockGeo.PERMISSION_DENIED);</li>
<li>window.mockGeo.setPositionErr(mockGeo.POSITION_UNAVAILABLE);</li>
<li>window.mockGeo.setPositionErr(mockGeo.TIMEOUT);</li>
</ul>
</li>

<li>
speed mock (m/s):
<ul><li>window.mockGeo.setSpeed(9);</li></ul>
</li>
</ul>





