import { restAPI } from "../components/_helpers/api.calls"

export const AuthenticationService = {

    login: (user) => {
        return restAPI.post("/auth/login", user)
            .then((res) => {
                localStorage.setItem('currentUser', JSON.stringify(res.data));
                return res.data;
            }
            );
    },

    register: (user) => {
        return restAPI.post("/auth/register", user)
            .then((res) => res.data);
    },

    logout: () => {
        localStorage.removeItem('currentUser');
    },

 
    isAuthenticated: () => {
        return AuthenticationService.currentUser() != null && AuthenticationService.currentUser() != undefined;

    },

    currentUser: () => {
        let cu = localStorage.getItem('currentUser');
        if (!cu)
            return null;

        JSON.parse(cu);
    }

}