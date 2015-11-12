package templates

html {
    head {
        title("index")
    }

    body {
        h1("test")
        h1("request implict object:${request}")
        h1("params implict object:${params}")
        h1("test:${test}")
        h1("test2:${test2}")
        h1("csrf:${_csrf}")
        h1("session:${session}")
    }
}

