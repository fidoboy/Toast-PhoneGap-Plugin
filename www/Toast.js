function Toast() {
}

Toast.prototype.show = function (message, image, duration, position, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Toast", "show", [message, duration, position, image]);
};

Toast.prototype.showShortTop = function (message, image, successCallback, errorCallback) {
  this.show(message, "short", "top", image, successCallback, errorCallback);
};

Toast.prototype.showShortCenter = function (message, image, successCallback, errorCallback) {
  this.show(message, "short", "center", image, successCallback, errorCallback);
};

Toast.prototype.showShortBottom = function (message, image, successCallback, errorCallback) {
  this.show(message, "short", "bottom", image, successCallback, errorCallback);
};

Toast.prototype.showLongTop = function (message, image, successCallback, errorCallback) {
  this.show(message, "long", "top", image, successCallback, errorCallback);
};

Toast.prototype.showLongCenter = function (message, image, successCallback, errorCallback) {
  this.show(message, "long", "center", image, successCallback, errorCallback);
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
