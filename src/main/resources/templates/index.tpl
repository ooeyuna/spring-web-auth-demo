package templates

html {
    head {
        title("index")
    }

    body {
        h1("test")
        h1("is_normal :${is_normal}")
        h1("is_admin :${is_admin}")
        h1("request implict object:${request}")
        h1("params implict object:${params}")
        h1("test:${test}")
        h1("test2:${test2}")
        h1("csrf:${_csrf}")
        h1("session:${session}")
        h1("user:${user}")
        div {
            form(action: "/logout") {
                input(type: "hidden", name: _csrf.parameterName, value: _csrf.token)
                button(type: "submit", class: "btn", "login out")
            }
        }
    }
}

