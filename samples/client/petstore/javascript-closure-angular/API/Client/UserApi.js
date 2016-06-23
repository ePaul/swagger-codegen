/**
 * @fileoverview AUTOMATICALLY GENERATED service for API.Client.UserApi.
 * Do not edit this file by hand or your changes will be lost next time it is
 * generated.
 *
 * This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key &#x60;special-key&#x60; to test the authorization filters.
 * Version: 1.0.0
 * Generated at: 2016-06-23T22:21:49.204+02:00
 * Generated by: class io.swagger.codegen.languages.JavascriptClosureAngularClientCodegen
 */
/**
 * @license Apache 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html
 */

goog.provide('API.Client.UserApi');

goog.require('API.Client.User');

/**
 * @constructor
 * @param {!angular.$http} $http
 * @param {!Object} $httpParamSerializer
 * @param {!angular.$injector} $injector
 * @struct
 */
API.Client.UserApi = function($http, $httpParamSerializer, $injector) {
  /** @private {!string} */
  this.basePath_ = $injector.has('UserApiBasePath') ?
                   /** @type {!string} */ ($injector.get('UserApiBasePath')) :
                   'http://petstore.swagger.io/v2';

  /** @private {!Object<string, string>} */
  this.defaultHeaders_ = $injector.has('UserApiDefaultHeaders') ?
                   /** @type {!Object<string, string>} */ (
                       $injector.get('UserApiDefaultHeaders')) :
                   {};

  /** @private {!angular.$http} */
  this.http_ = $http;

  /** @package {!Object} */
  this.httpParamSerializer = $injector.get('$httpParamSerializer');
}
API.Client.UserApi.$inject = ['$http', '$httpParamSerializer', '$injector'];

/**
 * Create user
 * This can only be done by the logged in user.
 * @param {!User} body Created user object
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.createUser = function(body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling createUser');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'POST',
    url: path,
    json: true,
    data: body,
        params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Creates list of users with given input array
 * 
 * @param {!Array<!API.Client.User>} body List of user object
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.createUsersWithArrayInput = function(body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/createWithArray';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling createUsersWithArrayInput');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'POST',
    url: path,
    json: true,
    data: body,
        params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Creates list of users with given input array
 * 
 * @param {!Array<!API.Client.User>} body List of user object
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.createUsersWithListInput = function(body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/createWithList';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling createUsersWithListInput');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'POST',
    url: path,
    json: true,
    data: body,
        params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Delete user
 * This can only be done by the logged in user.
 * @param {!string} username The name that needs to be deleted
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.deleteUser = function(username, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/{username}'
      .replace('{' + 'username' + '}', String(username));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'username' is set
  if (!username) {
    throw new Error('Missing required parameter username when calling deleteUser');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'DELETE',
    url: path,
    json: true,
            params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Get user by user name
 * 
 * @param {!string} username The name that needs to be fetched. Use user1 for testing. 
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!API.Client.User>}
 */
API.Client.UserApi.prototype.getUserByName = function(username, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/{username}'
      .replace('{' + 'username' + '}', String(username));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'username' is set
  if (!username) {
    throw new Error('Missing required parameter username when calling getUserByName');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'GET',
    url: path,
    json: true,
            params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Logs user into the system
 * 
 * @param {!string} username The user name for login
 * @param {!string} password The password for login in clear text
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!string>}
 */
API.Client.UserApi.prototype.loginUser = function(username, password, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/login';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'username' is set
  if (!username) {
    throw new Error('Missing required parameter username when calling loginUser');
  }
  // verify required parameter 'password' is set
  if (!password) {
    throw new Error('Missing required parameter password when calling loginUser');
  }
  if (username !== undefined) {
    queryParameters['username'] = username;
  }

  if (password !== undefined) {
    queryParameters['password'] = password;
  }

  /** @type {!Object} */
  var httpRequestParams = {
    method: 'GET',
    url: path,
    json: true,
            params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Logs out current logged in user session
 * 
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.logoutUser = function(opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/logout';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'GET',
    url: path,
    json: true,
            params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * Updated user
 * This can only be done by the logged in user.
 * @param {!string} username name that need to be deleted
 * @param {!User} body Updated user object
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.UserApi.prototype.updateUser = function(username, body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/user/{username}'
      .replace('{' + 'username' + '}', String(username));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'username' is set
  if (!username) {
    throw new Error('Missing required parameter username when calling updateUser');
  }
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling updateUser');
  }
  /** @type {!Object} */
  var httpRequestParams = {
    method: 'PUT',
    url: path,
    json: true,
    data: body,
        params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}
