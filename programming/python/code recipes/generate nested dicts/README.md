This is example to show how to generate nested python dictionary object without explicit new object generation.

Key principle 

```
from collections import defaultdict
_default_data = lambda: defaultdict(_default_data)
data = _default_data()
```
