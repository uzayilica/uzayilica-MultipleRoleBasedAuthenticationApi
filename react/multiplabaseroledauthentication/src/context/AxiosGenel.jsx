import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true
});

async function getCsrfToken() {
    try {
        const response = await api.get('/public/csrf-token');
        return response.data.token;
    } catch (error) {
        console.error('CSRF token alınamadı:', error);
        return null;
    }
}

api.interceptors.request.use(async config => {
    if (config.url !== '/public/csrf-token' && !config.url.startsWith('/public')) {
        const token = await getCsrfToken();
        if (token) {
            config.headers['X-XSRF-TOKEN'] = token;
        }

        const jwtToken = localStorage.getItem("jwttoken");
        if (jwtToken) {
            config.headers['Authorization'] = `Bearer ${jwtToken}`;
        }
    }
    return config;
}, error => {
    return Promise.reject(error);
});

export default api;
