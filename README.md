# clj-rest-server

A simple atom- backed REST server

## Usage

Start server by 

    lein run

or by specifying port 

    lein run 8080

Supports REST operations of form: 

    POST /[resourcename] - create a new resource (id will be assigned for the new resource)
    GET /[resource]/[id] - get a single resource
    PUT /[resource]/[id] - update a resource
    DELETE /[resource]/[id] - delete resource 

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
