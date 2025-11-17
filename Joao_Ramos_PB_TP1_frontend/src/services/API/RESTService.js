import config from "../../config";



class RESTService {
  static instance = null;

  constructor(baseURL) {
    if (ApiClient.instance) {
      return ApiClient.instance; 
    }

    this.baseURL = baseURL;
    this.defaultHeaders = {
      "Content-Type": "application/json",
      Accept: "application/json",
    };

    ApiClient.instance = this;
  }

  static getInstance(baseURL = "/api") {
    if (!ApiClient.instance) {
      ApiClient.instance = new ApiClient(baseURL);
    }
    return ApiClient.instance;
  }

  async request(endpoint, options = {}) {
    const url = `${this.baseURL}${endpoint}`;

    const config = {
      headers: { ...this.defaultHeaders, ...(options.headers || {}) },
      method: options.method || "GET",
    };

    if (options.body) {
      config.body = JSON.stringify(options.body);
    }

    const response = await fetch(url, config);

    if (!response.ok) {
      const msg = await response.text();
      throw new Error(`HTTP ${response.status}: ${msg}`);
    }

    try {
      return await response.json();
    } catch {
      return await response.text();
    }
  }

  get(endpoint, headers = {}) {
    return this.request(endpoint, { method: "GET", headers });
  }

  post(endpoint, body = {}, headers = {}) {
    return this.request(endpoint, { method: "POST", body, headers });
  }

  put(endpoint, body = {}, headers = {}) {
    return this.request(endpoint, { method: "PUT", body, headers });
  }

  delete(endpoint, headers = {}) {
    return this.request(endpoint, { method: "DELETE", headers });
  }
}

const apiClient = new RESTService(config.apiBaseUrl);

export default apiClient;
