module.exports = {
	Position: { TOP: 0, BOTTOM: 1, XY: 2 },
    addBanner: function(zoneId, position, x, y) {
        var self = this;
        cordova.exec(
            function (result) {
                if (typeof result == "string") {
					if (result == "onBannerClick") {
                        if (self.onBannerClick) {
                            self.onBannerClick();
                        }
                    }
                    if (result == "onBannerClose") {
                        if (self.onBannerClose) {
                            self.onBannerClose();
                        }
                    }
                    if (result == "onBannerFail") {
                        if (self.onBannerFail) {
                            self.onBannerFail();
                        }
                    }
                    if (result == "onBannerReady") {
                        if (self.onBannerReady) {
                            self.onBannerReady();
                        }
                    }
                    if (result == "onBannerRequest") {
                        if (self.onBannerRequest) {
                            self.onBannerRequest();
                        }
                    }
                    if (result == "onBannerShow") {
                        if (self.onBannerShow) {
                            self.onBannerShow();
                        }
                    }
                }
            },
            null,
            'Rayka',
            'addBanner',
            [ zoneId, position, x, y ]
        );
    },
    onBannerClick: null,
    onBannerClose: null,
    onBannerFail: null,
    onBannerReady: null,
    onBannerRequest: null,
    onBannerShow: null,
    removeBanner: function() {
        cordova.exec(
			null,
			null,
            'Rayka',
            'removeBanner',
            []
        ); 
    },
	cachePopup: function (zoneId) {
        var self = this;
        cordova.exec(
            function (result) {
                if (typeof result == "string") {
					if (result == "onPopupClick") {
                        if (self.onPopupClick) {
                            self.onPopupClick();
                        }
                    }
                    if (result == "onPopupClose") {
                        if (self.onPopupClose) {
                            self.onPopupClose();
                        }
                    }
                    if (result == "onPopupFail") {
                        if (self.onPopupFail) {
                            self.onPopupFail();
                        }
                    }
                    if (result == "onPopupReady") {
                        if (self.onPopupReady) {
                            self.onPopupReady();
                        }
                    }
                    if (result == "onPopupRequest") {
                        if (self.onPopupRequest) {
                            self.onPopupRequest();
                        }
                    }
                    if (result == "onPopupShow") {
                        if (self.onPopupShow) {
                            self.onPopupShow();
                        }
                    }
                }
            },
            null,
            'Rayka',
            'cachePopup',
            [ zoneId ]
        );
    },
    onPopupClick: null,
    onPopupClose: null,
    onPopupFail: null,
    onPopupReady: null,
    onPopupRequest: null,
    onPopupShow: null,
    showPopup: function() {
        cordova.exec(
			null,
			null,
            'Rayka',
            'showPopup',
            []
        ); 
    },
    cacheVideo: function (zoneId) {
        var self = this;
        cordova.exec(
            function (result) {
                if (typeof result == "string") {
					if (result == "onVideoClick") {
                        if (self.onVideoClick) {
                            self.onVideoClick();
                        }
                    }
                    if (result == "onVideoFail") {
                        if (self.onVideoFail) {
                            self.onVideoFail();
                        }
                    }
                    if (result == "onVideoReady") {
                        if (self.onVideoReady) {
                            self.onVideoReady();
                        }
                    }
                    if (result == "onVideoRequest") {
                        if (self.onVideoRequest) {
                            self.onVideoRequest();
                        }
                    }
                    if (result == "onVideoStart") {
                        if (self.onVideoStart) {
                            self.onVideoStart();
                        }
                    }
                    if (result == "onVideoComplete") {
                        if (self.onVideoComplete) {
                            self.onVideoComplete();
                        }
                    }
                    if (result == "onVideoNotComplete") {
                        if (self.onVideoNotComplete) {
                            self.onVideoNotComplete();
                        }
                    }
                }
            },
            null,
            'Rayka',
            'cacheVideo',
            [ zoneId ]
        );
    },
    onVideoClick: null,
    onVideoFail: null,
    onVideoReady: null,
    onVideoRequest: null,
    onVideoStart: null,
    onVideoComplete: null,
    onVideoNotComplete: null,
    showVideo: function () {
        cordova.exec(
            null,
            null,
            'Rayka',
            'showVideo',
            []
        );
    }
};