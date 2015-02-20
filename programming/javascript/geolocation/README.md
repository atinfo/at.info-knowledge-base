# geo-mock by <a href=https://github.com/fat-troll/js-geo-mock>fat-troll</a>

If you are testing geolocation-based features, you may have been need to change your geolocation according to different tests. One more purpose is to use geolocation speed change. This javascript mock will help you, you can use it inside any test within any programming language.

Just include this file to your testing page or execute the whole code in page context.
```
   <script type="text/javascript" src="geo-mock.js"></script>
```
 
And use it:

<ul>
<li>
latitude, longitude mock:
  
  window.mockGeo.setPosition(10, -15.1212);
</li>
<li>
   geolocation error mock:

  window.mockGeo.setPositionErr(mockGeo.PERMISSION_DENIED);
  window.mockGeo.setPositionErr(mockGeo.POSITION_UNAVAILABLE);
  window.mockGeo.setPositionErr(mockGeo.TIMEOUT);
</li>

<li>
   speed mock (m/s):

  window.mockGeo.setSpeed(9);
</li>
</ul>





