package templates

html {
    head {
        title("login")
    }
    body {
        p(params)
        form(action: "/login", method: "post") {
            if (error.isPresent()) {
                p("Invalid username and password.")
            }
            div {
                label(for: "username", "Username")
                input(type: "text", id: "username", name: "username")
            }
            div {
                label(for: "password", "Password")
                input(type: "text", id: "password", name: "password")
            }
            input(type: "hidden", name: _csrf.parameterName, value: _csrf.token)
            div {
                button(type: "submit", class: "btn", "Login in")
            }
        }

    }
}
