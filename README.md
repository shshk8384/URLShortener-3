URLShortener
============

This proof of concept is the bare bones of shortening a long URL. This java project would ideally be used within a HTTP web service. The web service would have regular expression checking to ensure the value passed in is in fact a URL.  The URL would then need to be split and the string after the domain name will be sent to the URLShortener. The client would invoke the service by passing in a long URL. Once shortened the web service would redirect the call to the shortened URL. This project also returns the long URL should the calling client request it by passing the short URL.