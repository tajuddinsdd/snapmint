var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'snapmint2', 'coolMethod', [arg0]);
};
exports.openSnapmint = function (arg0, success, error) {
    exec(success, error, 'snapmint2', 'openSnapmint', [arg0]);
};
