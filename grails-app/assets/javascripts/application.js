// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require scripts/ox-ui-bundle.js
// require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}

// Where the OX-UI bundle is located
//
// Note that Grails asset-pipeline expose assets by omitting the content-type folder. For example,
// grails-app/assets/javascripts/bootstrap.js becomes /assets/bootstrap.js. Since the OX-UI bundle
// is located in grails-app/assets/javascripts/scripts, the base URL is set to /assets/scripts.
requirejs.config({baseUrl: '/assets/scripts'});

// Bootstrap Aurelia into the web page execution context.
require(['aurelia-bootstrapper']);