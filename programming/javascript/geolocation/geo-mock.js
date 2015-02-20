var mockGeo;

window.mockGeo = {

    PERMISSION_DENIED: {
        PERMISSION_DENIED: 1,
        POSITION_UNAVAILABLE: 2,
        TIMEOUT: 3,
        code: 1,
        message: "Geolocation error: PERMISSION_DENIED"
    },

    POSITION_UNAVAILABLE: {
        PERMISSION_DENIED: 1,
        POSITION_UNAVAILABLE: 2,
        TIMEOUT: 3,
        code: 2,
        message: "Geolocation error: POSITION_UNAVAILABLE"
    },

    TIMEOUT: {
        PERMISSION_DENIED: 1,
        POSITION_UNAVAILABLE: 2,
        TIMEOUT: 3,
        code: 3,
        message: "Geolocation error: TIMEOUT"
    },

    _SUCCESS: 0,
    _ERROR: 1,

    _watches: [],
    _curErr: null,
    _curPos: null,

    _notInited: [],

    _isInited: function () {
        return this._curErr !== null || this._curPos !== null;
    },

    _asyncExec: function (callback, args) {
        setTimeout(function () {
            callback(args);
        }, 0);
    },

    _cbExec: function (resultsArray) {
        if (this._curErr && resultsArray[this._ERROR]) {
            this._asyncExec(resultsArray[this._ERROR], this._curErr);
        } else if (this._curPos && resultsArray[this._SUCCESS]) {
            this._asyncExec(resultsArray[this._SUCCESS], this._curPos);
        }
    },

    _cbWatch: function () {
        for (var idx = 0; idx < this._notInited.length; idx++) {
            this._cbExec(this._notInited[idx]);
        }

        this._notInited = [];

        for (var idx2 = 0; idx2 < this._watches.length; idx2++) {
            if (this._watches[idx2]) {
                this._cbExec(this._watches[idx2]);
            }
        }
    },

    _setCurPosition: function (currentPosition) {
        this._curErr = null;
        this._curPos = currentPosition;
        if (currentPosition) {
            this._cbWatch();
        }
    },

    /*
    *   Simulate geolocation error
    */
    setPositionErr: function (currentError) {
        this._curPos = null;
        this._curErr = currentError;
        if (currentError) {
            this._cbWatch();
        }
    },

    /*
    *   Simulate current position
    */
    setPosition: function (lat, lng) {
        this._setCurPosition({
            coords: {
                latitude: lat,
                longitude: lng,
                accuracy: 20
            }
        });
    },

    /*
    *   Simulate current speed
    *   based on current position
    */
    setSpeed: function(spd) {
        /*  
        *   Here you should add your default coordinates or take them from geolocation
        *   as speed mock doesn't work without lat and lon emulation
        */
        this._setCurPosition({
            coords: {
                /*
                *   Seems like it's impossible to mock
                *   only speed, without lat and lon
                */
                latitude: 1,
                longitude: 1,
                accuracy: 20,
                speed: spd
            }
        });
    }
};

if (!navigator.geolocation) {
    window.navigator.geolocation = {};
}

navigator.geolocation.getCurrentPosition = function(success, error) {
    if (mockGeo._isInited()) {
        mockGeo._cbExec([success, error]);
    } else {
        mockGeo._notInited.push([success, error]);
    }
};

navigator.geolocation.watchPosition = function(success, error) {
    if (mockGeo._isInited()) {
        mockGeo._cbExec([success, error]);
    }
    return mockGeo._watches.push([success, error]) - 1;
};

navigator.geolocation.clearWatch = function(id) {
    mockGeo._watches[id] = null;
};
