import axios from 'axios'
import { config } from "../../constants";
import { AlertService } from "../../service/AlertService"
import get from 'lodash/get';
import set from 'lodash/set';
import qs from 'qs';
import { AuthenticationService } from "../../service/AuthenticationService"


const restAPI = axios.create({
    baseURL: config.url.API_BASE_URL
})



restAPI.interceptors.request.use((config) => {
    let currentUser = AuthenticationService.currentUser();
    
    if (currentUser && currentUser.token) {
        set(config, 'headers.token', currentUser.token);
    }

    return config;
});


restAPI.interceptors.response.use(function (response) {

    return response;
}, function (error) {

    console.log(error.response.status);

    AlertService.error(error.response.data.message === '' ? error.response.data.error : error.response.data.message);

    if (error.response.status === 401) {

        if (AuthenticationService.isAuthenticated()) {
            AuthenticationService.logout();
            window.location = '/';
            
        }
        return Promise.reject(error);
    } else {

        return Promise.reject(error);
    }
});

export { restAPI }