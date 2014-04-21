# I need to create and verifry following structure
# {
#     "server": {
#         "host": "127.0.0.1",
#         "port": "22"
#     },
#     "configuration": {
#         "ssh": {
#             "access": "true",
#             "login": "some",
#             "password": "some"
#         }
#     }
# }


# Example 1

data = {}
# some logic here
print data["server"]  # will raise exception due to 'KeyError: 'server''
# some logic here
data["server"] = {
    "host": "127.0.0.1",
    "port": "22"
}
# some logic here
if data["configuration"]["ssh"]["login"]:  # will raise exception here
    pass
# some logic here
data["configuration"] = {
    "ssh": {
    "access": "true",
    "login": "some",
    "password": "some"
    }
}


# Example 2

data = {}
# some logic here
print data.get("server")
# some logic here
data["server"] = {
    "host": "127.0.0.1",
    "port": "22"
}
# some logic here
if data.get("configuration", {}).get("ssh", {}).get("login", {}):
    # will raise exception
    # AttributeError: 'NoneType' object has no attribute 'get'
    pass
# some logic here
data["configuration"] = {
    "ssh": {
    "access": "true",
    "login": "some",
    "password": "some"
    }
}


# Example 3

from collections import defaultdict
_default_data = lambda: defaultdict(_default_data)
data = _default_data()
# some logic here
print data["server"]
# some logic here
data["server"]["host"] = "127.0.0.1"
data["server"]["port"] = "22"
# some logic here
if data["configuration"]["ssh"]["login"]:
    print ("some logic")
# some logic here
data["configuration"]["ssh"]["access"] = "true"
data["configuration"]["ssh"]["login"] = "some"
data["configuration"]["ssh"]["password"] = "some"

import json
print json.dumps(data, indent=2)
