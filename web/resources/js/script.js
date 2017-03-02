window.fbAsyncInit = function () {
    FB.init({
        appId: '269250396835432',
        cookie: true, // enable cookies to allow the server to access 
        // the session
        xfbml: true, // parse social plugins on this page
        version: 'v2.8' // use graph api version 2.8
    });

    // This is called with the results from from FB.getLoginStatus().
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            document.getElementById('status').innerHTML = 'We are connected.';
            document.getElementById('login').style.visibility = 'hidden';
        } else if (response.status === 'not_authorized') {
            document.getElementById('status').innerHTML = 'We are not logged in.'
        } else {
            document.getElementById('status').innerHTML = 'You are not logged into Facebook.';
        }
    });
};

// Load the SDK asynchronously
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id))
        return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function login() {
    FB.login(function (response) {
        if (response.status === 'connected') {
            document.getElementById('status').innerHTML = 'We are connected.';
            document.getElementById('login').style.visibility = 'hidden';
        } else if (response.status === 'not_authorized') {
            document.getElementById('status').innerHTML = 'We are not logged in.'
        } else {
            document.getElementById('status').innerHTML = 'You are not logged into Facebook.';
        }
    }, {scope: 'publish_actions'});
}
function getInfo() {
    FB.api('/me', 'GET', {fields: 'first_name,last_name,name,id'}, function (response) {
        if (response.name === undefined) {
            response.name = null;
        }
        document.getElementById('status').innerHTML = response.name;
        document.getElementById("hiddenField").value = response.name;
        console.log(response.name);
    });
}




