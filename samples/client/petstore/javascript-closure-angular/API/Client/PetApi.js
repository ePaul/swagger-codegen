/**
 * @fileoverview AUTOMATICALLY GENERATED service for API.Client.PetApi.
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

goog.provide('API.Client.PetApi');

goog.require('API.Client.ApiResponse');
goog.require('API.Client.Pet');

/**
 * @constructor
 * @param {!angular.$http} $http
 * @param {!Object} $httpParamSerializer
 * @param {!angular.$injector} $injector
 * @struct
 */
API.Client.PetApi = function($http, $httpParamSerializer, $injector) {
  /** @private {!string} */
  this.basePath_ = $injector.has('PetApiBasePath') ?
                   /** @type {!string} */ ($injector.get('PetApiBasePath')) :
                   'http://petstore.swagger.io/v2';

  /** @private {!Object<string, string>} */
  this.defaultHeaders_ = $injector.has('PetApiDefaultHeaders') ?
                   /** @type {!Object<string, string>} */ (
                       $injector.get('PetApiDefaultHeaders')) :
                   {};

  /** @private {!angular.$http} */
  this.http_ = $http;

  /** @package {!Object} */
  this.httpParamSerializer = $injector.get('$httpParamSerializer');
}
API.Client.PetApi.$inject = ['$http', '$httpParamSerializer', '$injector'];

/**
 * Add a new pet to the store
 * 
 * @param {!Pet} body Pet object that needs to be added to the store
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.PetApi.prototype.addPet = function(body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling addPet');
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
 * Deletes a pet
 * 
 * @param {!number} petId Pet id to delete
 * @param {!string=} opt_apiKey 
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.PetApi.prototype.deletePet = function(petId, opt_apiKey, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/{petId}'
      .replace('{' + 'petId' + '}', String(petId));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'petId' is set
  if (!petId) {
    throw new Error('Missing required parameter petId when calling deletePet');
  }
  headerParams['api_key'] = opt_apiKey;

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
 * Finds Pets by status
 * Multiple status values can be provided with comma separated strings
 * @param {!Array<!string>} status Status values that need to be considered for filter
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!Array<!API.Client.Pet>>}
 */
API.Client.PetApi.prototype.findPetsByStatus = function(status, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/findByStatus';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'status' is set
  if (!status) {
    throw new Error('Missing required parameter status when calling findPetsByStatus');
  }
  if (status !== undefined) {
    queryParameters['status'] = status;
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
 * Finds Pets by tags
 * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
 * @param {!Array<!string>} tags Tags to filter by
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!Array<!API.Client.Pet>>}
 */
API.Client.PetApi.prototype.findPetsByTags = function(tags, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/findByTags';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'tags' is set
  if (!tags) {
    throw new Error('Missing required parameter tags when calling findPetsByTags');
  }
  if (tags !== undefined) {
    queryParameters['tags'] = tags;
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
 * Find pet by ID
 * Returns a single pet
 * @param {!number} petId ID of pet to return
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!API.Client.Pet>}
 */
API.Client.PetApi.prototype.getPetById = function(petId, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/{petId}'
      .replace('{' + 'petId' + '}', String(petId));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'petId' is set
  if (!petId) {
    throw new Error('Missing required parameter petId when calling getPetById');
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
 * Update an existing pet
 * 
 * @param {!Pet} body Pet object that needs to be added to the store
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.PetApi.prototype.updatePet = function(body, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet';

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  // verify required parameter 'body' is set
  if (!body) {
    throw new Error('Missing required parameter body when calling updatePet');
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

/**
 * Updates a pet in the store with form data
 * 
 * @param {!number} petId ID of pet that needs to be updated
 * @param {!string=} opt_name Updated name of the pet
 * @param {!string=} opt_status Updated status of the pet
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise}
 */
API.Client.PetApi.prototype.updatePetWithForm = function(petId, opt_name, opt_status, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/{petId}'
      .replace('{' + 'petId' + '}', String(petId));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  /** @type {!Object} */
  var formParams = {};

  // verify required parameter 'petId' is set
  if (!petId) {
    throw new Error('Missing required parameter petId when calling updatePetWithForm');
  }
  headerParams['Content-Type'] = 'application/x-www-form-urlencoded';

  formParams['name'] = opt_name;

  formParams['status'] = opt_status;

  /** @type {!Object} */
  var httpRequestParams = {
    method: 'POST',
    url: path,
    json: false,
        data: this.httpParamSerializer(formParams),
    params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}

/**
 * uploads an image
 * 
 * @param {!number} petId ID of pet to update
 * @param {!string=} opt_additionalMetadata Additional data to pass to server
 * @param {!Object=} opt_file file to upload
 * @param {!angular.$http.Config=} opt_extraHttpRequestParams Extra HTTP parameters to send.
 * @return {!angular.$q.Promise<!API.Client.ApiResponse>}
 */
API.Client.PetApi.prototype.uploadFile = function(petId, opt_additionalMetadata, opt_file, opt_extraHttpRequestParams) {
  /** @const {string} */
  var path = this.basePath_ + '/pet/{petId}/uploadImage'
      .replace('{' + 'petId' + '}', String(petId));

  /** @type {!Object} */
  var queryParameters = {};

  /** @type {!Object} */
  var headerParams = angular.extend({}, this.defaultHeaders_);
  /** @type {!Object} */
  var formParams = {};

  // verify required parameter 'petId' is set
  if (!petId) {
    throw new Error('Missing required parameter petId when calling uploadFile');
  }
  headerParams['Content-Type'] = 'application/x-www-form-urlencoded';

  formParams['additionalMetadata'] = opt_additionalMetadata;

  formParams['file'] = opt_file;

  /** @type {!Object} */
  var httpRequestParams = {
    method: 'POST',
    url: path,
    json: false,
        data: this.httpParamSerializer(formParams),
    params: queryParameters,
    headers: headerParams
  };

  if (opt_extraHttpRequestParams) {
    httpRequestParams = angular.extend(httpRequestParams, opt_extraHttpRequestParams);
  }

  return (/** @type {?} */ (this.http_))(httpRequestParams);
}
