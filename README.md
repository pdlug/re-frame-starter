# Re-frame starter app

This is a re-frame app skeleton that's a bit more full featured than the app
created by `lein new re-frame`. I found myself making the same modifications to
every new project: add a pedestal app for an API (and set up reloading), carve
out a component organization, add routing, etc. So I packed this into something
easy to clone and start working with for every new project.

Server side [pedestal](http://pedestal.io/) app:

* Static asset serving
* JSON REST API with echo endpoint for prototyping
* Hot code reloading

Client side [re-frame](https://github.com/Day8/re-frame) app:

* Routing using [bidi](https://github.com/juxt/bidi)
* Event interceptor that validate sapp DB against spec in development 
* Components structured into separate namespaces each with their own db spec, event handlers, subscriptions, and views. Namespaced using the re-frame [synthetic namespace](https://github.com/Day8/re-frame/blob/master/docs/Namespaced-Keywords.md) pattern 
* Pages separated out with potential to use parallel structure to components as their complexity grows 

To demonstrate things a homepage with a signup form that POSTs to an API which
just echo's back the response is provided. 

## Development Mode

Start the server in a terminal:

```bash
lein run-dev
```

In another terminal start figwheel:

```bash
lein figwheel dev
```

Point your browser to: [http://localhost:8080](http://localhost:8080) and you should be ready to play.

## TODO

* Package as a lein template
* Add test coverage/framework(s)
