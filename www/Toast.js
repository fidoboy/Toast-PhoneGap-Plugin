function Toast() {
}

Toast.prototype.show = function (message, image, duration, position, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Toast", "show", [message, image, duration, position]);
};

Toast.prototype.showShortTop = function (message, image, successCallback, errorCallback) {
  this.show(message, image, "short", "top", successCallback, errorCallback);
};

Toast.prototype.showShortCenter = function (message, image, successCallback, errorCallback) {
  this.show(message, image, "short", "center", successCallback, errorCallback);
};

Toast.prototype.showShortBottom = function (message, image, successCallback, errorCallback) {
  this.show(message, image, "short", "bottom", successCallback, errorCallback);
};

Toast.prototype.showLongTop = function (message, image, successCallback, errorCallback) {
  this.show(message, image, "long", "top", successCallback, errorCallback);
};

Toast.prototype.showLongCenter = function (message, image, successCallback, errorCallback) {
  this.show(message, image, "long", "center", successCallback, errorCallback);
};

Toast.prototype.showLongBottom = function (message, successCallback, errorCallback) {
  this.show(message, image, "long", "bottom", successCallback, errorCallback);
};

Toast.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.toast = new Toast();
  return window.plugins.toast;
};

cordova.addConstructor(Toast.install);
